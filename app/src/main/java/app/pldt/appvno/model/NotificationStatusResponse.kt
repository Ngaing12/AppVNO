package app.pldt.appvno.model

data class NotificationStatusResponse(
    val `data`: Data
)
data class Attributes(
    val device_token: String,
    val notification_id: String,
    val status: String,
    val user_id: String
)

data class Data(
    val attributes: Attributes,
    val id: String,
    val links: Links,
    val type: String
)

data class Links(
    val self: String
)