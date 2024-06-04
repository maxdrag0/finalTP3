package ar.edu.ort.tp3.finaltp3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.holders.FotoHolder


class FotoAdapter(private val context: Context, private val imageList: List<Int>) :
    RecyclerView.Adapter<FotoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_foto, parent, false)
        return FotoHolder(view)
    }

    override fun onBindViewHolder(holder: FotoHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}
