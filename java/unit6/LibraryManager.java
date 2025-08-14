import java.util.Scanner;


public class LibraryManager {
    public static void main(String[] args) {
        AssetShelf<LibraryAsset> digitalShelf = new AssetShelf<>();
        Scanner inputScanner = new Scanner(System.in);
        int menuChoice;

        do {
            System.out.println("\n========== LIBRARY MENU ==========");
            System.out.println("Add New Asset");
            System.out.println("Remove Asset");
            System.out.println("View All Assets");
            System.out.println("Exit");
            System.out.print("Select an option: ");
            menuChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear newline

            switch (menuChoice) {
                case 1:
                    System.out.print("Enter asset title: ");
                    String titleInput = inputScanner.nextLine();

                    System.out.print("Enter creator name: ");
                    String creatorInput = inputScanner.nextLine();

                    System.out.print("Enter unique asset ID: ");
                    String idInput = inputScanner.nextLine();

                    LibraryAsset asset = new LibraryAsset(titleInput, creatorInput, idInput);
                    digitalShelf.insertAsset(asset);
                    break;

                case 2:
                    System.out.print("Enter asset ID to remove: ");
                    String idToRemove = inputScanner.nextLine();
                    digitalShelf.deleteAsset(idToRemove);
                    break;

                case 3:
                    System.out.println("\nCurrent Assets on Shelf:");
                    digitalShelf.displayShelf();
                    break;

                case 4:
                    System.out.println("Exiting Library Catalog. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (menuChoice != 4);

        inputScanner.close();
    }
}

