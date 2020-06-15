package app.pldt.appvno.ui.notification

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_reset_password_last.*
import org.jetbrains.anko.toast


class NotificationFragment : Fragment() {

    lateinit var adapter:  NotificationItemAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.text = "Notifications"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.visibility = View.GONE
        setupButtons()

        TransitionManager.beginDelayedTransition(notification_layout)
        rv_notification.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context!!)
        rv_notification.layoutManager = layoutManager

        dummyData()
    }

    private fun dummyData() {
        val itemList = ArrayList<NotificationItem>()
        var i = 0
        while (i < 10) {
            itemList.add(NotificationItem(0,"Pie $i", "11/11/1111", "Sup"))
            i++
        }
        adapter = NotificationItemAdapter(context!!, itemList)
        rv_notification.adapter = adapter


//        val myCallback = object: SampleHelper() {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean = false
//
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
//                                  direction: Int) {
//
////                if (direction == ItemTouchHelper.RIGHT) {
////                    adapter.hideLayout(viewHolder.adapterPosition)
////                } else if (direction == ItemTouchHelper.LEFT) {
////                    adapter.showLayout(viewHolder.adapterPosition)
////                }
////                // More code here
//                adapter.notifyItemChanged(viewHolder.adapterPosition)
//
//            }
//        }
//
//
//
//
//        var myHelper = ItemTouchHelper(myCallback).attachToRecyclerView(rv_notification)
//
//


//
//
//        var v : RecyclerView.ViewHolder? = null
//        val swipe = object : MySwipeHelper(context!!, rv_notification,200) {
//            override fun instantiateMyButton(
//                viewHolder: RecyclerView.ViewHolder,
//                buffer: MutableList<CustomButton>
//            ) {
//                buffer.add(
//                    CustomButton(context!!,
//                        "Delete",
//                        1,
//                        0,
//                        Color.parseColor("#00FF3c30"),
//                        object : CustomButtonListener {
//                            override fun onClick(pos: Int) {
//                                activity?.toast("$pos deleted")
//                            }
//                        }
//                ))
//                buffer.add(
//                    CustomButton(context!!,
//                        "Update",
//                        1,
//                        R.drawable.ic_password_eye,
//                        Color.parseColor("#FF3c30"),
//                        object : CustomButtonListener {
//                            override fun onClick(pos: Int) {
//                                activity?.toast("$pos deleted")
//                            }
//                        }
//                    ))
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//
////                if (direction == ItemTouchHelper.RIGHT) {
////                    adapter.hideLayout(viewHolder.adapterPosition)
////                } else if (direction == ItemTouchHelper.RIGHT) {
////                    adapter.showLayout(viewHolder.adapterPosition)
////                }
//
//                super.onSwiped(viewHolder, direction)
//            }
//
//        }

    }


    private fun setupButtons() {}

    companion object {
        fun newInstance() = NotificationFragment()
    }
}



abstract class SampleHelper :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
    }
}