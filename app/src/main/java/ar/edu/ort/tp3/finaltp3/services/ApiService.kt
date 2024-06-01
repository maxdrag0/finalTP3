package ar.edu.ort.tp3.finaltp3.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


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
    val time: String
)
interface ApiService {
    @GET("search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10")
    suspend fun getVuelos(): ApiResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"

    val retrofitService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
