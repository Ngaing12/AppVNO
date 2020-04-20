package app.pldt.appvno.profile

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.StringRes
import app.pldt.appvno.R
import app.pldt.appvno.termsCondition.TermsConditionActivity
import kotlinx.android.synthetic.main.activity_input_profile.*
import kotlinx.android.synthetic.main.app_bar_with_back.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import permissions.dispatcher.*
import java.util.*

@RuntimePermissions
class InputProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_profile)

        setupToolbar()
        attachListener()

    }

    private fun setupToolbar() {
        toolbarTitle.text = resources.getText(R.string.complete_information)
        toolbarBack.setOnClickListener { finish() }
    }

    private fun attachListener() {
        inputProfile_btn_continue.setOnClickListener{
            requestPermissionWithPermissionCheck()
        }

        inputProfile_tv_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                inputProfile_tv_date.text = ("$dayOfMonth/$monthOfYear/$year")
            }, year, month, day)

            dpd.show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        onRequestPermissionsResult(requestCode, grantResults)
    }


    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALL_LOG,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
    fun requestPermission() {
        // Proceed to next Activity
        startActivity<TermsConditionActivity>()
    }


    @OnPermissionDenied(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALL_LOG,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        toast("You need to allow all Permission to proceed")
    }

    @OnShowRationale(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALL_LOG,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForContacts(request: PermissionRequest) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.temp_permission_contacts_rationale, request)
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALL_LOG,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsNeverAskAgain() {
        showSettingsDialog()
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


    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                openSettings()
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()

    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.setData(uri)
        startActivityForResult(intent, 101)
    }
}
