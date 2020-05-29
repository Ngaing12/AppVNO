package app.pldt.appvno.ui.getStarted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.pldt.appvno.R
import app.pldt.appvno.model.Onboarding
import kotlinx.android.synthetic.main.recycler_onboarding.view.*


class OnboardingAdapter(private  val onBoardingList: List<Onboarding>) :
    RecyclerView.Adapter<OnboardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.tv_onboarding_title)
        private val textDescription = view.findViewById<TextView>(R.id.tv_onboarding_description)
        private val imageIcon = view.findViewById<ImageView>(R.id.img_onboarding_image)

        fun bind (onBoarding : Onboarding) {
            textTitle.text = onBoarding.title
            textDescription.text = onBoarding.description
            imageIcon.setImageResource(onBoarding.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
      return OnBoardingViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.recycler_onboarding,
              parent,
              false
          ))
    }

    override fun getItemCount(): Int {
        return onBoardingList.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingList[position])
    }
}


