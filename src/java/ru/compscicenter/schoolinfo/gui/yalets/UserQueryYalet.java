package ru.compscicenter.schoolinfo.gui.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.db.AbstractDbYalet;

import ru.compscicenter.schoolinfo.gui.models.UserQueryManager;
import ru.compscicenter.schoolinfo.searcher.UnivRecord;
import ru.compscicenter.schoolinfo.searcher.UnivRecordComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 09.10.11
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class UserQueryYalet extends AbstractDbYalet {

    protected UserQueryManager manager = new UserQueryManager();

    public void setManager(UserQueryManager manager) {
        this.manager = manager;
    }

    public void process(InternalRequest req, InternalResponse res) {
        final String dir = req.getParameter("dir");   // названия текстовых полей в скобках, см. хсл
        final String city = req.getParameter("city");
        final String uni = req.getParameter("uni");

        ArrayList<UnivRecord> result = manager.getSearchResult(dir, city, uni);
//      log.error(result + "RESULT");
        if (result.size() == 0) {
            UnivRecord rec = new UnivRecord(0, "Error", "No information found");
            result.add(rec);
        }

        else if (uni == null)  {
            ArrayList<UnivRecord> rec = new ArrayList<UnivRecord>(5);

// тут надо ещё отсортировать result, позже следует удалить Comparator и сортировать данные при выборке из базы (searcher)
            Collections.sort(result, new UnivRecordComparator());

            for(int i = 0; i < 5; i ++)
                rec.add(result.get(i));

            result = rec;
        }

        res.add(result);
    }
}
