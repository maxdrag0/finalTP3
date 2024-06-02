package ar.edu.ort.tp3.finaltp3.entities

data class Vuelo(
    val airline: String,
    val duration: Int,
    val departureAirport: String,
    val departureTime: String,
    val arrivalAirport: String,
    val arrivalTime: String,
    val travelClass: String,
    val price: Double,
    val airlineLogo: String
)
