package ru.compscicenter.schoolinfo.gui.models;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 10.10.11
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;

import java.util.*;


import ru.compscicenter.schoolinfo.searcher.*;
import ru.compscicenter.schoolinfo.storage.*;

public class Manager {
    Logger log = Logger.getLogger(Manager.class);

 /*   public static enum DatabaseEnum {
        mainDB,
        dirtyDB
    }

    public static DatabaseEnum databaseName = DatabaseEnum.mainDB; */

    public void connectToDatabase() {

  /*      if (Manager.databaseName == DatabaseEnum.dirtyDB) {
            Database.connectToDirtyBase();
        } else {
            Database.connectToMainBase();
        }     */

        Database.connectToDB();
    }

    //Получение данных из базы
    public ArrayList<UnivRecord> getSearchResult(String div, String city, String uni) {
        ArrayList<UnivRecord> result = new ArrayList<UnivRecord>();
// обращение к searcher'у, код будет добавлен позже
        return result;
    }
}
