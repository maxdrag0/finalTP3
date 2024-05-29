package ar.edu.ort.tp3.finaltp3.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.ui.explore.adapters.OffersAdapter
import ar.edu.ort.tp3.finaltp3.ui.explore.entities.Offer

class FragmentOffers : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var offersList: List<Offer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_offers)
        offersList = listOf(
            Offer("20% discount", "Mastercard", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount", "Visa", "Limited time offer!", R.drawable.visa_image)
        )
        offersAdapter = OffersAdapter(offersList) { offer ->
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = offersAdapter
    }
}
