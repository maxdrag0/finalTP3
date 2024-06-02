package ar.edu.ort.tp3.finaltp3.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.adapters.OffersAdapter
class OffersHolder(itemView: View,
                   private val onOfferClick: (Offer) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    val discountTextView: TextView = itemView.findViewById(R.id.offer_discount)
    val cardTypeTextView: TextView = itemView.findViewById(R.id.offer_card_type)
    val descriptionTextView: TextView = itemView.findViewById(R.id.offer_description)
    val imageView: ImageView = itemView.findViewById(R.id.offer_image)

    fun bind(offer: Offer) {
        discountTextView.text = offer.discount
        cardTypeTextView.text = offer.cardType
        descriptionTextView.text = offer.description
        imageView.setImageResource(offer.imageResource)
        itemView.setOnClickListener { onOfferClick(offer) }
    }

}