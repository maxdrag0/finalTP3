package ar.edu.ort.tp3.finaltp3.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Offer

class OffersFragmentHolder(itemView: View
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
            val heartButton: ImageView = itemView.findViewById(R.id.heart_button)
            var isFavorite = false

            heartButton.setOnClickListener {
                if (isFavorite) {
                    heartButton.setImageResource(R.drawable.img_6)
                } else {
                    heartButton.setImageResource(R.drawable.img_5)
                }
                isFavorite = !isFavorite
            }
        }
    }



