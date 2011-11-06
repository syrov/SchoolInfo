package ru.compscicenter.schoolinfo.util;

import com.google.gson.Gson;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 16.10.11
 * Time: 15:34
 */

public class UnivDescription {

    public static final String UNIV_GOS = "1";
    public static final String UNIV_NEGOS = "2";
    public static final String HAS_CAMPUS = "1";
    public static final String NO_CAMPUS = "2";

    private String type;
    private String campus;

    public UnivDescription(String type, String campus) {
        this.type = type;
        this.campus = campus;
    }

    public String getType() {
        return type;
    }

    public String getCampus() {
        return campus;
    }

    public String toJson() {
        Gson gson = new Gson();
        String s = gson.toJson(this);
        return s;
    }

}