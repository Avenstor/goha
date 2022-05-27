package com.discord.bot.weather.dto;

import java.util.List;

public class CurrentWeather {

    private CurrentWeatherDetails current;

    public CurrentWeatherDetails getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeatherDetails current) {
        this.current = current;
    }
}
