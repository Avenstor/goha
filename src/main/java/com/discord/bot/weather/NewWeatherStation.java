package com.discord.bot.weather;

import com.discord.bot.weather.dto.CurrentWeather;
import com.discord.bot.weather.dto.Locations;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NewWeatherStation {

    public String checkWeather(String city) {

        try {
            String cityId = getCityId(city);
            if(cityId.equals("0")){
                return "Data unavailable.";
            } else {
                CurrentWeather currentWeather = getCurrentWeather(cityId);
                String forecast = "Weather data from: ";
                StringBuilder sB = new StringBuilder(forecast);
                String[] dateAndTime = currentWeather.getCurrent().getTime().split("T");
                sB.append(dateAndTime[0]).append(" ").append(dateAndTime[1]).append("\n");
                sB.append("Weather status: ").append(currentWeather.getCurrent().getSymbolPhrase()).append("\n");
                sB.append("Temperature: ").append(currentWeather.getCurrent().getTemperature()).append(" C\n");
                sB.append("Feels-like temperature: ").append(currentWeather.getCurrent().getFeelsLikeTemp()).append(" C\n");
                sB.append("Relative humidity: ").append(currentWeather.getCurrent().getRelHumidity()).append("%\n");
                sB.append("Wind speed: ").append(currentWeather.getCurrent().getWindSpeed()).append(" km/h\n");
                sB.append("Probability of rain: ").append(currentWeather.getCurrent().getPrecipProb()).append("%\n");
                sB.append("Cloudiness: ").append(currentWeather.getCurrent().getCloudiness()).append("%\n");
                sB.append("Pressure: ").append(currentWeather.getCurrent().getPressure()).append(" hPa\n");
                sB.append("Visibility: ").append(currentWeather.getCurrent().getVisibility()).append(" m");
                forecast = sB.toString();
                return forecast;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private String getCityId(String city) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://foreca-weather.p.rapidapi.com/location/search/" + city))
                .header("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c23f6752d8msh8eda1a86d7e3a25p146f4bjsnf18a9e5e700f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String cityId = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            Locations locations = objectMapper.readValue(cityId, Locations.class);
            return String.valueOf(locations.getLocations().get(0).getId());
        } catch (IOException | InterruptedException | IndexOutOfBoundsException e) {
            return "0";
        }

    }

    private CurrentWeather getCurrentWeather(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://foreca-weather.p.rapidapi.com/current/" + id + "?tempunit=C&windunit=KMH&tz=Europe%2FLondon&lang=en")) //103080526 zawiercie
                .header("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c23f6752d8msh8eda1a86d7e3a25p146f4bjsnf18a9e5e700f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String forecast = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(forecast, CurrentWeather.class);
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

    }

}
