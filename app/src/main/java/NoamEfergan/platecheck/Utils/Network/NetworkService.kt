package NoamEfergan.platecheck.Utils.Network

import NoamEfergan.platecheck.BuildConfig
import NoamEfergan.platecheck.Secrets
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private fun getBaseUrl(): String {
        if (BuildConfig.DEBUG ) {
            return "https://uat.driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/v1/"
        } else {
            return "https://driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/v1/"
        }
    }

    // Perform Retrofit2 post request to get DVLAResponse item for a giver plate number
    suspend fun getDVLAItem(plateNumber: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DVLAApiInterface::class.java)
        val call = service.getDvlaResponse(
            headers = mapOf("Content-Type" to "application/json", "x-api-key" to Secrets().API_KEY),
            body = CarRequest(registrationNumber = plateNumber)
        )
        val response = call.isSuccessful

    }
}