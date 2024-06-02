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
import ar.edu.ort.tp3.finaltp3.holders.TrendingDestinationsHolder

class TrendingDestinationsAdapter(private val destinations: List<Destination>,
                                  private val onDestinationClick: (Destination) -> Unit
) : RecyclerView.Adapter<TrendingDestinationsHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingDestinationsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return TrendingDestinationsHolder(view, onDestinationClick)
    }

    override fun onBindViewHolder(holder: TrendingDestinationsHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }
}
