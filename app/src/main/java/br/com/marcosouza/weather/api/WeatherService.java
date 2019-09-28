package br.com.marcosouza.weather.api;

import br.com.marcosouza.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    String APPID = "903a7eab47f20e0bd580b60ee7760f14";

        @GET("weather?")
        Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query(APPID) String app_id);
    }


