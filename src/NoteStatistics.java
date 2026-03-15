import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//ОПАСНО - ЭТО ТВОРЕНИЕ ДИК ПИКА
// Класс заметки
class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}

// Менеджер заметок
class NoteManager {
    private List<Note> notes = new ArrayList<>();

    public void createNote(String title, String content) {
        notes.add(new Note(title, content));
    }

    public List<Note> getAllNotes() {
        return notes;
    }
}

// 4. Статистика символов и слов
class Statistics {
    private NoteManager manager;

    public Statistics(NoteManager manager) {
        this.manager = manager;
    }

    public void printStatistics() {
        List<Note> notes = manager.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("Нет заметок");
            return;
        }

        System.out.println("\n=== Статистика слов и символов ===");

        int totalWords = 0;
        int totalChars = 0;
        Map<Character, Integer> charCount = new HashMap<>();

        for (Note note : notes) {
            String content = note.getContent();
            totalWords += content.split("\\s+").length;
            totalChars += content.length();

            for (char c : content.toLowerCase().toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
            }
        }

        System.out.println("Всего слов: " + totalWords);
        System.out.println("Всего символов: " + totalChars);
        System.out.println("Средняя длина: " + (totalChars / notes.size()) + " символов");

        // Самый частый символ
        Map.Entry<Character, Integer> mostFrequentChar = null;
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (mostFrequentChar == null || entry.getValue() > mostFrequentChar.getValue()) {
                mostFrequentChar = entry;
            }
        }

        if (mostFrequentChar != null) {
            System.out.println("Самый частый символ: '" + mostFrequentChar.getKey() +
                    "' (" + mostFrequentChar.getValue() + " раз)");
        }
    }
}

// Главный класс для запуска
public class NoteStatistics {
    public static void main(String[] args) {
        // Создаем менеджер
        NoteManager manager = new NoteManager();

        // Добавляем заметки
        manager.createNote("Покупки", "Купить хлеб, молоко, яйца и сыр");
        manager.createNote("Работа", "Сдать отчет до пятницы");
        manager.createNote("Идеи", "Создать приложение для заметок на Java");

        // Запускаем статистику
        Statistics stats = new Statistics(manager);
        stats.printStatistics();
    }
}