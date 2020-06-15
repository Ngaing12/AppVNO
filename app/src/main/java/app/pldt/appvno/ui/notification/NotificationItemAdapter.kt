package app.pldt.appvno.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import kotlinx.android.synthetic.main.dialog_touch_id.view.*
import kotlinx.android.synthetic.main.recycler_notification_item.view.*

data class NotificationItem(
    var icon : Int = 0,
    var title : String,
    var date : String,
    var content : String,
    var isVisible : Boolean = false
)

class NotificationItemAdapter (internal var context : Context, private var notifList: MutableList<NotificationItem>) :
    RecyclerView.Adapter<NotificationItemAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_notification_item, parent, false)
        return  NotificationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notifList.count()
    }


    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifList[position], position)
    }

    fun clickSample(position: Int){
        notifList[position].isVisible =  !notifList[position].isVisible
        notifyItemChanged(position)
    }

    inner class NotificationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (notif : NotificationItem, position: Int) {
            if (notif.icon != 0) {
                // TODO - Use Glide to load Image
            }

            itemView.tv_title.text = notif.title
            itemView.tv_content.text = notif.content
            itemView.tv_date.text = notif.date

            itemView.spacer.isVisible(notif.isVisible)
            itemView.container.isVisible(notif.isVisible)

            // TODO  - add button listener on delete and view

            itemView.img_notif.setOnClickListener {
                clickSample(position)
            }
        }
    }
}


interface  OnNotificationItemClick {
    fun onClick(position : Int)
}