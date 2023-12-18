package com.example.dbtest.weatherAPI

import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.dbtest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private fun findWeather() {
    val apiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService::class.java)

    val call: Call<WeatherResponse> = apiService.getWeatherData("Boston", "f59dbd3ae07ebb65b3538ca10a8c2cb7")

    call.enqueue(object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful && response.body() != null) {
                val weatherData: WeatherResponse = response.body()!!
                // Handle the weather data here
                handleWeatherData(weatherData)
            } else {
                // Handle unsuccessful response
                handleUnsuccessfulResponse()
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            // Handle failure
            handleFailure(t)
        }
    })
}
fun getTemperatureRecommendation(temperature: Double): String {
    return when {
        temperature < 0 -> "It's very cold! Bundle up!"
        temperature in 0.0..10.0 -> "It's quite chilly. Wear a coat!"
        temperature in 10.0..20.0 -> "It's cool. A jacket might be good."
        temperature in 20.0..30.0 -> "It's pleasant. Enjoy the weather!"
        temperature in 30.0..35.0 -> "It's warm. T-shirt weather!"
        temperature > 35.0 -> "It's hot! Find shade!"
        else -> "Temperature information unavailable."
    }
}
private fun handleUnsuccessfulResponse() {
    // Handle cases where the API response was not successful
    // For example, show an error message to the user
}

private fun handleFailure(t: Throwable) {
    // Handle failure in making the API call
    // For example, show an error message or log the error
}
private fun handleWeatherData(weatherData: WeatherResponse) {
    fun getWindDirection(degrees: Double): String {
        val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val index = ((degrees % 360) / 45).toInt()
        return directions[index]
    }
    // Process the weather data
    val weatherDescription = weatherData.weather.firstOrNull()?.description ?: "No description available"
    val kelvintemperature =  Math.ceil(weatherData.main.temp)
    val celsuis = kelvintemperature - 273.15
    val celsuistemperature = Math.ceil( kelvintemperature - 273.15)
    val farenheittemperature =  Math.ceil(celsuistemperature * 9 / 5 + 32)
    // Find the TextViews using the rootView
    val windSpeed = weatherData.wind.speed // Wind speed in meters per second
    val recommendation = getTemperatureRecommendation(celsuis)
    val windSpeedKmh = windSpeed * 3.6 // Conversion factor: 1 m/s = 3.6 km/h
}
@Composable
fun weatherSet(windSpeedKmh:Double, weatherDescription: String,farenheittemperature: String, recommendation:String){

    Column {
        var windSpeed = "Windspeed: ${"%.2f".format(windSpeedKmh)} km/h"
        Text(text = windSpeed)
        Text(text = weatherDescription)
        Text(text = "$farenheittemperature °F")
        Text(text = recommendation)
    }
}
