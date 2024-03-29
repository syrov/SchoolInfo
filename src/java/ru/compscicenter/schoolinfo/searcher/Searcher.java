package ru.compscicenter.schoolinfo.searcher;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 02.10.11
 * Time: 22:19
 */


import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import ru.compscicenter.schoolinfo.util.DBResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Searcher {

    /*public static void main(String[] args) throws IllegalArgumentException,
            IOException, ParseException {
        String indexDir = "/home/dzeta/index"; // папка для хранения индекса
        UserQuery q = new UserQuery("университет"); // запрос
        search(indexDir, q);
    }
    */

    public static ArrayList<DBResponse> startSearch(UserQuery q) throws IOException, ParseException {
        return search("/home/natasha/index", q);
    }

    public static ArrayList<DBResponse> search(String directory, UserQuery q)
            throws IOException, ParseException {
        // первичная подготовка
        Directory dir = FSDirectory.open(new File(directory));
        //IndexReader dir = IndexReader.open(FSDirectory.open(new File(directory)));
        IndexSearcher is = new IndexSearcher(dir);


        System.out.println("olololo2");

        QueryParser parser = new QueryParser(Version.LUCENE_34,
                UserQuery.F_NAME, new RussianAnalyzer(Version.LUCENE_34));

        System.out.println("olololo3");

        // первоначальный вариант, только простые запросы.
        // Для получения текста используется метод getQuery()
        Query query = parser.parse(q.getLuceneQuery().toString());

        System.out.println("olololo4");


        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 100);
        long end = System.currentTimeMillis();

        System.err.println("Found " + hits.totalHits + " document(s) (in " +
                (end - start) + " milliseconds) that matched query '" + q.getLuceneQuery() + "':");

        ArrayList<DBResponse> res = new ArrayList<DBResponse>();

        System.out.println("olololo");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            DBResponse univ = new DBResponse(Integer.parseInt(doc.get("id")), doc.get(UserQuery.F_NAME),
                    doc.get(UserQuery.F_CITY));
//            if (q.getQueryType().equals(UserQuery.Q_FACTS)) {
//                UnivDescription u = new UnivDescription(
//                        doc.get(UserQuery.UNIV_PREF + UserQuery.F_TYPE),
//                        doc.get(UserQuery.UNIV_PREF + UserQuery.F_CAMPUS));
//                univ.setUniv(u);
//            } else if (q.getQueryType().equals(UserQuery.Q_FACULTY)) {
//                FacultyDescription f = new FacultyDescription(
//                        doc.get(UserQuery.FAC_PREF + UserQuery.F_FORM),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.F_PHD),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.F_DIP_TYPE),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.F_MILITARY));
//                univ.setFac(f);
//            }
            res.add(univ);
        }

        is.close();

        return res;
    }
}