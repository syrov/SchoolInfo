/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.10.11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */


import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Indexer {
    String DBName;
    String user;
    String pass;
    String tableName;
    Directory dir;

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() +
            " <index dir>");
        }
        String indexDir = args[0];

        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir, "UNIINFO", "root", "mysql_1", "univercity");
        int numIndexed;
        try {
            numIndexed = indexer.index();
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");
    }

    private IndexWriter writer;

    public Indexer(String indexDir, String DBName, String user, String pass, String tableName) throws IOException {
        dir = FSDirectory.open(new File(indexDir));
        writer = new IndexWriter(dir, new RussianAnalyzer(Version.LUCENE_34),
                true, IndexWriter.MaxFieldLength.UNLIMITED);
        writer.commit();
        // инициализация необходимых полей
        this.DBName = DBName;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;

    }

    public void close() throws IOException {
        writer.close();
    }

    public int index() throws Exception {
         // установка соединения с индексируемой базой данных
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/" + DBName, user, pass);
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

        // добавление данных из базы в индекс
        while (rs.next()) {
            Document doc = new Document();
            doc.add(new Field("id", rs.getString("id"), Field.Store.YES, Field.Index.NO));
            doc.add(new Field("name", rs.getString("name"), Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field("about", rs.getString("about"), Field.Store.YES, Field.Index.ANALYZED));
            System.out.println(rs.getString("about"));
            writer.addDocument(doc);
        }
        stmt.close();
        return writer.numDocs();
    }
}
