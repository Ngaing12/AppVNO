package app.pldt.appvno.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET

interface AppVnoAPI {


    @GET("api/notification/status")
    suspend fun reportNotificationStatus(
       @Field("notification_id") notificationId : String,
       @Field("user_id") userId : String,
       @Field("status") status :String,
       @Field("device_token") deviceToken : String
    ) : Response<NewsResponse>


}