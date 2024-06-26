package ar.edu.ort.tp3.finaltp3.ui.search

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.OffersAdapter
import ar.edu.ort.tp3.finaltp3.entities.Offer
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class FragmentSearch : Fragment() {
    private lateinit var offersRecyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter

    private lateinit var departureLocation: AutoCompleteTextView
    private lateinit var arrivalLocation: AutoCompleteTextView
    private lateinit var departureDate: EditText
    private lateinit var passengers: Spinner
    private lateinit var flightClass: Spinner

    private val destinations = listOf(
        "New York",
        "Los Angeles",
        "Chicago",
        "Houston",
        "Miami",
        "París",
        "Roma",
        "Londres",
        "Barcelona",
        "Bangkok",
        "Dubai",
        "Singapur",
        "Tokio",
        "Ciudad del Cabo",
        "Estambul",
        "Sydney",
        "Beijing",
        "Nueva Delhi",
        "Bali",
        "Islas Maldivas",
        "Cancún",
        "Río de Janeiro",
        "Las Vegas",
        "Orlando",
        "Buenos Aires"
    )

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = ""
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Search Flight"
        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderToolbar()
        }
        if (activity is MainActivity) {
            (activity as MainActivity?)?.mostrarBottom()
        }

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        offersRecyclerView = view.findViewById(R.id.rv_offers)
        offersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(getOffers()) {
            findNavController().navigate(R.id.offers)
        }
        offersRecyclerView.adapter = offersAdapter

        val editTextDepartureDate: EditText = view.findViewById(R.id.departureDate)
        editTextDepartureDate.setOnClickListener {
            showDatePickerDialog(editTextDepartureDate)
        }

        val spinnerPassengers: Spinner = view.findViewById(R.id.passengers)
        val spinnerClass: Spinner = view.findViewById(R.id.flightClass)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.passengers_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPassengers.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.class_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerClass.adapter = adapter
        }

        // Autocomplete setup for departure location
        departureLocation = view.findViewById(R.id.departureLocation)
        val departureAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, destinations)
        departureLocation.setAdapter(departureAdapter)

        departureLocation.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val input = departureLocation.text.toString()
                if (!destinations.contains(input)) {
                    departureLocation.error = "Lugar no es correcto"
                }
            }
        }

        // Autocomplete setup for arrival location
        arrivalLocation = view.findViewById(R.id.arrivalLocation)
        val arrivalAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, destinations)
        arrivalLocation.setAdapter(arrivalAdapter)

        arrivalLocation.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val input = arrivalLocation.text.toString()
                if (!destinations.contains(input)) {
                    arrivalLocation.error = "Lugar no es correcto"
                } else if (input == departureLocation.text.toString()) {
                    arrivalLocation.error = "Arrival location cannot be the same as departure location"
                }
            }
        }

        // PARAMETROS PARA SEARCH RESULTS
        val buttonSearch: Button = view.findViewById(R.id.button_search)

        departureDate = view.findViewById(R.id.departureDate)
        passengers = view.findViewById(R.id.passengers)
        flightClass = view.findViewById(R.id.flightClass)

        buttonSearch.setOnClickListener {
            val departureLocationValue = departureLocation.text.toString()
            val arrivalLocationValue = arrivalLocation.text.toString()
            val departureDateValue = departureDate.text.toString()
            val passengersValue = passengers.selectedItem.toString()
            val flightClassValue = flightClass.selectedItem.toString()

            if (departureLocationValue.isEmpty() || arrivalLocationValue.isEmpty() || departureDateValue.isEmpty()) {
                Toast.makeText(requireContext(), "Please complete all fields", Toast.LENGTH_SHORT).show()
            } else if (!destinations.contains(departureLocationValue) ||
                !destinations.contains(arrivalLocationValue)) {
                Toast.makeText(requireContext(), "Departure or Arrival incorrect", Toast.LENGTH_SHORT).show()
            } else if (departureLocation.text.toString() == arrivalLocation.text.toString()) {
                Toast.makeText(requireContext(), "Departure and arrival cannot be the same", Toast.LENGTH_SHORT).show()
            }
            else {
                val bundle = Bundle().apply {
                    putString("departureLocation", departureLocationValue)
                    putString("arrivalLocation", arrivalLocationValue)
                    putString("departureDate", departureDateValue)
                    putString("passengers", passengersValue)
                    putString("flightClass", flightClassValue)
                    putString("baseUrl", "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/")
                    putString("queryPath", "search?engine=google_flights&api_key=123")
                }

                findNavController().navigate(R.id.action_search_to_searchResults, bundle)
            }
        }

        return view
    }

    // INICIA CON ONE WAY SIEMPRE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Search Flight"

        val buttonOneWay: MaterialButton = view.findViewById(R.id.button_one_way)
        buttonOneWay.isChecked = true
    }

    // ELECCION DE FECHA
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }

    // OBTENER OFERTAS
    private fun getOffers(): List<Offer> {
        return listOf(
            Offer("20% discount", "Mastercard", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount", "Visa", "Limited time offer!", R.drawable.visa_image)
        )
    }
}

