package ru.compscicenter.schoolinfo.gui.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.db.AbstractDbYalet;

import ru.compscicenter.schoolinfo.gui.models.Manager;
import ru.compscicenter.schoolinfo.searcher.UnivRecord;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 09.10.11
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class UserQueryYalet extends AbstractDbYalet {

    protected Manager manager = new Manager(); // добавить потом модель Manager

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void process(InternalRequest req, InternalResponse res) {
        final String dir = req.getParameter("dir");   // названия текстовых полей в скобках, см. хсл
        final String city = req.getParameter("city");
        final String uni = req.getParameter("uni");

        ArrayList<UnivRecord> result = manager.getSearchResult(dir, city, uni);
//      log.error(result + "RESULT");
        if (result.size() == 0) {
            UnivRecord rec = new UnivRecord(0, "Error", "Error");
            result.add(rec);
        }

        res.add(result);
    }
}
