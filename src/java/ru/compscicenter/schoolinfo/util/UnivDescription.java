package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 16.10.11
 * Time: 15:34
 */

public class UnivDescription {

    public static final byte UNIV_GOS = 1;
    public static final byte UNIV_NEGOS = 2;
    public static final byte HAS_CAMPUS = 1;
    public static final byte NO_CAMPUS = 2;

    private byte type;
    private byte campus;

    public UnivDescription() {
        type = campus = 0;
    }

    public UnivDescription(byte type, byte campus) {
        this.type = type;
        this.campus = campus;
    }

    public String getType() {
        return Byte.toString(type);
    }

    public String getCampus() {
        return Byte.toString(campus);
    }

}