import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int quantity;

    Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    void addQuantity(int qty) {
        this.quantity += qty;
    }

    boolean borrow(int qty) {
        if (this.quantity >= qty) {
            this.quantity -= qty;
            return true;
        }
        return false;
    }

    void returnBook(int qty) {
        this.quantity += qty;
    }
}

public class library_system {
    private static Map<String, Book> library = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addBooks();
                case 2 -> borrowBooks();
                case 3 -> returnBooks();
                case 4 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void addBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter quantity: ");
        int quantity = getIntInput();

        if (library.containsKey(title)) {
            library.get(title).addQuantity(quantity);
            System.out.println("Book already exists. Quantity updated.");
        } else {
            library.put(title, new Book(title, author, quantity));
            System.out.println("Book added successfully.");
        }
    }

    private static void borrowBooks() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter quantity to borrow: ");
        int quantity = getIntInput();

        Book book = library.get(title);
        if (book != null) {
            if (book.borrow(quantity)) {
                System.out.println("Successfully borrowed " + quantity + " copies of \"" + title + "\".");
            } else {
                System.out.println("Not enough copies available.");
            }
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    private static void returnBooks() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter quantity to return: ");
        int quantity = getIntInput();

        Book book = library.get(title);
        if (book != null) {
            book.returnBook(quantity);
            System.out.println("Successfully returned " + quantity + " copies of \"" + title + "\".");
        } else {
            System.out.println("This book does not belong to this library.");
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter again: ");
            }
        }
    }
}
