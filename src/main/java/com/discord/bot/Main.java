package com.discord.bot;

import com.discord.bot.alexa.Alexa;
import com.discord.bot.blackjack.BlackjackListener;
import com.discord.bot.blackjack.Deck;
import com.discord.bot.goha.GohaListener;
import net.dv8tion.jda.api.JDABuilder;
import com.discord.bot.weather.WeatherListener;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        JDABuilder jdaBuilder = JDABuilder.createDefault("OTc4ODIzNDIwMzY2MTU5OTAy.G_6jRn.SsXeAyelZKNQo-cIPL-ENz0ocjLpdcrWXT2-3I");
            jdaBuilder.addEventListeners(new GohaListener(), new Alexa(), new WeatherListener(), new BlackjackListener());
            try{
                jdaBuilder.build();
            } catch (LoginException e){
                e.printStackTrace();
            }

            //try{
            //    jda.awaitReady();
            //} catch (InterruptedException e){
            //    e.printStackTrace();
            //}
        // LA market api test
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://www.lostarkmarket.online/api/export-market-live/Europe%20Central?category=Enhancement%20Material&subcategory=Honing%20Materials"))
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

    }


}
