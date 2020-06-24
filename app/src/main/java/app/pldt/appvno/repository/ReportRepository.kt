package app.pldt.appvno.repository

import android.content.Context
import app.pldt.appvno.api.RetrofitInstance
import app.pldt.appvno.common.handleRetrofitDefaultRequest

class ReportRepository(context : Context) : RetrofitInstance(context) {
    suspend fun <T>reportNotificationStatus(notificationId : String, userId : String, status : String,  deviceToken : String)  =
        handleRetrofitDefaultRequest { api.reportNotificationStatus(notificationId, userId, status, deviceToken) } as T

}