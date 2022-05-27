package com.discord.bot.weather.dto;

import java.util.List;

public class Locations {

    private List<LocationDetails> locations;

    public List<LocationDetails> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDetails> locations) {
        this.locations = locations;
    }
}
