package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Destination
import ar.edu.ort.tp3.finaltp3.entities.Offer

class TrendingDestinationsAdapter(private val destinations: List<Destination>,
                                  private val onDestinationClick: (Destination) -> Unit
) : RecyclerView.Adapter<TrendingDestinationsAdapter.DestinationViewHolder>() {

    inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return DestinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }
}
