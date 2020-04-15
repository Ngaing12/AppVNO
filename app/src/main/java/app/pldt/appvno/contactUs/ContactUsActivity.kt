package app.pldt.appvno.contactUs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.app_bar_with_back.*

class ContactUsActivity : AppCompatActivity() {

    val MAX_TEXT_COUNT = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)


        edt_desc_contactUs.setOnClickListener {

        }
            edt_desc_contactUs.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {

                }

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                    scrollview_container_contactUs.post {
                        scrollview_container_contactUs.smoothScrollTo(0, scrollview_container_contactUs.bottom)
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    tv_count_contactUs.text = "${s.length}/$MAX_TEXT_COUNT"
                }
        })
        setupToolbar()

    }

    private fun setupToolbar() {
        toolbarBack.setOnClickListener { onBackPressed() }
        toolbarTitle.text = "Support"
    }
}
