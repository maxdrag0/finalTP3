package ar.edu.ort.tp3.finaltp3.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.OffersAdapter
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity

class FragmentOffers : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var offersList: List<Offer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = ""

        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderToolbar()
        }
        if (activity is MainActivity) {
            (activity as MainActivity?)?.mostrarBottom()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_offers)
        offersList = listOf(
            Offer("20% discount", "20% discount for mastercard users", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount", "25% discount with your Visa credit cards", "Limited time offer!", R.drawable.visa_image)
        )
        offersAdapter = OffersAdapter(offersList) { offer ->
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = offersAdapter

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Offers"
    }

}
