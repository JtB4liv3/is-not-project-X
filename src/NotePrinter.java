import java.util.ArrayList;
import java.util.List;

public class NotePrinter {
    private List<String> notes;

    public NotePrinter() {
        notes = new ArrayList<>();
        notes.add("Сделать работу с Git");
        notes.add("Выучить Java");
    }

    public void printAllNotes() {
        System.out.println("Список всех заметок");
        if (notes.isEmpty()) {
            System.out.println("Заметок пока нет");
        }

    }

    public static void main(String[] args) {
        NotePrinter printer = new NotePrinter();
        printer.printAllNotes();
    }
}
