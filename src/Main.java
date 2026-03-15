public class Main {
    public static void main(String[] args) {
        List<Note> notes = new ArrayList<>();
        NoteReminder reminder = new NoteReminder(notes);

        while (true) {
            // Проверяем напоминания перед показом меню
            reminder.checkReminders();


            System.out.println("1. Создать заметку ");

            System.out.println("11. Установить напоминание ");

            Никита лох кстати
        }
    }
}