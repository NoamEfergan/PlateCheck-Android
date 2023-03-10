package NoamEfergan.platecheck.Utils.Network

import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class DVLAResponse(
    val registrationNumber: String? = null,
    val artEndDate: String? = null,
    val co2Emissions: Long? = null,
    val engineCapacity: Long? = null,
    val euroStatus: String? = null,
    val markedForExport: Boolean? = null,
    val fuelType: String? = null,
    val motStatus: String? = null,
    val revenueWeight: Long? = null,
    val colour: String? = null,
    val make: String? = null,
    val typeApproval: String? = null,
    val yearOfManufacture: Long? = null,
    val taxDueDate: String? = null,
    val taxStatus: String? = null,
    val dateOfLastV5CIssued: String? = null,
    val wheelplan: String? = null,
    val monthOfFirstDvlaRegistration: String? = null,
    val monthOfFirstRegistration: String? = null,
    val realDrivingEmissions: String? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DVLAResponse>(json)
    }
}