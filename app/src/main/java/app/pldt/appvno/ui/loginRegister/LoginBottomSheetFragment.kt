package app.pldt.appvno.ui.loginRegister

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.pldt.appvno.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_login.*

class LoginBottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var fragmentView : View? = null

    private var mListener: ItemClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet_login, container,false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener){
            mListener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
    }

    private fun initView() {
        // View
//        tv_sample_sample.setOnClickListener {
//            mListener?.onItemClick("Clicked")
//        }
    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(v: View?) {
        Log.d("Test", "Click")
    }

    companion object {
        val TAG = "ActionBottomDialog"

        // TODO: Customize parameters
        fun newInstance(): LoginBottomSheetFragment =
            LoginBottomSheetFragment()
    }

    interface ItemClickListener {
        fun onItemClick(item: String?)
    }


}