package ru.compscicenter.schoolinfo.gui.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.core.Yalet;
import org.apache.lucene.queryParser.ParseException;
import ru.compscicenter.schoolinfo.gui.models.DBResponse;
import ru.compscicenter.schoolinfo.gui.services.UserQueryManager;

import java.io.IOException;
import java.util.List;

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
        final String dir = req.getParameter("_dir");   // названия текстовых полей в скобках, см. хсл
        final String spec = req.getParameter("_spec");
        final String city = req.getParameter("_city");
        final String uni = req.getParameter("_uni");

//        System.out.println("arrived at yalet");
//        System.out.println(uni);

        List<DBResponse> result = null;
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
        }

//        String a;
//        if(uni.equals("3"))   a = result.get(0).getCity();
//        else a = result.get(0).getName();
//          System.out.println(result.get(0).getId());
          res.add(result);
//        res.add(result.toString() + " sorry for such an output, later it will be transformed");
//        System.out.println("finished");
    }
}
