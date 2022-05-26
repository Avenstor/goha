package com.discord.bot.weather;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class WeatherStation {

    public String checkWeather(String city, Unit unit) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=" + city + "&units=" + unit.getUnitValue()))
                .header("X-RapidAPI-Host", "community-open-com.discord.bot.weather-map.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c23f6752d8msh8eda1a86d7e3a25p146f4bjsnf18a9e5e700f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (InterruptedException | IOException e) {
            return e.getMessage();
        }
    }

}