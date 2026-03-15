package com.noteapp;

import com.noteapp.manager.NoteManager;
import com.noteapp.reminder.NoteReminder;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NoteReminder {
    private static NoteManager noteManager = new NoteManager();//жжж
    private static NoteReminder reminder;
    private static Scanner scanner = new Scanner(System.in);
    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        reminder = new NoteReminder(noteManager);

        // Запускаем фоновую проверку напоминаний
        startReminderChecker();

        while (true) {
            // Проверяем напоминания перед меню
            reminder.checkReminders();

            // Показываем меню
            printMenu();

            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNote();
                    break;
                case "2":
                    // Поиск (Иван)
                    System.out.println("Функция в разработке");
                    break;
                case "3":
                    noteManager.showAllNotes();
                    break;
                case "11":
                    reminder.setReminder();
                    break;
                case "12":
                    reminder.showAllReminders();
                    break;
                case "0":
                    System.out.println("Выход...");
                    scheduler.shutdown();
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Создать заметку (Костя)");
        System.out.println("2. Поиск заметок (Иван)");
        System.out.println("3. Показать все заметки (Кирилл)");
        // ... другие пункты
        System.out.println("11. Установить напоминание (Платон)");
        System.out.println("12. Показать все напоминания (Платон)");
        System.out.println("0. Выход");
    }

    private static void createNote() {
        System.out.print("Введите заголовок: ");
        String title = scanner.nextLine();
        System.out.print("Введите содержание: ");
        String content = scanner.nextLine();
        noteManager.createNote(title, content);
    }

    private static void startReminderChecker() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            reminder.checkReminders();
        }, 0, 30, TimeUnit.SECONDS);
    }
}