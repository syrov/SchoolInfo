package ru.compscicenter.schoolinfo.util;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 11.10.11
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class DBRecordComparator implements Comparator {

    public int compare(Object urec1, Object urec2) {

        DBResponse rec1 = (DBResponse) urec1;
        DBResponse rec2 = (DBResponse) urec2;

        int ratingComp;

        if (rec1.getRating() == rec2.getRating())
            ratingComp = 0;
        else if (rec1.getRating() > rec2.getRating())
            ratingComp = 1;
        else
            ratingComp = -1;

        return ratingComp;
    }
}
