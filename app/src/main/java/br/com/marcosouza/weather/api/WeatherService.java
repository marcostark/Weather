package br.com.marcosouza.weather.api;

import br.com.marcosouza.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    //private static final String URL = "http://api.openweathermap.org/data/2.5";

//        @GET("weather?")
//        Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    @GET("weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q") String city, @Query("APPID") String app_id, @Query("units") String metric);
    }


