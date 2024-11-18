import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "books.csv";

    public static void loadInventory(Inventory inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }
                String[] parts = line.split("\t"); // Use tab as delimiter
                if (parts.length == 3) {
                    inventory.addBook(new Book(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public static void saveInventory(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("id\ttitle\tauthor"); // CSV header
            for (Book book : books) {
                writer.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}