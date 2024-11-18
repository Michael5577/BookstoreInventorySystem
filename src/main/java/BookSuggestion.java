import java.util.List;
import java.util.Random;

public class BookSuggestion {
    private static final Random random = new Random();

    public static Book getSuggestion(List<Book> books) {
        if (books.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(books.size());
        return books.get(randomIndex);
    }
}