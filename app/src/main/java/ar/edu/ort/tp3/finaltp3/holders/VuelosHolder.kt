package ar.edu.ort.tp3.finaltp3.holders

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.entities.Vuelo

class VuelosHolder(itemView: View, private val navController: NavController) : RecyclerView.ViewHolder(itemView) {

   private val viewDetailsButton: Button = itemView.findViewById(R.id.viewDetailsButton)

   val airline: TextView = itemView.findViewById(R.id.airline)
   val duration: TextView = itemView.findViewById(R.id.duration)
   val departureAirport: TextView = itemView.findViewById(R.id.departureAirport)
   val departureAirportId: TextView = itemView.findViewById(R.id.departureAirportId)
   val arrivalAirport: TextView = itemView.findViewById(R.id.arrivalAirport)
   val arrivalAirportId: TextView = itemView.findViewById(R.id.arrivalAirportId)
   val travelClass: TextView = itemView.findViewById(R.id.travelClass)
   val price: TextView = itemView.findViewById(R.id.price)
   val airlineLogo: ImageView = itemView.findViewById(R.id.airlineLogo)

   init {
      viewDetailsButton.setOnClickListener {
         val position = adapterPosition
         if (position != RecyclerView.NO_POSITION) {
            val vuelo = itemView.tag as Vuelo
            val bundle = Bundle().apply {
               putString("airline", vuelo.airline)
               putString("duration", vuelo.duration.toString())
               putString("departureAirport", vuelo.departureAirport)
               putString("departureAirportId", vuelo.departureAirportId)
               putString("arrivalAirport", vuelo.arrivalAirport)
               putString("arrivalAirportId", vuelo.arrivalAirportId)
               putString("travelClass", vuelo.travelClass)
               putString("price", vuelo.price.toString())
               putString("airlineLogo", vuelo.airlineLogo)
            }
            navController.navigate(R.id.action_searchResults_to_fragmentDestinationDetails, bundle)
         }
      }
   }
}
