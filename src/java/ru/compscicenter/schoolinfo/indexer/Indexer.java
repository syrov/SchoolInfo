package ru.compscicenter.schoolinfo.indexer;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import ru.compscicenter.schoolinfo.searcher.UserQuery;
import ru.compscicenter.schoolinfo.util.FacultyDescription;
import ru.compscicenter.schoolinfo.util.UnivDescription;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

    /*public static void main(String[] args) throws Exception {
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
    }  */

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
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/" + DBName, user, pass);
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

        // добавление данных из базы в индекс, в зависимости от типа индексируемой таблицы
        // одинакового кода много, наверное можно это сократить. Просто пока всё предельно понятно
        // todo: упростить запросы
        if (tableName.equals(UserQuery.QTYPE_UNIV)) {
            while (rs.next()) {
                Document doc = new Document();
                doc.add(new Field("id", rs.getString("id"), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(UserQuery.FIELD_NAME, rs.getString("name"), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field(UserQuery.FIELD_CITY, rs.getString("city"), Field.Store.YES, Field.Index.ANALYZED));

                //достаем список направлений в данном университете
                Statement tempStatement = conn.createStatement();
                ResultSet tmp = tempStatement.executeQuery("SELECT * FROM direction WHERE id IN " +
                        "(SELECT id FROM speciality WHERE id IN " +
                        "(SELECT speciality_id FROM speciality_faculty WHERE faculty_id IN " +
                        "(SELECT id FROM faculty WHERE university_id=" + rs.getString("id") + ")))");
                String direction = "";
                while (tmp.next()) {
                    direction += tmp.getString("name") + "; ";
                }
                doc.add(new Field(UserQuery.FIELD_DIRECTION, direction, Field.Store.YES, Field.Index.ANALYZED));

                //достаем список специальностей в данном университете
                tmp = tempStatement.executeQuery("SELECT * FROM speciality WHERE id IN " +
                        "(SELECT speciality_id FROM speciality_faculty WHERE faculty_id IN " +
                        "(SELECT id FROM faculty WHERE university_id=" + rs.getString("id") + "))");
                String spec = "";
                while (tmp.next()) {
                    spec += tmp.getString("name") + "; ";
                }
                doc.add(new Field(UserQuery.FIELD_SPECIALITY, spec, Field.Store.YES, Field.Index.ANALYZED));

                Gson gson = new Gson();
                if (!rs.getString("description").equals("null")) {
                    UnivDescription u = gson.fromJson(rs.getString("description"), UnivDescription.class);
                    doc.add(new Field(UserQuery.UNIV_PREF + UserQuery.FIELD_TYPE, u.getType(),
                            Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field(UserQuery.UNIV_PREF + UserQuery.FIELD_CAMPUS, u.getCampus(),
                            Field.Store.YES, Field.Index.ANALYZED));

                }
                writer.addDocument(doc);
            }
        } else if (tableName.equals(UserQuery.QTYPE_FACULTY)) {
            while (rs.next()) {
                Document doc = new Document();
                doc.add(new Field("id", rs.getString("id"), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(UserQuery.FIELD_NAME, rs.getString("name"), Field.Store.YES, Field.Index.ANALYZED));

                 //достаем список направлений на данном факультете
                Statement tempStatement = conn.createStatement();
                ResultSet tmp = tempStatement.executeQuery("SELECT * FROM direction WHERE id IN " +
                        "(SELECT id FROM speciality WHERE id IN " +
                        "(SELECT speciality_id FROM speciality_faculty WHERE faculty_id=" + rs.getString("id") + "))");
                String direction = "";
                while (tmp.next()) {
                    direction += tmp.getString("name") + "; ";
                }
                doc.add(new Field(UserQuery.FIELD_DIRECTION, direction, Field.Store.YES, Field.Index.ANALYZED));

                //достаем список специальностей на данном факультете
                tmp = tempStatement.executeQuery("SELECT * FROM speciality WHERE id IN " +
                        "(SELECT speciality_id FROM speciality_faculty WHERE faculty_id=" + rs.getString("id") + "))");
                String spec = "";
                while (tmp.next()) {
                    spec += tmp.getString("name") + "; ";
                }
                doc.add(new Field(UserQuery.FIELD_SPECIALITY, spec, Field.Store.YES, Field.Index.ANALYZED));

                Gson gson = new Gson();
                FacultyDescription u = gson.fromJson(rs.getString("description"), FacultyDescription.class);
                doc.add(new Field(UserQuery.FAC_PREF + UserQuery.FIELD_FORM, u.getForm(), Field.Store.YES,
                        Field.Index.ANALYZED));

                doc.add(new Field(UserQuery.FAC_PREF + UserQuery.FIELD_PHD, u.getPhd(), Field.Store.YES,
                        Field.Index.ANALYZED));

                doc.add(new Field(UserQuery.FAC_PREF + UserQuery.FIELD_DIP_TYPE, u.getDiplomaType(),
                        Field.Store.YES, Field.Index.ANALYZED));

                doc.add(new Field(UserQuery.FAC_PREF + UserQuery.FIELD_MILITARY, u.getMilitary(),
                        Field.Store.YES, Field.Index.ANALYZED));

                writer.addDocument(doc);
            }
        }
        stmt.close();
        //writer.commit();
        close();
        //dir.close();
        return writer.numDocs();
    }
}
