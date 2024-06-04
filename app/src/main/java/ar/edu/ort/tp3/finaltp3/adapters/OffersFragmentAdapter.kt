package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.holders.OffersFragmentHolder
import ar.edu.ort.tp3.finaltp3.holders.OffersHolder

class OffersFragmentAdapter(
    private val offers: List<Offer>

) : RecyclerView.Adapter<OffersFragmentHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersFragmentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer_fragment, parent, false)

        return OffersFragmentHolder(view)

    }

    override fun onBindViewHolder(holder: OffersFragmentHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offers.size
    }
}
