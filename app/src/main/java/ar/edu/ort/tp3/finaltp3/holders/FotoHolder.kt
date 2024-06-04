package ar.edu.ort.tp3.finaltp3.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R

class FotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView = itemView.findViewById(R.id.image_view)
}
