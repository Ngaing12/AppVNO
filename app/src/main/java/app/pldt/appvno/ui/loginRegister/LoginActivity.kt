package app.pldt.appvno.ui.loginRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import app.pldt.appvno.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() , LoginBottomSheetFragment.ItemClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_edt_countryCode.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog(){
        val addPhotoBottomDialogFragment: LoginBottomSheetFragment = LoginBottomSheetFragment.newInstance()
        addPhotoBottomDialogFragment.show(
            supportFragmentManager,
            LoginBottomSheetFragment.TAG
        )
    }

    private fun showBottomSheetDialog2(){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_login, null)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onItemClick(item: String?) {
        toast(item!!)
    }

}
