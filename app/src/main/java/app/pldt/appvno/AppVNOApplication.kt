package app.pldt.appvno

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.pldt.appvno.common.SessionManager
import app.pldt.appvno.firebase.MyFirebaseDatabase
import  app.pldt.appvno.googleAds.GoogleAdsManager
import app.pldt.appvno.location.LocationRequestManager
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.repository.ReportRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppVNOApplication : Application() {

    // Temp
    private val points = MutableLiveData<Int>()
    fun getPoints(): LiveData<Int> = points
    var tempUser : TempUser? = null

    fun addPoints(amount : Int){
        val num = points.value
        if (num != null) {
            points.value = num + amount
        }
    }

    override fun onCreate() {
        super.onCreate()


        // Temp Create Listener for call in this user and dialog for prompt


        points.value = 0

        // Initiate managers
        SessionManager.init(this)
        LocationRequestManager(this)
        GoogleAdsManager.init(this)
        MyFirebaseDatabase(this)

        setupKoin()

    }

    init {
        instance = this
    }

    private fun setupKoin() {
        val modules = module {
            // Room
            //single(named("appDatabase")) { AppDatabase.getInstance(get()) }
            //single { AppExecutors() }
            // single { AppDatabase.getInstance(get()).driverDao() }
            // Repo6
            single { ReportRepository(get()) }
            //single { DriverRepository(get()) }
        }

        startKoin {
            androidContext(this@AppVNOApplication)
            modules(modules)
        }
    }

    companion object {
        private lateinit var instance : AppVNOApplication

        fun getInstance(): AppVNOApplication {
            return instance
        }
    }
}