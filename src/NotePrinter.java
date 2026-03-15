import java.util.ArrayList; //библиотекм для заметок
import java.util.List;

public class NotePrinter { //Класс заметок
    private List<String> notes; //Список для хранения заметок

    public NotePrinter() { //функция созжающая примеры заметок
        notes = new ArrayList<>();
        notes.add("Сделать работу с Git"); //сами примеры
        notes.add("Выучить Java");
    }

    public void printAllNotes() { //метод для вывода заметок в консоль
        System.out.println("Список всех заметок");
        if (notes.isEmpty()) { //проверка на наличие заметок
            System.out.println("Заметок пока нет");
        }

    }

    public static void main(String[] args) { //главный метод
        NotePrinter printer = new NotePrinter(); //создание объекта для вывода примеров
        printer.printAllNotes(); //выполнение функции printAllNotes
    }
}
