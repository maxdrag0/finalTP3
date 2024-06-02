package ar.edu.ort.tp3.finaltp3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.VuelosAdapter
import ar.edu.ort.tp3.finaltp3.entities.Vuelo
import ar.edu.ort.tp3.finaltp3.services.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class FragmentSearchResults : Fragment() {

    private lateinit var vuelosAdapter: VuelosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        vuelosAdapter = VuelosAdapter(listOf())
        recyclerView.adapter = vuelosAdapter

        fetchVuelos()

        return view
    }

    private fun fetchVuelos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.retrofitService.getVuelos()
                val vuelos = response.best_flights.map { bestFlight ->
                    bestFlight.flights.firstOrNull()?.let { flight ->
                        Vuelo(
                            airline = flight.airline,
                            duration = flight.duration,
                            departureAirport = flight.departure_airport.name,
                            departureAirportId = flight.departure_airport.id,
                            arrivalAirport = flight.arrival_airport.name,
                            arrivalAirportId = flight.arrival_airport.id,
                            travelClass = flight.travel_class,
                            price = bestFlight.price,
                            airlineLogo = flight.airline_logo
                        )
                    }
                }.filterNotNull()

                withContext(Dispatchers.Main) {
                    vuelosAdapter.updateVuelos(vuelos)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
