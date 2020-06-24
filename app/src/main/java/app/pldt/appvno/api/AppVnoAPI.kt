package app.pldt.appvno.api

import app.pldt.appvno.model.NotificationStatusResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AppVnoAPI {

    @FormUrlEncoded
    @POST("api/notification/status")
    suspend fun reportNotificationStatus(
       @Field("notification_id") notificationId : String,
       @Field("user_id") userId : String,
       @Field("status") status :String,
       @Field("device_token") deviceToken : String
    ) : Response<NotificationStatusResponse>

}