package ar.edu.ort.tp3.finaltp3.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


data class ApiResponse(
    val best_flights: List<BestFlight>
)

data class BestFlight(
    val flights: List<Flight>,
    val price: Double,
    val airline_logo: String
)

data class Flight(
    val departure_airport: Airport,
    val arrival_airport: Airport,
    val duration: Int,
    val airline: String,
    val travel_class: String,
    val airline_logo: String
)

data class Airport(
    val name: String,
    val id: String,
    val time: String
)

interface ApiService {
    @GET
    suspend fun getVuelos(@Url url: String): ApiResponse
}


object RetrofitClient {
    fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
