package ru.compscicenter.schoolinfo.gui.models;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 14:21
 */

import org.apache.lucene.queryParser.ParseException;
import ru.compscicenter.schoolinfo.searcher.Searcher;
import ru.compscicenter.schoolinfo.searcher.UserQuery;
import ru.compscicenter.schoolinfo.util.DBResponse;

import java.io.IOException;
import java.util.ArrayList;

public class UserQueryManager {
//    Logger log = Logger.getLogger(UserQueryManager.class);

    //Получение данных из базы (обращение к searcher'у)
    public ArrayList<DBResponse> getSearchResult(String dir, String spec, String city, String uni) throws IOException, ParseException {
        ArrayList<DBResponse> result = new ArrayList<DBResponse>();
// обращение к searcher'у
        if (dir == null && city == null && uni == null && spec == null)
            return result;

        //     Searcher.setIndexDir(new File("index"));

     //   String IndexDir = "/home/index"; // подлежит корректировке

        UserQuery q = new UserQuery("university", dir, spec, city, uni);

 //       result = Searcher.search(q);

        if (result.size() == 0) {
            DBResponse rec = new DBResponse(0, "Error", "No information found");
            result.add(rec);
        }

        return result;
    }
}
