Weather Information App - JavaFX

Files included:
- Main.java
- WeatherService.java
- WeatherData.java

Requirements:
- Java 11 or newer (Java 17 recommended).
- JavaFX SDK (match your Java version).
- Gson library (for JSON parsing). Download gson-2.8.x.jar and add to classpath.

OpenWeatherMap API:
- Sign up at https://openweathermap.org/ and get a free API key.
- Replace the placeholder in WeatherService.java: private static final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";

How to compile (command-line example):
1) Place JavaFX SDK in a folder, e.g., /path/to/javafx-sdk-18/lib
2) Place gson-2.8.x.jar in libs/ folder

Example (Linux/macOS):
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp libs/gson-2.8.9.jar:. Main.java WeatherService.java WeatherData.java

Run:
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp libs/gson-2.8.9.jar:. Main

Notes for Windows (PowerShell):
Change path separators to semicolons and adjust paths accordingly.

What the app does:
- Input a city name and press Get Weather (or Enter).
- Displays temperature, humidity, wind speed, condition, an icon, and a short forecast.
- Toggle between Celsius and Fahrenheit.
- Stores the last 5 searches with timestamps in the right-hand history pane.
- Background color changes based on local time of day.

Screenshots:
- Run the app locally and take a screenshot of the GUI. Include it in your Word/PDF submission as required by the assignment.

Troubleshooting:
- If you get errors related to JavaFX, ensure you added the correct --module-path and --add-modules flags.
- If API returns null, verify your API key and that you have internet connectivity.

Permissions & API limits:
- The free tier of OpenWeatherMap has request limits. Avoid very rapid repeated testing.

License: 
This sample code is provided for educational purposes. You may adapt and extend it for your assignment.
*/

