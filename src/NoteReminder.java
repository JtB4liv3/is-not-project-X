package com.noteapp;

import com.noteapp.manager.NoteManager;
import com.noteapp.reminder.NoteReminder;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
package com.noteapp.reminder;

import com.noteapp.model.Note;
import com.noteapp.manager.NoteManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class NoteReminder {
    private final NoteManager noteManager;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public NoteReminder(NoteManager noteManager) {
        this.noteManager = noteManager;
        this.scanner = new Scanner(System.in);
    }

    public void setReminder() {
        List<Note> notes = noteManager.getNotes();

        if (notes.isEmpty()) {
            System.out.println("❌ Нет заметок для установки напоминания.");
            return;
        }

        System.out.println("\n=== Доступные заметки ===");
        for (Note note : notes) {
            String reminderInfo = (note.getReminderTime() != null)
                    ? " (напоминание: " + note.getReminderTime().format(formatter) + ")"
                    : "";
            System.out.println("ID " + note.getId() + ": " + note.getTitle() + reminderInfo);
        }

        System.out.print("\nВведите ID заметки: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Введите корректный ID!");
            return;
        }

        Note selectedNote = findNoteById(id);
        if (selectedNote == null) {
            System.out.println("❌ Заметка с ID " + id + " не найдена!");
            return;
        }

        System.out.print("Введите дату и время (ГГГГ-ММ-ДД ЧЧ:ММ): ");
        String dateTimeStr = scanner.nextLine();

        try {
            LocalDateTime reminderTime = LocalDateTime.parse(dateTimeStr, formatter);

            if (reminderTime.isBefore(LocalDateTime.now())) {
                System.out.println("❌ Нельзя установить напоминание на прошлое!");
                return;
            }

            selectedNote.setReminderTime(reminderTime);
            System.out.println("✅ Напоминание установлено на " + reminderTime.format(formatter));

        } catch (DateTimeParseException e) {
            System.out.println("❌ Неверный формат даты! Используйте: ГГГГ-ММ-ДД ЧЧ:ММ");
        }
    }

    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<Note> notes = noteManager.getNotes();

        for (Note note : notes) {
            if (note.getReminderTime() != null && note.getReminderTime().isBefore(now)) {
                showReminder(note);
                note.setReminderTime(null);
            }
        }
    }

    public void showAllReminders() {
        List<Note> notes = noteManager.getNotes();
        boolean found = false;

        System.out.println("\n=== Активные напоминания ===");
        for (Note note : notes) {
            if (note.getReminderTime() != null) {
                System.out.printf("ID %d: %s - %s%n",
                        note.getId(),
                        note.getTitle(),
                        note.getReminderTime().format(formatter));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Нет активных напоминаний.");
        }
    }

    private void showReminder(Note note) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🔔 НАПОМИНАНИЕ 🔔");
        System.out.println("=".repeat(50));
        System.out.println("Заметка: " + note.getTitle());
        System.out.println("Время: " + note.getReminderTime().format(formatter));
        System.out.println("-".repeat(50));
        System.out.println("Содержание:");
        System.out.println(note.getContent());
        System.out.println("=".repeat(50) + "\n");
    }

    private Note findNoteById(int id) {
        List<Note> notes = noteManager.getNotes();
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }
}