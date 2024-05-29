package ar.edu.ort.tp3.finaltp3.ui.explore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.ui.explore.entities.Offer

class OffersAdapter(private val offers: List<Offer>) :
    RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val discountTextView: TextView = itemView.findViewById(R.id.offer_discount)
        val cardTypeTextView: TextView = itemView.findViewById(R.id.offer_card_type)
        val descriptionTextView: TextView = itemView.findViewById(R.id.offer_description)
        val imageView: ImageView = itemView.findViewById(R.id.offer_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.discountTextView.text = offer.discount
        holder.cardTypeTextView.text = offer.cardType
        holder.descriptionTextView.text = offer.description
        holder.imageView.setImageResource(offer.imageResource)
    }

    override fun getItemCount(): Int {
        return offers.size
    }
}
