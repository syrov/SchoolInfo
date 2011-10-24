package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 10:51
 */

/**
 * Класс, представляющий пользовательские запросы,
 * как простые, так и сложные.
 */
public class UserQuery {

    // Названия полей в индексе
    public static final String QTYPE_FACULTY = "faculty";
    public static final String QTYPE_UNIV = "univercity";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_CAMPUS = "campus";
    public static final String FIELD_UNIV_ID = "univercity_id";
    public static final String FIELD_FORM = "form";
    public static final String FIELD_PHD = "phd";
    public static final String FIELD_DIP_TYPE = "diploma_type";
    public static final String FIELD_MILITARY = "military";
    public static final String UNIV_PREF = "univ";
    public static final String FAC_PREF = "fac";

    private String queryType;
    private String query = "";

    private int id;
    private String name;
    private String city;
    private int univercityId;
    private UnivDescription univDesc;
    private FacultyDescription facultyDesc;

    /**
     * Простейший конструктор
     */
    public UserQuery(String qType) {
        this.queryType = qType;
        id = 0;
        name = city = "";
        univDesc = null;
        facultyDesc = null;
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

    public void setUnivDesc(UnivDescription desc) {
        this.univDesc = desc;
    }

    public void setFacultyDesc(FacultyDescription desc) {
        this.facultyDesc = desc;
    }

    public void clear() {
        query = "";
    }

    public void add(String fieldName, String value) {
        if (value != "") {
            if (query != "") {
                query += " AND ";
            }
            query += fieldName + ":" + value;
        }
    }

    /**
     * Конструирует и возвращает строку запроса
     */
    public String getQuery() {
        if (queryType == QTYPE_UNIV) {
            clear();
            add(FIELD_NAME, name);
            add(FIELD_CITY, city);
            add(UNIV_PREF + FIELD_TYPE, univDesc.getType());
            add(UNIV_PREF + FIELD_CAMPUS, univDesc.getCampus());
        } else if (queryType == QTYPE_FACULTY) {
            clear();
            add(FIELD_UNIV_ID, String.valueOf(univercityId));
            add(FIELD_NAME, name);
            add(FAC_PREF + FIELD_FORM, facultyDesc.getForm());
            add(FAC_PREF + FIELD_PHD, facultyDesc.getPhd());
            add(FAC_PREF + FIELD_DIP_TYPE, facultyDesc.getDiplomaType());
            add(FAC_PREF + FIELD_MILITARY, facultyDesc.getMilitary());
        }
        return query;

    }
}
