package com.discord.bot.weather.dto;

import lombok.Getter;
import lombok.Setter;

public class CurrentWeatherDetails {

    private @Getter @Setter String time;
    private @Getter @Setter String symbol;
    private @Getter @Setter String symbolPhrase;
    private @Getter @Setter double temperature;
    private @Getter @Setter double feelsLikeTemp;
    private @Getter @Setter double relHumidity;
    private @Getter @Setter double dewPoint;
    private @Getter @Setter double windSpeed;
    private @Getter @Setter double windDir;
    private @Getter @Setter String windDirString;
    private @Getter @Setter double windGust;
    private @Getter @Setter double precipProb;
    private @Getter @Setter double precipRate;
    private @Getter @Setter double cloudiness;
    private @Getter @Setter double thunderProb;
    private @Getter @Setter double uvIndex;
    private @Getter @Setter double pressure;
    private @Getter @Setter double visibility;

}
