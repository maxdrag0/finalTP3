package ar.edu.ort.tp3.finaltp3.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R

 class VuelosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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