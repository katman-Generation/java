
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private TextField cityInput;
    private Label tempLabel, humidityLabel, windLabel, conditionLabel;
    private ImageView weatherIcon;
    private ListView<String> historyList;
    private List<String> history = new ArrayList<>();
    private boolean isCelsius = true; // start with Celsius

    @Override
    public void start(Stage primaryStage) {
        // Top input area
        cityInput = new TextField();
        cityInput.setPromptText("Enter city name (e.g., Pushkar)");
        Button searchButton = new Button("Get Weather");

        ToggleButton unitToggle = new ToggleButton("Switch to °F");
        unitToggle.setOnAction(e -> {
            isCelsius = !isCelsius;
            unitToggle.setText(isCelsius ? "Switch to °F" : "Switch to °C");
        });

        HBox inputBox = new HBox(10, cityInput, searchButton, unitToggle);
        inputBox.setPadding(new Insets(10));

        // Weather display area
        tempLabel = new Label("Temperature: -");
        humidityLabel = new Label("Humidity: -");
        windLabel = new Label("Wind: -");
        conditionLabel = new Label("Condition: -");
        weatherIcon = new ImageView();
        weatherIcon.setFitWidth(100);
        weatherIcon.setFitHeight(100);

        VBox weatherBox = new VBox(8, tempLabel, humidityLabel, windLabel, conditionLabel, weatherIcon);
        weatherBox.setPadding(new Insets(10));

        // Forecast area (simple placeholder)
        Label forecastLabel = new Label("Short-term forecast (from API):");
        ListView<String> forecastList = new ListView<>();
        forecastList.setPrefHeight(120);

        VBox forecastBox = new VBox(6, forecastLabel, forecastList);
        forecastBox.setPadding(new Insets(10));

        // History
        historyList = new ListView<>();
        historyList.setPrefWidth(260);
        VBox historyBox = new VBox(6, new Label("Search History (latest 5):"), historyList);
        historyBox.setPadding(new Insets(10));

        // Layout root
        BorderPane root = new BorderPane();
        HBox center = new HBox(20, weatherBox, forecastBox);
        center.setPadding(new Insets(10));
        root.setTop(inputBox);
        root.setCenter(center);
        root.setRight(historyBox);
        root.setStyle("-fx-background-color: " + getBackgroundColor() + ";");

        // Search button action
        searchButton.setOnAction(e -> {
            fetchWeather(forecastList);
        });

        // allow Enter key to trigger search
        cityInput.setOnAction(e -> fetchWeather(forecastList));

        Scene scene = new Scene(root, 900, 460);
        primaryStage.setTitle("Weather Information App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchWeather(ListView<String> forecastList) {
        String city = cityInput.getText().trim();
        if (city.isEmpty()) {
            showAlert("Input Error", "Please enter a city name.");
            return;
        }

        try {
            // WeatherService handles API calls and JSON parsing
            WeatherData data = WeatherService.getCurrentWeather(city);

            if (data == null) {
                showAlert("API Error", "No data returned. Check city name or API key.");
                return;
            }

            double temp = isCelsius ? data.getTempCelsius() : data.getTempFahrenheit();
            tempLabel.setText(String.format("Temperature: %.1f °%s", temp, isCelsius ? "C" : "F"));
            humidityLabel.setText(String.format("Humidity: %d %%", data.getHumidity()));
            windLabel.setText(String.format("Wind: %.1f m/s", data.getWindSpeed()));
            conditionLabel.setText("Condition: " + data.getCondition());

            // Load icon (OpenWeatherMap icon)
            if (data.getIconUrl() != null && !data.getIconUrl().isEmpty()) {
                weatherIcon.setImage(new Image(data.getIconUrl(), true));
            }

            // Forecasts (simple list of next items)
            forecastList.getItems().clear();
            for (String f : data.getShortForecast()) {
                forecastList.getItems().add(f);
            }

            // Update history (store latest 5)
            String record = String.format("%s | %.1f°C | %s | %s",
                    data.getCityName(), data.getTempCelsius(), data.getCondition(), LocalDateTime.now());
            history.add(0, record);
            if (history.size() > 5) history.remove(5);
            historyList.getItems().setAll(history);

        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "Failed to fetch weather. Check your internet connection and API key.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String getBackgroundColor() {
        int hour = LocalTime.now().getHour();
        if (hour >= 6 && hour < 12) return "#FFFACD";  // morning (light lemon)
        if (hour >= 12 && hour < 18) return "#87CEEB"; // afternoon (sky blue)
        if (hour >= 18 && hour < 20) return "#FFD580"; // evening (warm)
        return "#191970"; // night (midnight blue)
    }

    public static void main(String[] args) {
        launch(args);
    }
}

