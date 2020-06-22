package app.pldt.appvno.ui.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import app.pldt.appvno.R
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_freebee_home.*

class FreebeeHomeFragment : Fragment() {


    val images = intArrayOf(R.drawable.ic_coin_bronze, R.drawable.ic_coin_iron, R.drawable.ic_coin_steel, R.drawable.ic_coin_iron)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_freebee_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carousel_view.setImageListener(listener)
        carousel_view.pageCount = images.size

    }

    val listener = ImageListener { position, imageView -> imageView.setImageResource(images[position]) }

    companion object {
        fun newInstance() = FreebeeHomeFragment()
    }
}
