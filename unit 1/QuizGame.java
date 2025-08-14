import java.util.Scanner; 
 
public class QuizGame { 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
 
        String[] questions = { 
            "1. When was Chelsea Football Club founded?\nA. 1892\nB. 1905\nC. 1910\nD. 1923", 
            "2. What is the name of Chelsea's home stadium?\nA. Old Trafford\nB. Anfield\nC. Stamford Bridge\nD. Emirates Stadium", 
            "3. Who is Chelsea's all-time top scorer?\nA. Didier Drogba\nB. Frank Lampard\nC. Gianfranco Zola\nD. Jimmy Greaves", 
            "4. Which year did Chelsea win their first UEFA Champions League title?\nA. 2008\nB. 2012\nC. 2014\nD. 2016", 
            "5. What are the traditional colors of Chelsea's home kit?\nA. Red and White\nB. Blue and White\nC. Green and Black\nD. Yellow and Blue" 
        }; 
 
        char[] answers = {'B', 'C', 'B', 'B', 'B'}; 
 
        int score = 0; 
 
        for (int i = 0; i < questions.length; i++) { 
            System.out.println(questions[i]); 
            System.out.print("Your answer (A/B/C/D): "); 
            String input = scanner.nextLine().toUpperCase(); 
 
            while (!input.equals("A") && !input.equals("B") && !input.equals("C") && !input.equals("D")) { 
                System.out.print("Invalid input. Please enter A, B, C, or D: "); 
                input = scanner.nextLine().toUpperCase(); 
            } 
 
            switch (input.charAt(0)) { 
                case 'A': 
                case 'B': 
                case 'C': 
                case 'D': 
                    if (input.charAt(0) == answers[i]) { 
                        score++; 
                    } 
                    break; 
                default: 
                    System.out.println("Unexpected error."); 
            } 
            System.out.println(); 
        } 
 
        double percentage = (score / (double) questions.length) * 100; 
        System.out.println("Quiz completed!"); 
        System.out.println("You got " + score + " out of " + questions.length + " correct."); 
        System.out.printf("Your score: %.2f%%\n", percentage); 
 
        scanner.close(); 
    } 
} 
