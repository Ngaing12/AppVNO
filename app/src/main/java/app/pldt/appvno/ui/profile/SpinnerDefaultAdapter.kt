package app.pldt.appvno.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.spinner_freebee.view.*


class SpinnerDefaultAdapter(ctx: Context, data: List<String>) : ArrayAdapter<String>(ctx, 0, data) {
    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val name = getItem(position)
        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.spinner_freebee,
            parent,
            false
        )
        view.tv_data.text = name
        return view
    }
}