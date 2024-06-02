package ar.edu.ort.tp3.finaltp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import com.bumptech.glide.Glide

class VuelosAdapter(private var vuelos: List<Vuelo>) : RecyclerView.Adapter<VuelosAdapter.VueloViewHolder>() {

    inner class VueloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airline: TextView = itemView.findViewById(R.id.airline)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val departureAirport: TextView = itemView.findViewById(R.id.departureAirport)
        val departureAirportId: TextView = itemView.findViewById(R.id.departureAirportId)
        val arrivalAirport: TextView = itemView.findViewById(R.id.arrivalAirport)
        val arrivalAirportId: TextView = itemView.findViewById(R.id.arrivalAirportId)
        val travelClass: TextView = itemView.findViewById(R.id.travelClass)
        val price: TextView = itemView.findViewById(R.id.price)
        val airlineLogo: ImageView = itemView.findViewById(R.id.airlineLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VueloViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vuelo, parent, false)
        return VueloViewHolder(view)
    }

    override fun onBindViewHolder(holder: VueloViewHolder, position: Int) {
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
    }


    override fun getItemCount(): Int = vuelos.size

    fun updateVuelos(newVuelos: List<Vuelo>) {
        vuelos = newVuelos
        notifyDataSetChanged()
    }
}
