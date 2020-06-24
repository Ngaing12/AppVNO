package app.pldt.appvno.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.R
import app.pldt.appvno.common.Resource
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.Country
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.loginRegister.LoginBottomSheetFragment
import app.pldt.appvno.ui.password.forgotPassword.ForgotPasswordActivity
import app.pldt.appvno.ui.register.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*
import kotlinx.android.synthetic.main.recycler_contact_item.view.*
import kotlinx.android.synthetic.main.recycler_country_code_item.view.*
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModelProviderFactory = LoginViewModelProviderFactory()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(LoginViewModel::class.java)


        viewModel.notificationReport("111", "111111","Clicked", "sdfdsfsdfsdfsdf")

        viewModel.loginResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    toast(response.message ?: "Success")
                    startActivity(intentFor<FreebeeHomeActivity>().newTask().clearTask())
                }
                is Resource.Error -> {
                    toast(response.message ?: "Error")
//                    hideProgressBar()
//                    response.message?.let { message ->
//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                    }
                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
            }
        })


        viewModel.notificationResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    toast(response.data!!.data.attributes.status ?: "Success")
                }
                is Resource.Error -> {
                    toast(response.message ?: "Error")
//                    hideProgressBar()
//                    response.message?.let { message ->
//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                    }
                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
            }
        })



        // Getting Token
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast

                Log.d("FCM", token)
            })

//        FirebaseMessaging.getInstance().subscribeToTopic("AllUser").addOnCompleteListener { task ->
//            var msg = "Subscribed"
//            if (!task.isSuccessful) {
//                msg = "Failed to subscribed"
//            }
//            toast(msg)
//        } .addOnFailureListener {
//            toast("failed")
//        }
//        toast("failed")
        setupBottomSheet()
        setupDummyData()
        setupButtons()

    }

    private fun setupButtons() {
        tv_signUp.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }
        tv_forgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }
        btn_login.setOnClickListener{
//            if (edt_mobileNumber.text.toString() == "") {
//                edt_mobileNumber.setError("This field is required!", null)
//            }
          //
            doLogin()
        }

        edt_countryCode.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        loginBSheet_img_close.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
    }

    private fun doLogin() {
        if (edt_mobileNumber.text.toString().isEmpty() ||
                edt_password.text.toString().isEmpty()){
            toast("Please enter mobile number and password")
            return
        }
        viewModel.loginToFirebase(edt_mobileNumber.text.toString(), edt_password.text.toString())
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)

        bottomSheetBehavior.addBottomSheetCallback(  object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_SETTLING ->{
                        img_blocker.isVisible(true)
                    }
                    else -> {
                        img_blocker.isVisible(false)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset
            }
        })
    }

    private fun setupDummyData() {
        val country = Country(
            "+1",
            "Country of Something"
        )

        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.setOnItemClickListener { _, _ ->
//            val row = item as MessageContact
//
//            activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
        }

        loginBSheet_rv_country.adapter = adapter
    }
}



class CountryCodeItem ( val country: Country): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_country_code_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_code.text = country.code
        viewHolder.itemView.tv_country.text = country.name
    }
}

