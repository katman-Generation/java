import java.util.ArrayList;

public class StockAnalysisTool {

    // Method to calculate the average value of stock prices in an array
    public static float calculateAverageStockPrice(int[] dailyPrices) {
        int totalPrice = 0;
        for (int currentPrice : dailyPrices) {
            totalPrice += currentPrice;  // Add each day's price to the total
        }
        return (float) totalPrice / dailyPrices.length;  // Compute average
    }

    // Method to find the highest stock price in the array
    public static int findHighestStockPrice(int[] dailyPrices) {
        int highestPrice = dailyPrices[0];  // Start with the first price
        for (int price : dailyPrices) {
            if (price > highestPrice) {
                highestPrice = price;  // Update if a higher price is found
            }
        }
        return highestPrice;
    }

    // Method to count how many times a specific price appears in the array
    public static int countTargetPriceOccurrences(int[] dailyPrices, int targetPrice) {
        int occurrenceCount = 0;
        for (int price : dailyPrices) {
            if (price == targetPrice) {
                occurrenceCount++;  // Increment count if match found
            }
        }
        return occurrenceCount;
    }

    // Method to calculate cumulative stock prices using an ArrayList
    public static ArrayList<Integer> generateCumulativeStockPrices(ArrayList<Integer> priceList) {
        ArrayList<Integer> cumulativePriceList = new ArrayList<>();
        int runningTotal = 0;

        for (int price : priceList) {
            runningTotal += price;  // Add current price to running total
            cumulativePriceList.add(runningTotal);  // Store the cumulative value
        }
        return cumulativePriceList;
    }

    // Main method to test and display results
    public static void main(String[] args) {
        // Sample stock prices for 10 days
        int[] openingStockPrices = {115, 120, 118, 123, 125, 130, 128, 132, 135, 138};

        // Copy array values to an ArrayList for cumulative sum calculation
        ArrayList<Integer> stockPriceList = new ArrayList<>();
        for (int price : openingStockPrices) {
            stockPriceList.add(price);
        }

        // Output results
        System.out.println("=== Stock Price Analysis ===");
        System.out.println("Daily Opening Prices: ");
        for (int price : openingStockPrices) {
            System.out.print(price + " ");
        }

        System.out.println("\n\n=> Average Price over 10 Days: " + calculateAverageStockPrice(openingStockPrices));
        System.out.println("=> Maximum Price Observed: " + findHighestStockPrice(openingStockPrices));
        System.out.println("=> Occurrences of Price 130: " + countTargetPriceOccurrences(openingStockPrices, 130));
        System.out.println("=> Cumulative Price List: " + generateCumulativeStockPrices(stockPriceList));
    }
}
