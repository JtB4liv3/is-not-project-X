import java.util.*;
import java.util.stream.Collectors;

public class NoteFilter {
    private List<Note> notes = new ArrayList<>();

    static class Note {
        String title, content; Set<String> tags;
        Note(String t, String c, String... ts) { title=t; content=c; tags=new HashSet<>(Arrays.asList(ts)); }
        boolean match(String text, Set<String> filterTags, boolean and) {
            boolean textOk = text==null || text.isEmpty() || title.contains(text) || content.contains(text);
            if (filterTags==null || filterTags.isEmpty()) return textOk;
            return textOk && (and ? tags.containsAll(filterTags) : tags.stream().anyMatch(filterTags::contains));
        }
        public String toString() { return title+" "+tags; }
    }

    void add(String t, String c, String... ts) { notes.add(new Note(t, c, ts)); }

    List<Note> filter(String text, Set<String> tags, boolean and) {
        return notes.stream().filter(n -> n.match(text, tags, and)).collect(Collectors.toList());
    }

    Set<String> getAllTags() {
        return notes.stream().flatMap(n -> n.tags.stream()).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        NoteFilter nf = new NoteFilter();
        nf.add("Встреча", "Проект", "работа", "важно");
        nf.add("Покупки", "Хлеб", "личное");
        nf.filter(null, Set.of("работа"), true).forEach(System.out::println);
        System.out.println("Теги: "+nf.getAllTags());
    }
}