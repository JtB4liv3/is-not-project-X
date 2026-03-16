import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class NoteImporter {
    private String name;
    private String content;

    // Конструктор: при создании объекта он сам запросит имя файла и прочитает его
    public NoteImporter() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название файла с расширением: ");
        this.name = in.nextLine();

        try {
            // Читаем весь файл целиком в одну строку
            this.content = Files.readString(Path.of(name));
            System.out.println("Файл успешно прочитан!");
        } catch (IOException e) {
            System.err.println("Ошибка: не удалось найти или прочитать файл " + name);
            this.content = ""; // Оставляем пустым при ошибке
        }
    }

    // Геттеры, чтобы можно было получить данные из других частей программы
    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}