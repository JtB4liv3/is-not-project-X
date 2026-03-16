import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
public class Note {
    Scanner in = new Scanner(System.in);
    private String name;
    private String content;
    public void NoteImporter() {
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
    public void show(){
        System.out.print("Название: "+name+"\nОписание:\n"+content);
    }
}