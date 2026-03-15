import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class NoteVersion {
    int version;
    String content;
    LocalDateTime date;
    String author;

    NoteVersion(int version, String content, String author) {
        this.version = version;
        this.content = content;
        this.date = LocalDateTime.now();
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("Версия %d | %s | %s | %s",
                version, date, author, content);
    }
}

class Note {
    String id = UUID.randomUUID().toString();
    String title;
    List<NoteVersion> history = new ArrayList<>();

    Note(String title, String content, String author) {
        this.title = title;
        addVersion(content, author);
    }

    void addVersion(String content, String author) {
        history.add(new NoteVersion(history.size() + 1, content, author));
    }

    NoteVersion getVersion(int version) {
        return version > 0 && version <= history.size()
                ? history.get(version - 1) : null;
    }

    List<NoteVersion> getHistory() {
        return new ArrayList<>(history);
    }
}

class NoteHistory {
    private Map<String, Note> notes = new HashMap<>();

    public Note createNote(String title, String content, String author) {
        Note note = new Note(title, content, author);
        notes.put(note.id, note);
        return note;
    }

    public void updateNote(String id, String content, String author) {
        Note note = notes.get(id);
        if (note != null) {
            note.addVersion(content, author);
            System.out.println("Заметка обновлена");
        }
    }

    public void showHistory(String id) {
        Note note = notes.get(id);
        if (note == null) {
            System.out.println("Заметка не найдена");
            return;
        }

        System.out.println("\n=== История изменений: " + note.title + " ===");
        List<NoteVersion> history = note.getHistory();
        if (history.isEmpty()) {
            System.out.println("История пуста");
        } else {
            history.forEach(System.out::println);
        }
    }

    public void showVersion(String id, int version) {
        Note note = notes.get(id);
        if (note == null) {
            System.out.println("Заметка не найдена");
            return;
        }

        NoteVersion ver = note.getVersion(version);
        if (ver == null) {
            System.out.println("Версия не найдена");
            return;
        }

        System.out.println("\n=== Версия " + version + " ===");
        System.out.println(ver);
    }

    public void compareVersions(String id, int v1, int v2) {
        Note note = notes.get(id);
        if (note == null) {
            System.out.println("Заметка не найдена");
            return;
        }

        NoteVersion ver1 = note.getVersion(v1);
        NoteVersion ver2 = note.getVersion(v2);

        if (ver1 == null || ver2 == null) {
            System.out.println("Версия не найдена");
            return;
        }

        System.out.println("\n=== Сравнение версий " + v1 + " и " + v2 + " ===");
        System.out.println(v1 + ": " + ver1.content);
        System.out.println(v2 + ": " + ver2.content);

        if (ver1.content.equals(ver2.content)) {
            System.out.println("Версии идентичны");
        } else {
            System.out.println("Версии различаются");
        }
    }

    public void rollback(String id, int version, String author) {
        Note note = notes.get(id);
        if (note == null) {
            System.out.println("Заметка не найдена");
            return;
        }

        NoteVersion ver = note.getVersion(version);
        if (ver == null) {
            System.out.println("Версия не найдена");
            return;
        }

        note.addVersion(ver.content + " (восстановлено из версии " + version + ")", author);
        System.out.println("Восстановлена версия " + version);
    }
}

public class Main {
    public static void main(String[] args) {
        NoteHistory history = new NoteHistory();

        Note note = history.createNote("План", "Начать проект", "Анна");
        history.updateNote(note.id, "Добавить тесты", "Анна");
        history.updateNote(note.id, "Завершить разработку", "Петр");

        history.showHistory(note.id);
        history.showVersion(note.id, 2);
        history.compareVersions(note.id, 1, 3);
        history.rollback(note.id, 1, "Анна");
        history.showHistory(note.id);
    }
}