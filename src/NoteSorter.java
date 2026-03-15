import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoteSorter {

    // Сортировка по заголовку (Алфавитный порядок)
    public static void sortByTitle(List<Note> notes) {
        notes.sort(Comparator.comparing(Note::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    // Сортировка по дате создания (Сначала старые)
    public static void sortByDateAsc(List<Note> notes) {
        notes.sort(Comparator.comparing(Note::getCreatedAt));
    }

    // Сортировка по дате создания (Сначала новые)
    public static void sortByDateDesc(List<Note> notes) {
        notes.sort(Comparator.comparing(Note::getCreatedAt).reversed());
    }

    public static void main(String[] args) {
        List<Note> myNotes = new ArrayList<>();

        System.out.println("Исходный список");
        myNotes.forEach(System.out::println);

        // Пример проверки сортировки по названию
        System.out.println("\nСортировка по заголовку");
        sortByTitle(myNotes);
        myNotes.forEach(System.out::println);

        // Пример проверки сортировки по дате (новые сверху)
        System.out.println("\nСортировка по дате (сначала новые)");
        sortByDateDesc(myNotes);
        myNotes.forEach(System.out::println);
    }
}