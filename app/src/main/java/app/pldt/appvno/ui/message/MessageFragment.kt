package app.pldt.appvno.ui.message


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.AppVNOApplication

import app.pldt.appvno.R
import app.pldt.appvno.model.Contact
import app.pldt.appvno.model.TempUser
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.recycler_contact_item.view.*
import org.jetbrains.anko.startActivity

const val CONTACT_INFO = "CONTACT_INFO"

class MessageFragment : Fragment() {


    val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachListener()

        setupDummyData()
    }

    private fun setupDummyData() {



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

        if (AppVNOApplication.getInstance().tempUser?.number == tempUser1.number) {
            adapter.add(MessageContact(tempUser2))
            messageFragment_recycler_contact.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter.setOnItemClickListener { item, view ->
                val row = item as MessageContact

                activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
            }
        }
        else {
            adapter.add(MessageContact(tempUser1))
            messageFragment_recycler_contact.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter.setOnItemClickListener { item, view ->
                val row = item as MessageContact

                activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
            }
        }


        messageFragment_recycler_contact.adapter = adapter
    }

    private fun attachListener() {
//        img_message_messageFragment.setOnClickListener {
//            activity?.startActivity<NewMessageActivity>()
//        }
    }

    companion object {
        fun newInstance() = MessageFragment()
    }
}



class MessageContact (val user: TempUser ): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_contact_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recyclerContact_tv_name.text = user.email
        viewHolder.itemView.recyclerContact_tv_latest.text = user.number
    }
}

