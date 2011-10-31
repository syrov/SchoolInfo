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
import java.util.List;

import static ru.compscicenter.schoolinfo.searcher.Searcher.startSearch;

public class UserQueryManager {
//    Logger log = Logger.getLogger(UserQueryManager.class);

    //Получение данных из базы (обращение к searcher'у)
    public List<DBResponse> getSearchResult(String dir, String spec, String city, String uni) throws IOException, ParseException {
        List<DBResponse> result = new ArrayList<DBResponse>();
// обращение к searcher'у
        if (dir == null && city == null && uni == null && spec == null)
            return result;

        //     Searcher.setIndexDir(new File("index"));

        // String IndexDir = "/home/natasha/index"; // подлежит корректировке

        UserQuery q = new UserQuery("university", dir, spec, city, uni);


        System.out.println("olololo1");
        result = startSearch(q);

        System.out.println(result.size());
        //result = Searcher.search(IndexDir, q);

        if (result.size() == 0) {
            DBResponse rec = new DBResponse(0, "Error", "No information found");
            result.add(rec);
        }

        System.out.println(result.get(0).getName());
        return result;
    }
}