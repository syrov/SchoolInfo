package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */

/**
 * Класс, представляющий пользовательские запросы,
 * как простые, так и сложные.
 */
public class UserQuery {
//  это всякие разные пункты, потом добавим ещё, можно в один список

    /**
     * Пользовательский запрос
     */
    private String query;

    private String direction;
    private String city;
    private String university;

    /**
     * Простейший конструктор
     */
    public UserQuery(String queryExpression) {
        query = queryExpression;
    }

    /**
     * Возвращает текст пользовательского запроса
     */
    public String getQueryExpression() {
        return query;
    }

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