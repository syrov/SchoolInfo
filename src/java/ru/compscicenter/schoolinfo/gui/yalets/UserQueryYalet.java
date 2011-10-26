package ru.compscicenter.schoolinfo.gui.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.core.Yalet;
import org.apache.lucene.queryParser.ParseException;
import ru.compscicenter.schoolinfo.gui.models.UserQueryManager;
import ru.compscicenter.schoolinfo.util.DBResponseComparator;
import ru.compscicenter.schoolinfo.util.DBResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: Alexandra Mikhaylova
 */

public class UserQueryYalet implements Yalet {

    protected UserQueryManager manager = new UserQueryManager();

    public void setManager(UserQueryManager manager) {
        this.manager = manager;
    }

    public void process(InternalRequest req, InternalResponse res) {
        final String dir = req.getParameter("dir");   // названия текстовых полей в скобках, см. хсл
        final String spec = req.getParameter("spec");
        final String city = req.getParameter("city");
        final String uni = req.getParameter("uni");

        ArrayList<DBResponse> result = null;
        try {
            result = manager.getSearchResult(dir, spec, city, uni);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//      log.error(result + "RESULT");
        if (result.size() == 0) {
            DBResponse rec = new DBResponse(0, "Error", "No information found");
            result.add(rec);
//      в случае, когда университет не указан, пользователю выдается рейтинг
        } else if (uni == null) {
            ArrayList<DBResponse> rec = new ArrayList<DBResponse>(5);

 /*           Collections.sort(result, new DBResponseComparator());

            for (int i = 0; i < 5; i++)
                rec.add(result.get(i));      */

            result = rec;
        }

        res.add(result);
    }
}
