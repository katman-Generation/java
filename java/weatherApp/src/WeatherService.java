import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    // TODO: Replace with your own OpenWeatherMap API key
    private static final String API_KEY = "ad68624956b797353b93ba064fd46b58";
    private static final String CURRENT_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric";

    // Get current weather and a short forecast
    public static WeatherData getCurrentWeather(String city) throws Exception {
        String encoded = URLEncoder.encode(city, "UTF-8");
        String curUrl = String.format(CURRENT_URL, encoded, API_KEY);
        String curJson = getJsonResponse(curUrl);
        if (curJson == null) return null;

        Gson gson = new Gson();
        JsonObject curObj = gson.fromJson(curJson, JsonObject.class);

        if (curObj.has("cod") && curObj.get("cod").getAsInt() != 200) {
            return null; // city not found or error
        }

        WeatherData data = new WeatherData();
        data.setCityName(curObj.get("name").getAsString());

        JsonObject main = curObj.getAsJsonObject("main");
        data.setTempCelsius(main.get("temp").getAsDouble());
        data.setHumidity(main.get("humidity").getAsInt());

        JsonObject wind = curObj.getAsJsonObject("wind");
        data.setWindSpeed(wind.get("speed").getAsDouble());

        JsonArray weatherArr = curObj.getAsJsonArray("weather");
        if (weatherArr.size() > 0) {
            JsonObject w = weatherArr.get(0).getAsJsonObject();
            data.setCondition(w.get("description").getAsString());
            String icon = w.get("icon").getAsString();
            data.setIconUrl("https://openweathermap.org/img/wn/" + icon + "@2x.png");
        }

        // Get short forecast (next ~5 items from forecast API)
        String forecastJson = getJsonResponse(String.format(FORECAST_URL, encoded, API_KEY));
        if (forecastJson != null) {
            JsonObject fObj = gson.fromJson(forecastJson, JsonObject.class);
            JsonArray list = fObj.getAsJsonArray("list");
            List<String> shortF = new ArrayList<>();
            int count = 0;
            for (JsonElement el : list) {
                if (count >= 5) break; // take first 5 entries (~15 hours)
                JsonObject item = el.getAsJsonObject();
                String dtTxt = item.get("dt_txt").getAsString();
                JsonObject m = item.getAsJsonObject("main");
                double t = m.get("temp").getAsDouble();
                JsonArray wArr = item.getAsJsonArray("weather");
                String desc = wArr.get(0).getAsJsonObject().get("description").getAsString();
                shortF.add(String.format("%s -> %.1f°C, %s", dtTxt, t, desc));
                count++;
            }
            data.setShortForecast(shortF);
        }

        return data;
    }

    private static String getJsonResponse(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        BufferedReader in;
        if (status >= 200 && status < 300) {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            // try to read error stream for debugging
            if (conn.getErrorStream() != null) {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                return null;
            }
        }

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }
}
