package ar.edu.ort.tp3.finaltp3.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.OffersAdapter
import ar.edu.ort.tp3.finaltp3.entities.Offer
import java.util.Calendar

class FragmentSearch : Fragment() {
    private lateinit var offersRecyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        offersRecyclerView = view.findViewById(R.id.rv_offers)
        offersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(getOffers()) {
            findNavController().navigate(R.id.offers)
        }
        offersRecyclerView.adapter = offersAdapter

        val editTextDepartureDate: EditText = view.findViewById(R.id.edit_text_departure_date)
        editTextDepartureDate.setOnClickListener {
            showDatePickerDialog(editTextDepartureDate)
        }

        val spinnerPassengers: Spinner = view.findViewById(R.id.spinner_passengers)
        val spinnerClass: Spinner = view.findViewById(R.id.spinner_class)

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Search Flight"

        val radioButtonOneWay: RadioButton = view.findViewById(R.id.radio_button_one_way)
        radioButtonOneWay.isChecked = true
    }

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

    private fun getOffers(): List<Offer> {
        return listOf(
            Offer("20% discount", "Mastercard", "Limited time offer!", R.drawable.mastercard_image),
            Offer("25% discount", "Visa", "Limited time offer!", R.drawable.visa_image)
        )
    }
}