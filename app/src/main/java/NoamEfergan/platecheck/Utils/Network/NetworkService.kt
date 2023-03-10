package NoamEfergan.platecheck.Utils.Network

import NoamEfergan.platecheck.BuildConfig
import NoamEfergan.platecheck.Secrets
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private fun getBaseUrl(): String {
        if (BuildConfig.DEBUG) {
            return "https://uat.driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/v1/"
        } else {
            return "https://driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/v1/"
        }
    }

    // Perform Retrofit2 post request to get DVLAResponse item for a giver plate number
    suspend fun getDVLAItem(plateNumber: String): Pair<DVLAResponse?, String?> {
        val retrofit = Retrofit.Builder().baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(DVLAApiInterface::class.java)
        val call = service.getDvlaResponse(
            headers = mapOf("Content-Type" to "application/json", "x-api-key" to Secrets().API_KEY),
            body = CarRequest(registrationNumber = plateNumber)
        )

        val value = call.body()
        value?.let {
            return Pair(it, null)
        } ?: run {
            val errorText: String = mapErrorCodeToString(call.code())
            return Pair(null, errorText)
        }


    }

    private fun mapErrorCodeToString(code: Int): String {
        return when (code) {
            400 -> "The registration plate number that you've entered seems to be invalid."
            429 -> "You're making too many requests. please wait before trying again."
            in 500..599-> "The DVLA server seems to be down. please try again later."
            else -> "Whoops! something went wrong"
        }
    }
}