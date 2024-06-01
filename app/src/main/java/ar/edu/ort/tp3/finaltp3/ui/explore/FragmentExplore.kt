package ar.edu.ort.tp3.finaltp3.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.OffersAdapter
import ar.edu.ort.tp3.finaltp3.adapters.TrendingDestinationsAdapter
import ar.edu.ort.tp3.finaltp3.entities.Destination
import ar.edu.ort.tp3.finaltp3.entities.Offer

class FragmentExplore : Fragment() {

    private lateinit var trendingDestinationsRecyclerView: RecyclerView
    private lateinit var trendingDestinationsAdapter: TrendingDestinationsAdapter
    private lateinit var offersRecyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        trendingDestinationsRecyclerView = view.findViewById(R.id.rv_trending_destinations)
        trendingDestinationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        trendingDestinationsAdapter = TrendingDestinationsAdapter(getTrendingDestinations())
        trendingDestinationsRecyclerView.adapter = trendingDestinationsAdapter

        offersRecyclerView = view.findViewById(R.id.rv_offers)
        offersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(getOffers()) {
            findNavController().navigate(R.id.offers)
        }
        offersRecyclerView.adapter = offersAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Establecer el título de la actividad en una cadena vacía
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
    }
    private fun getTrendingDestinations(): List<Destination> {
        return listOf(
            Destination("Boracay", "Philippines", "5D4N", R.drawable.boracay_image),
            Destination("Baros", "Maldives", "7D6N", R.drawable.baros_image),
            Destination("Bali", "Indonesia", "3D2N", R.drawable.bali_image),
            Destination("Palawan", "Philippines", "3D2N", R.drawable.palawan_image)
        )
    }

    private fun getOffers(): List<Offer> {
        return listOf(
            Offer("20% discount", "Mastercard", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount", "Visa", "Limited time offer!", R.drawable.visa_image)
        )
    }

}
