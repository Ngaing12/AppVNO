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

import app.pldt.appvno.R
import app.pldt.appvno.model.Contact
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
        val contact = Contact(
            "yI6MsL4mJkecFXbK6eS0SXJNkHl1",
            "Bad Mark"
        )

        adapter.add(MessageContact(contact))
        messageFragment_recycler_contact.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener { item, view ->
            val row = item as MessageContact

            activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.contact)
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



class MessageContact (val contact: Contact ): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_contact_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recyclerContact_tv_name.text = contact.name
    }
}

