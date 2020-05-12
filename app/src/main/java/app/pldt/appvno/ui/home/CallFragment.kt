package app.pldt.appvno.ui.home


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration

import app.pldt.appvno.R
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.call.CallDetailActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.android.synthetic.main.recycler_call_contact_item.view.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */


class CallFragment : Fragment() {


    val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var userRef  : DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userRef = FirebaseDatabase.getInstance().reference.child("users")
        userRef.addChildEventListener(childEventListener)
        attachListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        userRef.removeEventListener(childEventListener)
    }

    val childEventListener  = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {}
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {}
        override fun onChildRemoved(p0: DataSnapshot) {}

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            val tempUser = p0.getValue(TempUser::class.java)


            tempUser?.let {
                if (it.id  != FirebaseAuth.getInstance().uid){
                    adapter.add(CallContact(it))
                    callFragment_recycler_contact.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                    adapter.setOnItemClickListener { data, view ->
                        val row = (data as CallContact).user
                        Log.d("Test" , (row.email))
                        activity?.toast(row.email)

                        val string = "Do you want to call" + row.email + "?"
                        AlertDialog.Builder(context)
                            .setTitle("Information")
                            .setPositiveButton("Call") { _, _ ->

                                makecallToUser()
                                activity?.startActivity<CallDetailActivity>()
                                // Todo - create node for calling for both user and status to  (Calling,Ringing)
                               // activity?.toast(row.email)
//                            // activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)

                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.cancel()
                            }
                            .setCancelable(true)
                            .setMessage(string)
                            .show()
                    }
                }
                callFragment_recycler_contact.adapter = adapter
            }
        }
    }

    private fun makecallToUser() {

    }

    private fun attachListener() {
    }


    companion object {
        fun newInstance() = CallFragment()
    }
}

class CallContact (val user: TempUser ): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_call_contact_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recyclerCall_tv_name.text = user.email
        viewHolder.itemView.recyclerCall_tv_latest.text = user.number

    }
}


