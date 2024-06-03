package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Vuelo
import ar.edu.ort.tp3.finaltp3.holders.VuelosHolder
import com.bumptech.glide.Glide

class VuelosAdapter(private var vuelos: List<Vuelo>, private val navController: NavController) : RecyclerView.Adapter<VuelosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VuelosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vuelo, parent, false)
        return VuelosHolder(view, navController)
    }

    override fun onBindViewHolder(holder: VuelosHolder, position: Int) {
        val vuelo = vuelos[position]
        holder.airline.text = vuelo.airline
        holder.duration.text = "${vuelo.duration} min"
        holder.departureAirport.text = vuelo.departureAirport
        holder.departureAirportId.text = vuelo.departureAirportId
        holder.arrivalAirport.text = vuelo.arrivalAirport
        holder.arrivalAirportId.text = vuelo.arrivalAirportId
        holder.travelClass.text = vuelo.travelClass
        holder.price.text = "$${vuelo.price}"
        Glide.with(holder.itemView.context).load(vuelo.airlineLogo).into(holder.airlineLogo)

        holder.itemView.tag = vuelo
    }

    override fun getItemCount(): Int = vuelos.size

    fun updateVuelos(newVuelos: List<Vuelo>) {
        vuelos = newVuelos
        notifyDataSetChanged()
    }
}
