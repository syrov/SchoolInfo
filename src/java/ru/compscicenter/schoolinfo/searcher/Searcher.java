package ru.compscicenter.schoolinfo.searcher;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 02.10.11
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */


import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import ru.compscicenter.schoolinfo.indexer.Indexer;
import ru.compscicenter.schoolinfo.util.DBResponse;
import ru.compscicenter.schoolinfo.util.UserQuery;

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

    public static ArrayList<DBResponse> search(UserQuery q) throws IOException, ParseException {
        return search("/home/natasha/index", q);
    }

    public static ArrayList<DBResponse> search(String directory, UserQuery q)
            throws IOException, ParseException {
        // первичная подготовка
        Directory dir = FSDirectory.open(new File(directory));
        IndexSearcher is = new IndexSearcher(dir);

        QueryParser parser = new QueryParser(Version.LUCENE_34,
                "about", new RussianAnalyzer(Version.LUCENE_34));

        // первоначальный вариант, только простые запросы.
        // Для получения текста используется метод getQueryExpression()
        Query query = parser.parse(q.getQuery());

        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);
        long end = System.currentTimeMillis();

        System.err.println("Found " + hits.totalHits + " document(s) (in " +
                (end - start) + " milliseconds) that matched query '" + q + "':");

        ArrayList<DBResponse> res = new ArrayList<DBResponse>();
        if (q.getQueryType().equals(UserQuery.QTYPE_UNIV)) {
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = is.doc(scoreDoc.doc);
                //System.out.println(doc.get("name") + " " + doc.get("about"));
                DBResponse univ = new DBResponse(Integer.parseInt(doc.get("id")), doc.get("name"), doc.get("city"));
                res.add(univ);
            }
        }

        is.close();

        return res;
    }
}