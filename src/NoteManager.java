import com.noteapp.model.Note;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private List<Note> notes;
    private int nextId;
    
    public NoteManager() {
        this.notes = new ArrayList<>();
        this.nextId = 1;
    }
    
    
    public Note createNote(String title, String content) {
        Note note = new Note(title, content, nextId);
        notes.add(note);
        nextId++;
        System.out.println("Заметка создана: " + title + " (ID: " + note.getId() + ")");
        return note;
    }
    
    public boolean deleteNote(int noteId) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == noteId) {
                notes.remove(i);
                System.out.println("Заметка с ID " + noteId + " удалена");
                return true;
            }
        }
        System.out.println("Заметка с ID " + noteId + " не найдена");
        return false;
    }
    
    public void showAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("Заметок нет");
            return;
        }
        
        System.out.println("\n Список заметок ");
        for (Note note : notes) {
            System.out.println("ID: " + note.getId() + " | " + note.getTitle());
        }
        System.out.println("-----------------------\n");
    }
}
