package NoamEfergan.platecheck

import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class DVLAResponse(
    val registrationNumber: String,
    val artEndDate: String,
    val co2Emissions: Long,
    val engineCapacity: Long,
    val euroStatus: String,
    val markedForExport: Boolean,
    val fuelType: String,
    val motStatus: String,
    val revenueWeight: Long,
    val colour: String,
    val make: String,
    val typeApproval: String,
    val yearOfManufacture: Long,
    val taxDueDate: String,
    val taxStatus: String,
    val dateOfLastV5CIssued: String,
    val wheelplan: String,
    val monthOfFirstDvlaRegistration: String,
    val monthOfFirstRegistration: String,
    val realDrivingEmissions: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DVLAResponse>(json)
    }
}

