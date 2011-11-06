package ru.compscicenter.schoolinfo.indexer;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import ru.compscicenter.schoolinfo.searcher.UserQuery;
import ru.compscicenter.schoolinfo.util.FacultyDescription;
import ru.compscicenter.schoolinfo.util.UnivDescription;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.10.11
 * Time: 21:10
 */


public class Indexer {

    private String DBName;
    private String user;
    private String pass;
    private String tableName;
    private Connection conn;

    private IndexWriter writer;
    //public static Directory dir;

    public Indexer(String indexDir, String DBName, String user, String pass, String tableName) throws IOException {
        FileUtils.deleteDirectory(new File(indexDir));
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_34, new RussianAnalyzer(Version.LUCENE_34));
        writer = new IndexWriter(dir, conf);
        //writer.commit();

        // инициализация необходимых полей
        this.DBName = DBName;
        this.user = user;
        this.pass = pass;
        this.tableName = tableName;

    }

    public void close() throws IOException {
        writer.close();
    }

    /*
     * Индексация базы данных, с параметрами указанными при создании
     */
    public int index() throws Exception {
        // установка соединения с индексируемой базой данных
        conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName, user, pass);
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement stmt = conn.createStatement();
        String sqlQuery = "SELECT * FROM table_of_facts ORDER BY id_fac";
        ResultSet rs = stmt.executeQuery(sqlQuery);

        //добавление данных в индекс
        while (rs.next()) {
            Document doc = getDocument(rs);
            writer.addDocument(doc);
        }
        stmt.close();
        //writer.commit();
        close();
        return writer.numDocs();
    }

    Document getDocument(ResultSet rs) throws SQLException {
        Document doc = new Document();

        doc.add(new Field("id", rs.getString("id"), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("id_fac", rs.getString("id_fac"), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("id_univ", rs.getString("id_univ"), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("id_res", rs.getString("id_res"), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("id_method", rs.getString("id_method"), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("city", rs.getString("city"), Field.Store.YES, Field.Index.ANALYZED));

        //достаем описание университета
        Statement tempStatement = conn.createStatement();
        ResultSet tmp = tempStatement.executeQuery("SELECT * FROM university WHERE id=id_univ");
        tmp.next();
        doc.add(new Field("univ.name", tmp.getString("name"), Field.Store.YES, Field.Index.ANALYZED));
        if (rs.getString("description") != null) {
            Gson gson = new Gson();
            UnivDescription u = gson.fromJson(rs.getString("description"), UnivDescription.class);
            if (u != null) {
                doc.add(new Field("univ.type", u.getType(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("univ.campus", u.getCampus(), Field.Store.YES, Field.Index.ANALYZED));
            }
        }

        //достаем описание факультета
        tmp = tempStatement.executeQuery("SELECT * FROM faculty WHERE id=id_fac");
        tmp.next();
        doc.add(new Field("fac.name", tmp.getString("name"), Field.Store.YES, Field.Index.ANALYZED));
        if (rs.getString("description") != null) {
            Gson gson = new Gson();
            FacultyDescription u = gson.fromJson(rs.getString("description"), FacultyDescription.class);
            if (u != null) {
                doc.add(new Field("fac.diptype", u.getDiplomaType(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("fac.form", u.getForm(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("fac.military", u.getMilitary(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("fac.phd", u.getPhd(), Field.Store.YES, Field.Index.ANALYZED));
            }
        }

        return doc;
    }
}


//        if (!ids.equals("")) {
//            //достаем список направлений в данном университете
//            tmp = tempStatement.executeQuery("SELECT * FROM direction WHERE id IN " +
//                    "(SELECT id FROM speciality WHERE id IN (" + ids.substring(1) + "))");
//            //"(SELECT speciality_id FROM speciality_faculty WHERE faculty_id IN " +
//            //"(SELECT id FROM faculty WHERE university_id=" + rs.getString("id") + ")))");
//            String direction = "";
//            while (tmp.next()) {
//                direction += tmp.getString("name") + "; ";
//            }
//            doc.add(new Field(UserQuery.F_DIRECTION, direction, Field.Store.YES, Field.Index.ANALYZED));
//        }