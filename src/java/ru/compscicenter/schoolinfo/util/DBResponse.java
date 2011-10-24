package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:32
 */

/**
 * Класс, представляющий запись в базе.
 */
public class DBResponse {
    private int id;
    private int rating;
    private String name;
    private String city;
    private int univercityId;
    private String about;
    private UnivDescription univ;
    private FacultyDescription fac;

    private UnivDescription desc;

    public DBResponse(int id, String name, String about) {
        this.id = id;
        this.name = name;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return about;
    }

    public int getRating() {
        return rating;
    }
}