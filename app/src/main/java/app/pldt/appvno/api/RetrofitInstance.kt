package app.pldt.appvno.api

import android.content.Context
import app.pldt.appvno.common.BASE_URL
import app.pldt.appvno.common.NetworkConnectionInterceptor
import app.pldt.appvno.common.SessionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitInstance (private val context: Context){

    protected var api: AppVnoAPI

    init {
        val retrofit = createRetroBuilder().build()
        api = retrofit.create(AppVnoAPI::class.java)
        generateTokenBaseApi(context, BASE_URL)
    }

    private fun createRetroBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    private fun createRetroBuilder(baseApi: String): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create())
    }


    protected fun rebuildRetroWithClient(baseApi: String, client: OkHttpClient) {
        val builder = createRetroBuilder(baseApi)
        val retrofit = builder.client(client).build()

        api = retrofit.create(AppVnoAPI::class.java)
    }

    protected fun generateTokenBaseApi(context: Context, baseApi: String) {
        val sessionToken = SessionManager.getSession().token
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        networkConnectionInterceptor.setContext(context)
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $sessionToken")
                    .build()
                chain.proceed(newRequest)
            }.build()

        rebuildRetroWithClient(baseApi, client)
    }

//
//    private val retrofit by lazy {
//        val networkConnectionInterceptor = NetworkConnectionInterceptor()
//        networkConnectionInterceptor.setContext(context)
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .addInterceptor(networkConnectionInterceptor)
//            .build()
//
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }

}