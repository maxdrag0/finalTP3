package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Destination

class TrendingDestinationsAdapter(private val destinations: List<Destination>) :
    RecyclerView.Adapter<TrendingDestinationsAdapter.DestinationViewHolder>() {

    inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.destination_name)
        val countryTextView: TextView = itemView.findViewById(R.id.destination_country)
        val durationTextView: TextView = itemView.findViewById(R.id.destination_duration)
        val imageView: ImageView = itemView.findViewById(R.id.destination_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return DestinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        holder.nameTextView.text = destination.name
        holder.countryTextView.text = destination.country
        holder.durationTextView.text = destination.duration
        holder.imageView.setImageResource(destination.imageResource)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }
}
