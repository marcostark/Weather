package br.com.marcosouza.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    private Sys sys;
    private Main main;

    @SerializedName("weather")
    public ArrayList<Weather> weather = new ArrayList<Weather>();

    @SerializedName("name")
    public String name;

    public long getSunrise(){
        return sys.sunrise;
    }

    public long getSunset(){
        return sys.sunset;
    }

    public float getTemp(){ return main.temp; }

    public float getTempMax(){
        return main.temp_max;
    }

    public float getTempMin(){
        return main.temp_min;
    }

    public String getCountry(){ return sys.country;}

    public String getIcon(){
        return weather.get(0).icon;
    }

    public float getHumidity(){ return main.humidity;}

    public String getMain(){
        return weather.get(0).main;
    }

    public String getName(){
        return name;
    }


}
