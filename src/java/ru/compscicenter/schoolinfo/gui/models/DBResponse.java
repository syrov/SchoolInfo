package ru.compscicenter.schoolinfo.gui.models;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:32
 */

import ru.compscicenter.schoolinfo.util.FacultyDescription;
import ru.compscicenter.schoolinfo.util.UnivDescription;

/**
 * Класс, представляющий запись в базе.
 */
public class DBResponse {
    private int id;
    private int rating;
    private String name;
    private String city;
//    private int universityId;
//    private String about;
    private UnivDescription univ;
    private FacultyDescription fac;


    public DBResponse(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
// temporally
        this.rating = 666;

//        universityId = 0;
//        about = "nothing";
        univ = new UnivDescription("type", "campus");
        fac = new FacultyDescription("1", "2", "3", "4");
    }

//    public void setUniv(UnivDescription univ) {
//        this.univ = univ;
//    }
//
//    public void setFac(FacultyDescription fac) {
//        this.fac = fac;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getCity() {
        return city;
    }

}