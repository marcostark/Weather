package br.com.marcosouza.weather.model;

import com.google.gson.annotations.SerializedName;

class Main {

    @SerializedName("temp")
    public float temp;

    @SerializedName("humidity")
    public float humidity;

    @SerializedName("temp_min")
    public float temp_min;

    @SerializedName("temp_max")
    public float temp_max;
}
