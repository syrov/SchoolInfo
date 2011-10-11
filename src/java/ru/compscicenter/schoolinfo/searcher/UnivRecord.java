package ru.compscicenter.schoolinfo.searcher;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */

import java.util.Comparator;

/**
 * Класс, представляющий запись в базе.
 */
public class UnivRecord {
    private int id;
    private int rating;
    private String name;
    private String about;

    public UnivRecord(int id, String name, String about) {
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