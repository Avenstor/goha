package com.discord.bot.weather.dto;

public class CurrentWeatherDetails {

    private String time;
    private String symbol;
    private String symbolPhrase;
    private double temperature;
    private double feelsLikeTemp;
    private double relHumidity;
    private double dewPoint;
    private double windSpeed;
    private double windDir;
    private String windDirString;
    private double windGust;
    private double precipProb;
    private double precipRate;
    private double cloudiness;
    private double thunderProb;
    private double uvIndex;
    private double pressure;
    private double visibility;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolPhrase() {
        return symbolPhrase;
    }

    public void setSymbolPhrase(String symbolPhrase) {
        this.symbolPhrase = symbolPhrase;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(double feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public double getRelHumidity() {
        return relHumidity;
    }

    public void setRelHumidity(double relHumidity) {
        this.relHumidity = relHumidity;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDir() {
        return windDir;
    }

    public void setWindDir(double windDir) {
        this.windDir = windDir;
    }

    public String getWindDirString() {
        return windDirString;
    }

    public void setWindDirString(String windDirString) {
        this.windDirString = windDirString;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public double getPrecipProb() {
        return precipProb;
    }

    public void setPrecipProb(double precipProb) {
        this.precipProb = precipProb;
    }

    public double getPrecipRate() {
        return precipRate;
    }

    public void setPrecipRate(double precipRate) {
        this.precipRate = precipRate;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getThunderProb() {
        return thunderProb;
    }

    public void setThunderProb(double thunderProb) {
        this.thunderProb = thunderProb;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }
}
