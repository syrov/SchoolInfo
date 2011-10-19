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

    public static final String QTYPE_FACULTY = "faculty";
    public static final String QTYPE_UNIV = "univercity";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";

    private String queryType;
    private String query = "";

    private int id;
    private String name;
    private String city;
    private UnivDescription description;

    /**
     * Простейший конструктор
     */
    public UserQuery(String qType) {
        this.queryType = qType;
        id = 0;
        name = city = "";
        description = null;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDesc(UnivDescription desc) {
        this.description = desc;
    }

    /**
     * Конструирует и возвращает строку запроса
     */
    public String getQuery() {
        if (query == "") {
            if (name != "") {
                query += FIELD_NAME + ":" + name;
            }

            if (city != "") {
                query += FIELD_CITY + ":" + city;
            }
        }
        return query;

    }
}
