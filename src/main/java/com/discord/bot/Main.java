package com.discord.bot;

import com.discord.bot.alexa.Alexa;
import com.discord.bot.blackjack.BlackjackDiscordListener;
import com.discord.bot.blackjack.languagepack.EnglishMessageService;
import com.discord.bot.blackjack.languagepack.MessageService;
import com.discord.bot.blackjack.languagepack.PolishMessageService;
import com.discord.bot.blackjack.languagepack.SupportedLanguages;
import com.discord.bot.goha.GohaListener;
import net.dv8tion.jda.api.JDABuilder;
import com.discord.bot.weather.WeatherListener;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {

        SupportedLanguages lang = SupportedLanguages.EN;
        MessageService msgService = languageChoice(lang);

        JDABuilder jdaBuilder = JDABuilder.createDefault(System.getenv("discord_token"));
            jdaBuilder.addEventListeners(new GohaListener(), new Alexa(), new WeatherListener(), new BlackjackDiscordListener(msgService));
            try{
                jdaBuilder.build();
            } catch (LoginException e){
                e.printStackTrace();
            }



        // LA market api test
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://www.lostarkmarket.online/api/export-market-live/Europe%20Central?category=Enhancement%20Material&subcategory=Honing%20Materials"))
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

    }

    private static MessageService languageChoice(SupportedLanguages lang) {
        if (lang == SupportedLanguages.PL) {
            return new PolishMessageService();
        } else {
            return new EnglishMessageService();
        }
    }

}
