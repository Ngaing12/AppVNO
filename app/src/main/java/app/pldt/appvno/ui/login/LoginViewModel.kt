package app.pldt.appvno.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pldt.appvno.AppVNOApplication
import app.pldt.appvno.common.Resource
import app.pldt.appvno.firebase.MyFirebaseDatabase
import app.pldt.appvno.model.NotificationStatusResponse
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.repository.ReportRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel : ViewModel(), KoinComponent {

    val loginResponse : MutableLiveData<Resource<String>> = MutableLiveData()
    val notificationResponse : MutableLiveData<Resource<NotificationStatusResponse>> = MutableLiveData()

    private val reportRepository : ReportRepository by inject()

    val tempUser1 = TempUser(
        "9000000000",
        "sample@gmail.com",
        "123456",
        "YsoykGNdT9azgDYZGtrX1RFR6Pg1"
    )

    val tempUser2 = TempUser(
        "9111111111",
        "example@gmail.com",
        "123456",
        "mN20pCadWOQLdhkKFIFEkPP2I6u2"
    )
    val phone1 = "9000000000"

    val phone2 = "9111111111"

    // TODO - change to courotine
    fun loginToFirebase(mobileNum : String, password : String){
        if (FirebaseAuth.getInstance().currentUser != null )
            FirebaseAuth.getInstance().signOut()


        loginResponse.postValue(Resource.Loading())
        val user = getUser(mobileNum)

        if (user == null) {
            loginResponse.postValue(Resource.Error("User not found"))
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    AppVNOApplication.getInstance().tempUser = user
                    MyFirebaseDatabase.startListening(user.id)
                    loginResponse.postValue(Resource.Success("Success"))
                }
            }
            .addOnFailureListener {
                loginResponse.postValue(Resource.Error(it.message ?: "Something went wrong!"))
            }
    }


    private fun getUser(mobileNum: String) : TempUser? {
        if (tempUser1.number == mobileNum)
            return  tempUser1
        if (tempUser2.number == mobileNum)
            return tempUser2
        return null
    }


    fun notificationReport(
        notificationId : String,
        userId : String,
        status : String,
        deviceToken : String
    ) = viewModelScope.launch {
        notificationResponse.postValue(Resource.Loading())
        notificationResponse.postValue(reportRepository.reportNotificationStatus(notificationId, userId, status,deviceToken))
    }

}