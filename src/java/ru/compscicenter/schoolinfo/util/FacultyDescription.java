package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 19.10.11
 * Time: 21:23
 */

public class FacultyDescription {
    public static final byte FORM_DAY = 1;
    public static final byte FORM_Z = 2;
    public static final byte FORM_EVENING = 3;
    public static final byte HAS_PHD = 1;
    public static final byte NO_PHD = 2;
    public static final byte DIP_BACHELOR = 1;
    public static final byte DIP_SPECIALIST = 2;
    public static final byte DIP_MASTER = 3;
    public static final byte HAS_MILITARY = 1;
    public static final byte NO_MILITARY = 2;

    private byte form;
    private byte phd;
    private byte diplomaType;
    private byte military;

    public FacultyDescription() {
        form = phd = diplomaType = military = 0;
    }

    public FacultyDescription(byte form, byte phd, byte diplomaType, byte military) {
        this.form = form;
        this.phd = phd;
        this.diplomaType = diplomaType;
        this.military = military;
    }

    public String getForm() {
        return Byte.toString(form);
    }

    public String getPhd() {
        return Byte.toString(phd);
    }

    public String getDiplomaType() {
        return Byte.toString(diplomaType);
    }

    public String getMilitary() {
        return Byte.toString(military);
    }

}
