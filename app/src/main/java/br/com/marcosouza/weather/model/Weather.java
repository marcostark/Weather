package br.com.marcosouza.weather.model;


import com.google.gson.annotations.SerializedName;

class Weather {

    @SerializedName("id")
    public int id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}


