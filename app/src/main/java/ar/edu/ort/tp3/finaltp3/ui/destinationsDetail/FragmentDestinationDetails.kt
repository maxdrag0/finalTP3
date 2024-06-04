package ar.edu.ort.tp3.finaltp3.ui.destinationsDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.adapters.FotoAdapter
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity
import java.util.Arrays


class FragmentDestinationDetails : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = ""
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""

        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderToolbar()
        }
        if (activity is MainActivity) {
            (activity as MainActivity?)?.esconderBottom()
        }


        val view = inflater.inflate(R.layout.fragment_travel_details, container, false)

        val heartButton: ImageView? = view.findViewById<ImageView>(R.id.heart_button)
        var isFavorite = false

        heartButton?.setOnClickListener {
            if (isFavorite) {
                heartButton.setImageResource(R.drawable.img_6) // Imagen de corazón no favorito
            } else {
                heartButton.setImageResource(R.drawable.img_5) // Imagen de corazón favorito
            }
            isFavorite = !isFavorite
        }
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val imageList = Arrays.asList<Int>(R.drawable.photo, R.drawable.photo2, R.drawable.photo3)
        val adapter: FotoAdapter? = context?.let { FotoAdapter(it, imageList) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val airline = arguments?.getString("airline")
        val duration = arguments?.getString("duration")
        val departureAirport = arguments?.getString("departureAirport")
        val departureAirportId = arguments?.getString("departureAirportId")
        val arrivalAirport = arguments?.getString("arrivalAirport")
        val arrivalAirportId = arguments?.getString("arrivalAirportId")
        val travelClass = arguments?.getString("travelClass")
        val price = arguments?.getString("price")
        val airlineLogo = arguments?.getString("airlineLogo")


        if(arrivalAirport == null) {
            val destinationName = arguments?.getString("destination_name")
            // Usa destinationName para mostrarlo en tu FragmentDestinationDetails
            val destinationTextView: TextView = view.findViewById(R.id.text_boracay)
            destinationTextView.text = destinationName

            val destinationPais1 = arguments?.getString("destination_country")
            val destinationPais: TextView = view.findViewById(R.id.text_philippines)
            destinationPais.text = destinationPais1

            val destinationFoto1 = arguments?.getInt("destination_image")
            val destinationFoto: ImageView = view.findViewById(R.id.background_image)
            if (destinationFoto1 != null) {
                destinationFoto.setImageResource(destinationFoto1)
            }

            view.findViewById<TextView>(R.id.text_price).text = "$350"
        }
        else {
            view.findViewById<TextView>(R.id.text_boracay).text = arrivalAirport
            view.findViewById<TextView>(R.id.text_philippines).text = arrivalAirportId
            view.findViewById<TextView>(R.id.text_price).text = "$" + price

        }



        return view

    }
}