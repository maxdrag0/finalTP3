package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.holders.OffersHolder

class OffersAdapter(
    private val offers: List<Offer>,
    private val onOfferClick: (Offer) -> Unit
) : RecyclerView.Adapter<OffersHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer, parent, false)

        return OffersHolder(view, onOfferClick)

    }

    override fun onBindViewHolder(holder: OffersHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offers.size
    }
}
