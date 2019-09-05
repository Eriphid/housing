package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Picker<T> {
    String title;
    boolean cancellable = false;

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public Picker(String title) {
        this.title = title;
    }

    static Scanner scanner = new Scanner(System.in);
    ArrayList<Entry<T>> entries = new ArrayList<>();

    private Entry<T> pick(String msg, String errmsg) {
        System.out.print(msg + " ");
        for (; ; ) {
            try {
                final var value = scanner.nextLine();
                if (value.equals("q")) {
                    System.out.println("quitting...");
                    System.exit(0);
                }
                if (value.equals("c") && cancellable) {
                    return null;
                }
                final var input = Integer.parseInt(value);
                if (input > 0 && input <= entries.size())
                    return entries.get(input - 1);
                else
                    System.out.println("Please type a number between 1 and " + entries.size());
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(errmsg);
            }
        }
    }

    private Entry<T> pick(String msg) {
        return pick(msg, "Invalid input... Please type a number");
    }

    public T show(String msg) {
        System.out.println();
        System.out.println(title);
        for (int i = 0; i < entries.size(); ++i) {
            final var entry = entries.get(i);
            System.out.println((i + 1) + " - " + entry.desc);
        }
        if (cancellable)
            System.out.println("c - Cancel");
        System.out.println("q - Quit");
        System.out.println();
        var entry = pick(msg);
        if (entry == null)
            return null;
        return entry.getValue();
    }

    public T show() {
        return show("Choice: ");
    }

    public boolean add(String description, T value) {
        return entries.add(new Entry<>(description, value));
    }

}
