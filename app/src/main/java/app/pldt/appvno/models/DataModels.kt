package app.pldt.appvno.models

data class LocationDetails (
    val countryCode : String,
    val countryName : String,
    val state : String,
    val city : String,
    val street : String,
    val streetNo : String,
    val postalCode : String
)