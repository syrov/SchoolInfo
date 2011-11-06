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
    public static final String Q_FACULTY = "faculty";
    public static final String Q_FACTS = "table_of_facts";
    public static final String F_DIRECTION = "direction";
    public static final String F_SPECIALITY = "speciality";
    public static final String F_CITY = "city";
    public static final String F_NAME = "name";
    public static final String F_DESCRIPTION = "description";
    public static final String F_TYPE = "type";
    public static final String F_CAMPUS = "campus";
    public static final String FIELD_UNIV_ID = "university_id";
    public static final String F_FORM = "form";
    public static final String F_PHD = "phd";
    public static final String F_DIP_TYPE = "diploma_type";
    public static final String F_MILITARY = "military";
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

    /**
     * Конструирует и возвращает строку запроса
     *
     * @done: добавить в строку запроса universityName, speciality и direction, доделать строку запроса
     */
    public String getQuery() {
        final StringBuffer stringBuffer = new StringBuffer();

        if (queryType.equals(Q_FACTS)) {
            if (!universityName.equals("")) {
                stringBuffer.append(F_NAME + ":").append(universityName);
                //stringBuffer.append(universityName);
            }
            /*stringBuffer.append(F_CITY + ":").append(city + " ");
            stringBuffer.append(F_SPECIALITY + ":").append(speciality + " ");
            stringBuffer.append(F_DIRECTION + ":").append(direction + " ");  */
            if (univDesc != null) {
                stringBuffer.append(UNIV_PREF + F_TYPE + ":").append(univDesc.getType() + " ");
                stringBuffer.append(UNIV_PREF + F_CAMPUS + ":").append(univDesc.getCampus() + " ");
            }
        } else if (queryType.equals(Q_FACULTY)) {
            /*
      add(FIELD_UNIV_ID, String.valueOf(universityId));
      add(F_NAME, name);
      add(FAC_PREF + F_FORM, facultyDesc.getForm());
      add(FAC_PREF + F_PHD, facultyDesc.getPhd());
      add(FAC_PREF + F_DIP_TYPE, facultyDesc.getDiplomaType());
      add(FAC_PREF + F_MILITARY, facultyDesc.getMilitary());   */
        }

        return stringBuffer.toString();
    }

    public BooleanQuery getLuceneQuery() {
        BooleanQuery q = new BooleanQuery();
        if (direction != null) {
            q.add(new TermQuery(new Term(F_DIRECTION, "\"" + direction + "\"")), BooleanClause.Occur.MUST);
        }
        if (!speciality.equals("")) {
            q.add(new TermQuery(new Term(F_SPECIALITY, "\"" + speciality + "\"")), BooleanClause.Occur.MUST);
        }
        if (queryType.equals(Q_FACTS)) {
            if (universityName != null) {
                q.add(new TermQuery(new Term(F_NAME, "\"" + universityName + "\"")), BooleanClause.Occur.MUST);
            }
            if (city != null) {
                q.add(new TermQuery(new Term(F_CITY, "\"" + city + "\"")), BooleanClause.Occur.MUST);
            }
            if (univDesc != null) {
                if (univDesc.getType() != null) {
                    q.add(new TermQuery(new Term("univ.type", univDesc.getType())), BooleanClause.Occur.MUST);
                }
                if (univDesc.getCampus() != null) {
                    q.add(new TermQuery(new Term("univ.campus", univDesc.getCampus())), BooleanClause.Occur.MUST);
                }
            }
        }
        return q;
    }
}
