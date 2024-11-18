import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Book> books;

    public Inventory() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public Book searchById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}