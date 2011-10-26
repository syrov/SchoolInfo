package ru.compscicenter.schoolinfo.indexer;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class TestSearchAndIndex {
    // Параметры базы и индекса. Необходимо изменить
    public static String DBName = "UNIINFO";
    public static String user = "root";
    public static String pass = "Mat-mex2012";
    public static String tableName = "university";
    public static String INDEX_DIR = "/home/natasha/index";

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
//
//        ArrayList<DBResponse> res = Searcher.search(INDEX_DIR, new UserQuery("университет"));
//        for (int i = 0; i < res.size(); i++) {
//            DBResponse univ = res.get(i);
//            System.out.println("id=" + univ.getId() + "; Name=" + univ.getName() + "; About:\n" + univ.getInfo() + "\n");
//        }


    }
}
