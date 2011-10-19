package ru.compscicenter.schoolinfo.storage;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */
public class University {
    //    private int id;
    private final String name;
    private String city;
    private String description;

    public University(String name, String city, String description) {
        //       this.id = id;
        this.name = name;
        this.city = city;
        this.description = description;
    }

    public University(String name) {
        this.name = name;
    }

/*    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }       */

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
