package ru.compscicenter.schoolinfo.storage;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */
public class University {
    //    private int id;
    private final String name;
    private String city;

    public University(String name, String city) {
        //       this.id = id;
        this.name = name;
        this.city = city;
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

    @Override
    public String toString() {
        return getName();
    }
}
