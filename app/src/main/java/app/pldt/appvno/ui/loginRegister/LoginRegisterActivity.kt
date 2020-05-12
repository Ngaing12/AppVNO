package app.pldt.appvno.ui.loginRegister

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import app.pldt.appvno.AppVNOApplication
import com.afollestad.materialdialogs.MaterialDialog
import app.pldt.appvno.R
import app.pldt.appvno.common.LOCATION_ENABLE_REQUEST_CODE
import app.pldt.appvno.location.LocationRequestManager
import app.pldt.appvno.extensions.addCountryCode
import app.pldt.appvno.extensions.formatNumber
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.home.HomeActivity
import app.pldt.appvno.ui.otp.OtpConfirmationActivity
import com.google.android.gms.common.internal.Objects
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.dialog_verify_number.*
import kotlinx.android.synthetic.main.include_login.*
import kotlinx.android.synthetic.main.include_login_fingerprint.*
import kotlinx.android.synthetic.main.include_register.*
import kotlinx.android.synthetic.main.include_register.register_btn_register
import kotlinx.android.synthetic.main.include_register.register_edt_phone_number
import kotlinx.android.synthetic.main.include_register.register_tv_signin
import kotlinx.android.synthetic.main.include_register.register_spinner_location
import org.jetbrains.anko.*
import permissions.dispatcher.*

@RuntimePermissions
class LoginRegisterActivity : AppCompatActivity() {


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        attachListener()
        setupSpinner()
    }


    private fun attachListener() {
        login_img_fingerprint.setOnClickListener {
            loginFingerprint_group.isVisible(true)
        }

        loginFingerprint_img_close.setOnClickListener {
            loginFingerprint_group.isVisible(false)
        }

        login_tv_register.setOnClickListener {
            // TODO - Clear edit Text
            login_group.isVisible(false)
            register_group.isVisible(true)
        }

        register_tv_signin.setOnClickListener {
            // TODO - Clear edit Text
            register_group.isVisible(false)
            login_group.isVisible(true)
        }


        loginFingerprint_img_fingerprint.setOnClickListener {
            // TODO - add Fingerprint auth
          //  startActivity(intentFor<HomeActivity>().newTask().clearTask())
        }

        login_btn_login.setOnClickListener {
            requestPermissionWithPermissionCheck()
        }

        register_btn_register.setOnClickListener {
            // TODO - Check country and api call to check number
            val number = register_edt_phone_number.text.toString()

            if (number.isEmpty() ||
                number.length < 10){
                toast("Invalid number")
                return@setOnClickListener
            }
            openVerifyDialog(number)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOCATION_ENABLE_REQUEST_CODE -> {
                if (LocationRequestManager.checkGPS()){

                    getLocation()
                    // TODO - temp
                } else {
                    toast("Location Service is disabled")
                }
            }
            else -> {
                toast("Location Service is disabled")
            }
        }
    }

    //region Location Permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun requestPermission() {
        if (LocationRequestManager.checkGPS()){
            getLocation()
        } else {
            promptGpsPermission()
        }
    }

    private fun getLocation(){
        if (LocationRequestManager.isServicesOK()){
            // Google service available
            LocationRequestManager.getCurrentLocationViaGoogle()
        }
        else {
            // use Location Manager
            LocationRequestManager.getCurrentLocationViaLocal()
        }
        // TODO - Temp login
        doLogin()
    }

    private fun doLogin() {
        login_group.isVisible(false)
        loginRegister_progressBar.isVisible(true)
        val number  = login_edt_number.text.toString()
        if (number.isEmpty()){
            toast("Please input number")
            return
        }

        when (number){
            phone1 ->{
                loginToFirebase(tempUser1)
            }
            phone2 -> {
                loginToFirebase(tempUser2)
            }
            else -> {
                login_group.isVisible(true)
                loginRegister_progressBar.isVisible(false)
                toast("Incorrect number")

            }
        }
    }


    private fun loginToFirebase(user : TempUser){
        if (FirebaseAuth.getInstance().currentUser != null )
            FirebaseAuth.getInstance().signOut()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    AppVNOApplication.getInstance().tempUser = user
                    startActivity(intentFor<HomeActivity>().newTask().clearTask())
                    login_group.isVisible(true)
                    loginRegister_progressBar.isVisible(false)
                }
            }
            .addOnFailureListener {
                toast("${it.message}")
                login_group.isVisible(true)
                loginRegister_progressBar.isVisible(false)
            }


    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        toast("You need to accept this permission to login")
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForContacts(request: PermissionRequest) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        //showRationaleDialog(R.string.temp_permission_contacts_rationale, request)
        request.proceed()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsNeverAskAgain() {
        showSettingsDialog()
    }
    // endregion

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS" ) { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun showRationaleDialog(@StringRes messageResId: Int, request: PermissionRequest) {

        AlertDialog.Builder(this)
            .setTitle("Permissions")
            .setPositiveButton("Proceed") { _, _ ->  request.proceed() }
            .setNegativeButton("Cancel") { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }

    private fun promptGpsPermission() {
        val confirmDialog = MaterialDialog.Builder(this)
            .title(R.string.use_google_location_service)
            .content(R.string.use_google_location_service_message)
            .cancelable(false)
            .positiveText("Continue")
            .negativeText("Cancel")
            .onPositive { dialog, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(intent, LOCATION_ENABLE_REQUEST_CODE)
                dialog.dismiss()
            }.onNegative { dialog, _ ->
                toast("You need to enable Location Service to continue login.")
                dialog.dismiss()
            }
        confirmDialog.show()
    }

    private fun setupSpinner() {
        // TODO - add data
        register_spinner_location.setItems("Philippines")
        register_spinner_location.setOnItemSelectedListener { view, position, id, item ->
            Log.d("Sample", item.toString())
        }
    }

    private fun openVerifyDialog(number: String){
        val confirmDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_verify_number, false)
            .cancelable(false)
            .build()

        val formattedNumber = number.formatNumber().addCountryCode("63")
        // Set the Origin and Destination data
        confirmDialog.tv_number_d_verify.text = formattedNumber

        // Setup actions of buttons
        confirmDialog.btn_cancel_d_verify.setOnClickListener {  confirmDialog.dismiss()  }
        confirmDialog.btn_continue_d_verify.setOnClickListener {
            startActivity<OtpConfirmationActivity>("CONFIRM_NUMBER" to formattedNumber)
            confirmDialog.dismiss()
        }

        // Make the dialog background transparent
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirmDialog.show()
    }
}
