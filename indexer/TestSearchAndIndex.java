import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class TestSearchAndIndex {
    public static String DBName = "UNIINFO";
    public static String user = "root";
    public static String pass = "mysql_1";
    public static String tableName = "univercity";

    public static void main(String[] args) throws Exception {
        Indexer ind = new Indexer("index", DBName, user, pass, tableName);
        if (ind.index() == 0) {
            System.out.println("Ничего не проиндексировано");
            System.exit(0);
        }

        ArrayList<UnivRecord> res = Searcher.search("index", "университет");
        ind.close();
        for(int i = 0 ; i < res.size() ; i++) {
            UnivRecord univ = res.get(i);
            System.out.println("id=" + univ.getId() + "; Name=" + univ.getName() + "; About:\n" + univ.getInfo());
        }

    }
}
