package ui;

public class Menu {
    Picker<Runnable> picker;

    public Menu(String title) {
        picker = new Picker<>(title);
    }

    public boolean show(String msg) {
        final var entry = picker.show(msg);
        if (entry != null) {
            entry.run();
            return true;
        }
        return false;
    }

    public boolean show() {
        return show("Choice: ");
    }

    public boolean add(String desc, Runnable task) {
        return picker.add(desc, task);
    }

    public boolean isCancellable() {
        return picker.isCancellable();
    }

    public void setCancellable(boolean value) {
        picker.setCancellable(value);
    }
}
