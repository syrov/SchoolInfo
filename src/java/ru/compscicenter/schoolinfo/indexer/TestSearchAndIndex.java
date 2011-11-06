package ru.compscicenter.schoolinfo.indexer;

import ru.compscicenter.schoolinfo.searcher.Searcher;
import ru.compscicenter.schoolinfo.searcher.UserQuery;
import ru.compscicenter.schoolinfo.util.DBResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 */
public class TestSearchAndIndex {
    //параметры базы и индекса
    public static String DBName = "UNIINFO";
    public static String user = "root";
    public static String pass = "Mat-mex2012";
    public static String tableName = "university";
    public static String INDEX_DIR = "/home/dzeta/index";

    public static void main(String[] args) throws Exception {
        Indexer ind = new Indexer(INDEX_DIR, DBName, user, pass, tableName);
        try {
            if (ind.index() == 0) {
                System.out.println("Ничего не проиндексировано");
                System.exit(0);
            }
        } finally {
            ind.close();
        }

        UserQuery query = new UserQuery(UserQuery.Q_FACTS, "", "", "Russian", "");
        System.out.println(query.getQuery());
        ArrayList<DBResponse> res = Searcher.search(INDEX_DIR, query);
        for (int i = 0; i < res.size(); i++) {
            DBResponse univ = res.get(i);
            System.out.println("id=" + univ.getId() + "; Name=" + univ.getName() + "; City=" + univ.getCity());
        }


    }
}
