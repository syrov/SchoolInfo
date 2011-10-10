package ru.compscicenter.schoolinfo.searcher;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class UserQuery {
//  это всякие разные пункты, потом добавим ещё, можно в один список
    private String direction;
    private String city;
    private String university;

    public UserQuery(String dir, String cit, String uni) {
        direction = dir;
        city = cit;
        university = uni;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getCity() {
        return this.city;
    }

    public String getUniversity() {
        return this.university;
    }
}
