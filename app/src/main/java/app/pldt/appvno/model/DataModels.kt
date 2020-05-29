package app.pldt.appvno.model

data class LocationDetails (
    val countryCode : String,
    val countryName : String,
    val state : String,
    val city : String,
    val street : String,
    val streetNo : String,
    val postalCode : String
)



data class Onboarding(
    val title: String,
    val description : String,
    val icon : Int
)