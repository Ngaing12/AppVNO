package app.pldt.appvno.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.pldt.appvno.model.TempUser
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(app : Application) : AndroidViewModel(app) {

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

//    // TODO - change to courotine
//    private fun loginToFirebase(mobileNum : String, password : String){
//        if (FirebaseAuth.getInstance().currentUser != null )
//            FirebaseAuth.getInstance().signOut()
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    AppVNOApplication.getInstance().tempUser = user
//                    MyFirebaseDatabase.startListening(user.id)
//                    login_group.isVisible(true)
//                    loginRegister_progressBar.isVisible(false)
//                    // Start new Activity
//                    startActivity(intentFor<HomeActivity>().newTask().clearTask())
//                    finish()
//                }
//            }
//            .addOnFailureListener {
//                toast("${it.message}")
//                login_group.isVisible(true)
//                loginRegister_progressBar.isVisible(false)
//            }
//    }



}