package ru.pritykovskaya;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */
public class University {
    private int id;
    private final String name;

    public University(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public University(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
