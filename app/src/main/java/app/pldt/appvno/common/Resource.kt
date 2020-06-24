package app.pldt.appvno.common

import android.util.Log
import retrofit2.Response

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

const val DEFAULT_ERROR_MESSAGE = "Something went wrong. Please try again later."

suspend fun <T : Any>handleRetrofitDefaultRequest(requestFunc: suspend () -> T): Resource<T> {
    return try {
        val response = requestFunc.invoke() as Response<T>
        if (response.isSuccessful) {
            Log.d("Test", response.code().toString())
            response.body()?.let {
                Resource.Success(it)
            } ?: throw NullPointerException()

        } else {
            // parse defending on error
            // errorRequest(response)
            Log.d("Test", response.code().toString())
            Resource.Error(response.message(), null)
        }

    } catch (e: Exception) {
        Log.d("Test", e.message?: "")
        Resource.Error(e.message ?: "DEFAULT_ERROR_MESSAGE", null)
    }
}