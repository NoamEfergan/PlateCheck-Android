package NoamEfergan.platecheck.Utils.Network

import NoamEfergan.platecheck.Secrets
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface DVLAApiInterface {
    @POST("vehicles")
    suspend fun getDvlaResponse(
        @HeaderMap headers: Map<String, String>, @Body body: CarRequest
    ): Response<DVLAResponse>
}