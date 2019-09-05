package ui;

class Entry<T> {
    String desc;
    T value;

    public Entry(String desc, T value) {
        this.desc = desc;
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
