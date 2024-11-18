import java.util.List;
import java.util.Scanner;

public class InventorySystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Inventory inventory = new Inventory();

    public static void main(String[] args) {
        FileHandler.loadInventory(inventory);

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> searchBook();
                case 2 -> addBook();
                case 3 -> removeBook();
                case 4 -> getSuggestion();
                case 5 -> {
                    FileHandler.saveInventory(inventory.getAllBooks());
                    System.out.println("Inventory saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Bookstore Inventory System ---");
        System.out.println("1. Search for a book");
        System.out.println("2. Add a book");
        System.out.println("3. Remove a book");
        System.out.println("4. Get a book suggestion");
        System.out.println("5. Save and Exit");
    }

    private static void searchBook() {
        System.out.println("\n--- Search for a book ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1 -> {
                String id = getStringInput("Enter book ID: ");
                Book book = inventory.searchById(id);
                if (book != null) {
                    System.out.println("Book found: " + book);
                } else {
                    System.out.println("Book not found.");
                }
            }
            case 2 -> {
                String title = getStringInput("Enter book title: ");
                List<Book> books = inventory.searchByTitle(title);
                printBooks(books);
            }
            case 3 -> {
                String author = getStringInput("Enter author name: ");
                List<Book> books = inventory.searchByAuthor(author);
                printBooks(books);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addBook() {
        String id = getStringInput("Enter book ID: ");
        String title = getStringInput("Enter book title: ");
        String author = getStringInput("Enter book author: ");

        Book newBook = new Book(id, title, author);
        inventory.addBook(newBook);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        String id = getStringInput("Enter book ID to remove: ");
        inventory.removeBook(id);
        System.out.println("Book removed successfully (if it existed).");
    }

    private static void getSuggestion() {
        Book suggestion = BookSuggestion.getSuggestion(inventory.getAllBooks());
        if (suggestion != null) {
            System.out.println("Book suggestion: " + suggestion);
        } else {
            System.out.println("No books in inventory for suggestion.");
        }
    }

    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}