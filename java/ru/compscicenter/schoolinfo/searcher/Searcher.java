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

import lib.webharvester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Searcher {

    public static void main(String[] args) throws IllegalArgumentException,
            IOException, ParseException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java " + Searcher.class.getName()
            + " <index dir> <query>");
        }
        String indexDir = "/home/dzeta/index";
        String q = "университет";
        search(indexDir, q);
    }

    public static ArrayList<UnivRecord> search(String indexDir, String q)
        throws IOException, ParseException {
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexSearcher is = new IndexSearcher(dir);
        QueryParser parser = new QueryParser(Version.LUCENE_34,
                "about", new RussianAnalyzer(Version.LUCENE_34));
        Query query = parser.parse(q);
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);
        long end = System.currentTimeMillis();

        System.err.println("Found " + hits.totalHits + " document(s) (in " +
                (end - start) + " milliseconds) that matched query '" + q + "':");

        ArrayList<UnivRecord> res = new ArrayList<UnivRecord>();
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            //System.out.println(doc.get("name") + " " + doc.get("about"));
            UnivRecord univ = new UnivRecord(Integer.parseInt(doc.get("id")), doc.get("name"), doc.get("about"));
            res.add(univ);
        }

        is.close();
        return res;
    }
}