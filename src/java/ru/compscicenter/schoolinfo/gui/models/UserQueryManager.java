package ru.compscicenter.schoolinfo.gui.models;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;


import org.apache.lucene.queryParser.ParseException;
import ru.compscicenter.schoolinfo.searcher.*;
import ru.compscicenter.schoolinfo.storage.*;

public class UserQueryManager {
//    Logger log = Logger.getLogger(UserQueryManager.class);

    //Получение данных из базы (обращение к searcher'у)
    public ArrayList<UnivRecord> getSearchResult(String dir, String city, String uni) {
        ArrayList<UnivRecord> result = new ArrayList<UnivRecord>();
// обращение к searcher'у
        if(dir == null && city == null && uni == null)
            return result;

   //     Searcher.setIndexDir(new File("index"));

        String IndexDir = "/home/index"; // пождлежит корректировке

        if (uni != null)
            result = Searcher.search(IndexDir, new UserQuery(uni));
        else if (city != null)
            result = Searcher.search(IndexDir, new UserQuery(city));
        else
            result = Searcher.search(IndexDir, new UserQuery(dir));

        if (result.size() == 0) {
            UnivRecord rec = new UnivRecord(0, "Error", "No information found");
            result.add(rec);
        }

        return result;
    }
}
