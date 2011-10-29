package ru.compscicenter.schoolinfo.util;

import com.google.gson.Gson;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 19.10.11
 * Time: 21:23
 */

public class FacultyDescription {
    public static final String FORM_DAY = "1";
    public static final String FORM_Z = "2";
    public static final String FORM_EVENING = "3";
    public static final String HAS_PHD = "1";
    public static final String NO_PHD = "2";
    public static final String DIP_BACHELOR = "1";
    public static final String DIP_SPECIALIST = "2";
    public static final String DIP_MASTER = "3";
    public static final String HAS_MILITARY = "1";
    public static final String NO_MILITARY = "2";

    private String form;
    private String phd;
    private String diplomaType;
    private String military;

    public FacultyDescription() {
        form = phd = diplomaType = military = "";
    }

    public FacultyDescription(String form, String phd, String diplomaType, String military) {
        this.form = form;
        this.phd = phd;
        this.diplomaType = diplomaType;
        this.military = military;
    }

    public String getForm() {
        return form;
    }

    public String getPhd() {
        return phd;
    }

    public String getDiplomaType() {
        return diplomaType;
    }

    public String getMilitary() {
        return military;
    }

    public String toJson() {
        Gson gson = new Gson();
        String s = gson.toJson(this);
        return s;
    }

}
