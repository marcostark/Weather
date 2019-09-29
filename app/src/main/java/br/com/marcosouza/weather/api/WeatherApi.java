package br.com.marcosouza.weather.api;

import br.com.marcosouza.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q") String city, @Query("APPID") String app_id, @Query("units") String metric);
    }


