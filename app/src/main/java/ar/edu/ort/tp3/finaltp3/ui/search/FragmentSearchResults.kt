package ar.edu.ort.tp3.finaltp3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.R.id
import ar.edu.ort.tp3.finaltp3.adapters.VuelosAdapter
import ar.edu.ort.tp3.finaltp3.entities.Vuelo
import ar.edu.ort.tp3.finaltp3.services.ApiService
import ar.edu.ort.tp3.finaltp3.services.RetrofitClient
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity
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

        val navController = findNavController()

        val departureLocation = arguments?.getString("departureLocation")
        val arrivalLocation = arguments?.getString("arrivalLocation")
        val departureDate = arguments?.getString("departureDate")
        val returnDate = arguments?.getString("returnDate")
        val passengers = arguments?.getString("passengers")
        val flightClass = arguments?.getString("flightClass")
        val baseUrl = arguments?.getString("baseUrl")
        val queryPath = arguments?.getString("queryPath")

        // Prueba de parámetros

        val fecha = view.findViewById<TextView>(R.id.tvDepartureDate)
        fecha.text = departureDate

        val from = view.findViewById<TextView>(R.id.tvFrom)
        from.text = departureLocation

        val to = view.findViewById<TextView>(R.id.tvTo)
        to.text = arrivalLocation


        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderToolbar()
        }
        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderBottom()
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        vuelosAdapter = VuelosAdapter(listOf(), navController)
        recyclerView.adapter = vuelosAdapter

        fetchVuelos(baseUrl, queryPath, departureLocation, arrivalLocation, departureDate, returnDate, passengers, flightClass)
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        return view
    }

    private fun fetchVuelos(
        baseUrl: String?,
        queryPath: String?,
        departureLocation: String?,
        arrivalLocation: String?,
        departureDate: String?,
        returnDate: String?,
        passengers: String?,
        flightClass: String?
    ) {
        if (baseUrl == null || queryPath == null) {
            // Manejar el caso en que falten los parámetros
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retrofit = RetrofitClient.getRetrofit(baseUrl)
                val apiService = retrofit.create(ApiService::class.java)


                val url = "$queryPath&departure_id=$departureLocation&arrival_id=$arrivalLocation&outbound_date=$departureDate&return_date=$returnDate&passengers=$passengers&class=$flightClass"

                val response = apiService.getVuelos(url)
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
