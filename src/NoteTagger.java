import java.util.HashSet;
import java.util.Set;

public class NoteTagger {
    private Set<String> tags;

    public NoteTagger() {
        this.tags = new HashSet<>();
    }

    // добавляем тег
    public void addTag(String tag) {
        if (tag != null && !tag.isEmpty()) {
            tags.add(tag.toLowerCase()); // в нижний регистр
            System.out.println("Тег добавлен: " + tag);
        }
    }

    // вывод всех тегов
    public void showTags() {
        System.out.println("Теги заметки: " + tags);
    }

    public static void main(String[] args) {
        NoteTagger myNote = new NoteTagger();

        // теги
        myNote.addTag("Учеба");
        myNote.addTag("Работа");
        myNote.addTag("Важное");

        // против дубликатов
        myNote.addTag("Покупки");

        myNote.showTags();
    }
}