package ru.compscicenter.schoolinfo.searcher;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 10:51
 */

import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import ru.compscicenter.schoolinfo.util.FacultyDescription;
import ru.compscicenter.schoolinfo.util.UnivDescription;

/**
 * Класс, представляющий пользовательские запросы,
 * как простые, так и сложные.
 */

public class UserQuery {

    // Названия полей в индексе
    public static final String QTYPE_FACULTY = "faculty";
    public static final String QTYPE_UNIV = "university";
    public static final String FIELD_DIRECTION = "direction";
    public static final String FIELD_SPECIALITY = "speciality";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_CAMPUS = "campus";
    public static final String FIELD_UNIV_ID = "university_id";
    public static final String FIELD_FORM = "form";
    public static final String FIELD_PHD = "phd";
    public static final String FIELD_DIP_TYPE = "diploma_type";
    public static final String FIELD_MILITARY = "military";
    public static final String UNIV_PREF = "univ";
    public static final String FAC_PREF = "fac";

// строка, по которой идет обращение к Searcher'у
//    private final String query;

    // параметры поиска
    private final String queryType;

    //    private final String name;
    private final String direction;
    private final String speciality;
    private final String city;
    private final String universityName;

    private final UnivDescription univDesc;
    private final FacultyDescription facultyDesc;

    private int id;
    private int universityId;

    //  простой конструктор
    public UserQuery(String qType, String direction, String speciality, String city, String universityName) {
        this.id = 0;

        this.queryType = qType;

        this.direction = direction;
        this.speciality = speciality;
        this.city = city;
        this.universityName = universityName;

        univDesc = null;
        facultyDesc = null;

//        this.query = getQuery();
    }

    public String getQueryType() {
        return queryType;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*
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


    public String getQuery() {
        if (queryType == QTYPE_UNIV) {
            clear();
            add(FIELD_NAME, name);
            add(FIELD_CITY, city);
            add(UNIV_PREF + FIELD_TYPE, univDesc.getType());
            add(UNIV_PREF + FIELD_CAMPUS, univDesc.getCampus());
        } else if (queryType == QTYPE_FACULTY) {
            clear();
            add(FIELD_UNIV_ID, String.valueOf(universityId));
            add(FIELD_NAME, name);
            add(FAC_PREF + FIELD_FORM, facultyDesc.getForm());
            add(FAC_PREF + FIELD_PHD, facultyDesc.getPhd());
            add(FAC_PREF + FIELD_DIP_TYPE, facultyDesc.getDiplomaType());
            add(FAC_PREF + FIELD_MILITARY, facultyDesc.getMilitary());
        }
        return query;

    }  */


    /**
     * Конструирует и возвращает строку запроса
     *
     * @done: добавить в строку запроса universityName, speciality и direction, доделать строку запроса
     */
    public String getQuery() {
        final StringBuffer stringBuffer = new StringBuffer();

        if (queryType.equals(QTYPE_UNIV)) {
            if (!universityName.equals("")) {
                stringBuffer.append(FIELD_NAME + ":").append(universityName);
                //stringBuffer.append(universityName);
            }
            /*stringBuffer.append(FIELD_CITY + ":").append(city + " ");
            stringBuffer.append(FIELD_SPECIALITY + ":").append(speciality + " ");
            stringBuffer.append(FIELD_DIRECTION + ":").append(direction + " ");  */
            if (univDesc != null) {
                stringBuffer.append(UNIV_PREF + FIELD_TYPE + ":").append(univDesc.getType() + " ");
                stringBuffer.append(UNIV_PREF + FIELD_CAMPUS + ":").append(univDesc.getCampus() + " ");
            }
        } else if (queryType.equals(QTYPE_FACULTY)) {
            /*
      add(FIELD_UNIV_ID, String.valueOf(universityId));
      add(FIELD_NAME, name);
      add(FAC_PREF + FIELD_FORM, facultyDesc.getForm());
      add(FAC_PREF + FIELD_PHD, facultyDesc.getPhd());
      add(FAC_PREF + FIELD_DIP_TYPE, facultyDesc.getDiplomaType());
      add(FAC_PREF + FIELD_MILITARY, facultyDesc.getMilitary());   */
        }

        return stringBuffer.toString();
    }

    public BooleanQuery getLuceneQuery() {
        BooleanQuery q = new BooleanQuery();
        if (!direction.equals("")) {
            q.add(new TermQuery(new Term(FIELD_DIRECTION, direction)), BooleanClause.Occur.MUST);
        }
        if (!speciality.equals("")) {
            q.add(new TermQuery(new Term(FIELD_SPECIALITY, speciality)), BooleanClause.Occur.MUST);
        }
        if (queryType.equals(QTYPE_UNIV)) {
            if (!universityName.equals("")) {
                q.add(new TermQuery(new Term(FIELD_NAME, universityName)), BooleanClause.Occur.MUST);
            }
            if (!city.equals("")) {
                q.add(new TermQuery(new Term(FIELD_CITY, city)), BooleanClause.Occur.MUST);
            }
            if (univDesc != null) {
                if (!univDesc.getType().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_TYPE)), BooleanClause.Occur.MUST);
                }
                if (!univDesc.getCampus().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_CAMPUS)), BooleanClause.Occur.MUST);
                }
            }
        } else if (queryType.equals(QTYPE_FACULTY)) {
            if (facultyDesc != null) {
                if (!facultyDesc.getForm().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_FORM)), BooleanClause.Occur.MUST);
                }
                if (!facultyDesc.getPhd().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_PHD)), BooleanClause.Occur.MUST);
                }
                if (!facultyDesc.getDiplomaType().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_DIP_TYPE)), BooleanClause.Occur.MUST);
                }
                if (!facultyDesc.getMilitary().equals("")) {
                    q.add(new TermQuery(new Term(UNIV_PREF + FIELD_MILITARY)), BooleanClause.Occur.MUST);
                }
            }
        }
        return q;
    }
}
