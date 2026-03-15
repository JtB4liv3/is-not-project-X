import java.util.ArrayList;
import java.util.List;

public class NotePrinter {
    private List<String> notes;

    public NotePrinter() {
        notes = new ArrayList<>();

    }

    public void printAllNotes() {
        System.out.println("Список всех заметок");
        if (notes.isEmpty()) {
            System.out.println("Заметок пока нет.");
        }
        
    }

    public static void main(String[] args) {
        NotePrinter printer = new NotePrinter();
        printer.printAllNotes();
    }
}
