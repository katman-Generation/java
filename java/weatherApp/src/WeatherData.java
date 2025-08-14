import java.util.List;

public class WeatherData {
    private String cityName;
    private double tempCelsius;
    private int humidity;
    private double windSpeed; // m/s
    private String condition;
    private String iconUrl;
    private List<String> shortForecast;

    // getters and setters
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public double getTempCelsius() { return tempCelsius; }
    public void setTempCelsius(double tempCelsius) { this.tempCelsius = tempCelsius; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public List<String> getShortForecast() { return shortForecast; }
    public void setShortForecast(List<String> shortForecast) { this.shortForecast = shortForecast; }

    // convenience
    public double getTempFahrenheit() { return tempCelsius * 9/5 + 32; }
}
