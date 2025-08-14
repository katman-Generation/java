import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TextAnalyzerTool {

    public static void main(String[] args) {
        // Create Scanner object to read user input
        Scanner userInputScanner = new Scanner(System.in);

        // 1. Ask user to input a paragraph
        System.out.println("Welcome to the Text Analysis Tool!");
        System.out.println("Please enter a paragraph of text:");
        String inputText = userInputScanner.nextLine().trim();

        // Input validation: check for empty input
        while (inputText.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter a paragraph:");
            inputText = userInputScanner.nextLine().trim();
        }

        // Convert input text to lowercase for uniform analysis
        String processedText = inputText.toLowerCase();

        // 2. Character Count (including spaces and punctuation)
        int totalCharacters = inputText.length();

        // 3. Word Count: splitting the string by space
        String[] wordsArray = inputText.trim().split("\\s+");
        int totalWords = wordsArray.length;

        // 4. Most Common Character (excluding spaces)
        HashMap<Character, Integer> characterFrequencyMap = new HashMap<>();

        for (char character : processedText.toCharArray()) {
            if (Character.isLetter(character)) {
                characterFrequencyMap.put(character, characterFrequencyMap.getOrDefault(character, 0) + 1);
            }
        }

        // Find the most common character
        char mostFrequentChar = ' ';
        int maxFrequency = 0;

        for (Map.Entry<Character, Integer> entry : characterFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentChar = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        // 5. Character Frequency (user input)
        System.out.println("Enter a character to check its frequency:");
        String characterInput = userInputScanner.nextLine().toLowerCase();

        while (characterInput.length() != 1 || !Character.isLetter(characterInput.charAt(0))) {
            System.out.println("Please enter a single alphabet character:");
            characterInput = userInputScanner.nextLine().toLowerCase();
        }

        char characterToCheck = characterInput.charAt(0);
        int charCount = 0;
        for (char c : processedText.toCharArray()) {
            if (c == characterToCheck) {
                charCount++;
            }
        }

        // 6. Word Frequency (user input)
        System.out.println("Enter a word to check its frequency:");
        String wordToCheck = userInputScanner.nextLine().toLowerCase();

        while (wordToCheck.trim().isEmpty() || !wordToCheck.matches("[a-zA-Z]+")) {
            System.out.println("Please enter a valid word (alphabet only):");
            wordToCheck = userInputScanner.nextLine().toLowerCase();
        }

        int wordFrequency = 0;
        for (String word : wordsArray) {
            if (wordToCheck.equalsIgnoreCase(word)) {
                wordFrequency++;
            }
        }

        // 7. Unique Word Count (case-insensitive)
        HashSet<String> uniqueWordsSet = new HashSet<>();
        for (String word : wordsArray) {
            uniqueWordsSet.add(word.toLowerCase());
        }
        int uniqueWordCount = uniqueWordsSet.size();

        // Display results
        System.out.println("\n--- Text Analysis Results ---");
        System.out.println("Total number of characters (including spaces): " + totalCharacters);
        System.out.println("Total number of words: " + totalWords);
        System.out.println("Most common character: '" + mostFrequentChar + "'");
        System.out.println("Frequency of character '" + characterToCheck + "': " + charCount);
        System.out.println("Frequency of word \"" + wordToCheck + "\": " + wordFrequency);
        System.out.println("Number of unique words: " + uniqueWordCount);

        // Close Scanner to prevent resource leak
        userInputScanner.close();
    }
}

