package ru.compscicenter.schoolinfo.util;

/**
 * Created by IntelliJ IDEA.
 * User: dzeta
 * Date: 16.10.11
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */

public class UnivDescription {

    public static final byte UNIV_GOS = 1;
    public static final byte UNIV_NEGOS = 2;
    public static final byte HAS_CAMPUS = 1;
    public static final byte HAS_NOT_CAMPUS = 2;

    // type == 1, если университет государственный
    // type == 2, если университет негосударственный
    // type == 0, если это не имеет значения
    private byte type;

    // campus == 1, если общежития нету
    // campus == 2, если общежитие есть
    // campus == 0, если это не имеет значения
    private byte campus;

    public UnivDescription() {
        type = campus = 0;
    }

    public UnivDescription(byte type, byte campus) {
        this.type = type;
        this.campus = campus;
    }

    public byte getType() {
        return type;
    }

    public byte getCampus() {
        return campus;
    }

}