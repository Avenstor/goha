import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class Weather {

    public String checkWeather(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=0&lon=0&callback=test&id=2172797&lang=pl&units=metric&mode=xml"))
                .header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c23f6752d8msh8eda1a86d7e3a25p146f4bjsnf18a9e5e700f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        String re = "";
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            re = response.body();
        } catch (InterruptedException e) {e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        return re;
    }

}
