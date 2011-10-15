package ru.compscicenter.schoolinfo.gui.models;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

import ru.compscicenter.schoolinfo.searcher.DBRecord;
import ru.compscicenter.schoolinfo.searcher.Searcher;
import ru.compscicenter.schoolinfo.searcher.UserQuery;

import java.util.ArrayList;

public class UserQueryManager {
//    Logger log = Logger.getLogger(UserQueryManager.class);

    //Получение данных из базы (обращение к searcher'у)
    public ArrayList<DBRecord> getSearchResult(String dir, String city, String uni) {
        ArrayList<DBRecord> result = new ArrayList<DBRecord>();
// обращение к searcher'у
        if (dir == null && city == null && uni == null)
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
            DBRecord rec = new DBRecord(0, "Error", "No information found");
            result.add(rec);
        }

        return result;
    }
}
