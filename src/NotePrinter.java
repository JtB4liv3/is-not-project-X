
import java.util.ArrayList;
import java.util.List;

public class NotePrinter {
    private List<String> notes = new ArrayList();

    public NotePrinter() {
        this.notes.add("Сделать работу с Git");
        this.notes.add("Выучить Java");
    }

    public void printAllNotes() {
        System.out.println("Список всех заметок");
        if (this.notes.isEmpty()) {
            System.out.println("Заметок пока нет");
        }

    }

    public static void main(String[] args) {
        NotePrinter printer = new NotePrinter();
        printer.printAllNotes();
    }
}
