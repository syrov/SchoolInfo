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
import ru.compscicenter.schoolinfo.gui.models.DBResponse;
import ru.compscicenter.schoolinfo.util.FacultyDescription;
import ru.compscicenter.schoolinfo.util.UnivDescription;

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

    public static ArrayList<DBResponse> search(String directory, UserQuery q)
            throws IOException, ParseException {
        // первичная подготовка
        Directory dir = FSDirectory.open(new File(directory));
        //IndexReader dir = IndexReader.open(FSDirectory.open(new File(directory)));
        IndexSearcher is = new IndexSearcher(dir);

        QueryParser parser = new QueryParser(Version.LUCENE_34,
                UserQuery.FIELD_NAME, new RussianAnalyzer(Version.LUCENE_34));

        // первоначальный вариант, только простые запросы.
        // Для получения текста используется метод getQuery()
        Query query = parser.parse(q.getLuceneQuery().toString());

        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 100);
        long end = System.currentTimeMillis();

        System.out.println("Found " + hits.totalHits + " document(s) (in " +
                (end - start) + " milliseconds) that matched query '" + q.getLuceneQuery() + "':");

        ArrayList<DBResponse> res = new ArrayList<DBResponse>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            //System.out.println(doc.get("name") + " " + doc.get("about"));
            DBResponse univ = new DBResponse(Integer.parseInt(doc.get("id")), doc.get(UserQuery.FIELD_NAME),
                    doc.get(UserQuery.FIELD_CITY));
//            if (q.getQueryType().equals(UserQuery.QTYPE_UNIV)) {
//                UnivDescription u = new UnivDescription(
//                        doc.get(UserQuery.UNIV_PREF + UserQuery.FIELD_TYPE),
//                        doc.get(UserQuery.UNIV_PREF + UserQuery.FIELD_CAMPUS));
//                univ.setUniv(u);
//            } else if (q.getQueryType().equals(UserQuery.QTYPE_FACULTY)) {
//                FacultyDescription f = new FacultyDescription(
//                        doc.get(UserQuery.FAC_PREF + UserQuery.FIELD_FORM),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.FIELD_PHD),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.FIELD_DIP_TYPE),
//                        doc.get(UserQuery.FAC_PREF + UserQuery.FIELD_MILITARY));
//                univ.setFac(f);
//            }
            res.add(univ);
        }

        is.close();

        return res;
    }
}