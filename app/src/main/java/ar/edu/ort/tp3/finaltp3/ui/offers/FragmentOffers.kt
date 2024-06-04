package ar.edu.ort.tp3.finaltp3.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.OffersFragmentAdapter
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity

class FragmentOffers : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var offersAdapter: OffersFragmentAdapter
    private lateinit var offersList: List<Offer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = ""
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Offers"
        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderToolbar()
        }
        if (activity is MainActivity) {
            (activity as MainActivity?)?.mostrarBottom()
        }

        val view1 = inflater.inflate(R.layout.item_offer_fragment, container, false)
        val heartButton: ImageView? = view1.findViewById<ImageView>(R.id.heart_button)
        var isFavorite = false

        heartButton?.setOnClickListener {
            if (isFavorite) {
                heartButton.setImageResource(R.drawable.img_6)
            } else {
                heartButton.setImageResource(R.drawable.img_5)
            }
            isFavorite = !isFavorite
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_offers)


        offersList = listOf(
            Offer("Get 20% discount with your Master credit cards", "", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount with your Visa credit or debit cards", "", "Limited time offer!", R.drawable.visa_image)
        )
        offersAdapter = OffersFragmentAdapter(offersList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = offersAdapter

    }

}
