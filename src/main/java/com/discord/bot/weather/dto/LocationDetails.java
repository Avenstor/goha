package com.discord.bot.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = { "state" })
public class LocationDetails {
    private @Getter @Setter long id;
    private @Getter @Setter String name;
    private @Getter @Setter String country;
    private @Getter @Setter String timezone;
    private @Getter @Setter String adminArea;
    private @Getter @Setter double lon;
    private @Getter @Setter double lat;
}
