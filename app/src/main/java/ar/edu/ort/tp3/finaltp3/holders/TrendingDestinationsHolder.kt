package ar.edu.ort.tp3.finaltp3.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Destination

 class TrendingDestinationsHolder(itemView: View,  private val onDestinationClick: (Destination) -> Unit) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.destination_name)
    val countryTextView: TextView = itemView.findViewById(R.id.destination_country)
    val durationTextView: TextView = itemView.findViewById(R.id.destination_duration)
    val imageView: ImageView = itemView.findViewById(R.id.destination_image)

    fun bind(destination: Destination) {
        nameTextView.text = destination.name
        countryTextView.text = destination.country
        durationTextView.text = destination.duration
        imageView.setImageResource(destination.imageResource)
        itemView.setOnClickListener { onDestinationClick(destination) }
    }
}