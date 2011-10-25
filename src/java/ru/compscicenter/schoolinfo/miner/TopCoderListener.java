package ru.compscicenter.schoolinfo.miner;

import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;
import ru.compscicenter.schoolinfo.storage.Database;
import ru.compscicenter.schoolinfo.storage.University;
import ru.compscicenter.schoolinfo.util.GetUniversityCity;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pritykovskaya
 * Date: 16.10.11
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class TopCoderListener implements ScraperRuntimeListener {

    Database database;

    public TopCoderListener(Database database) {
        this.database = database;
    }

    public void onExecutionStart(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionPaused(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionContinued(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onNewProcessorExecution(Scraper scraper, BaseProcessor baseProcessor) {
        if ("empty".equalsIgnoreCase(scraper.getRunningProcessor().getElementDef().getShortElementName())) {
             Variable universityName = (Variable) scraper.getContext().get("universityName");

            if (universityName != null) {
                University uni = new University(universityName.toString());
                try {
                    database.addUniversity(uni);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            /*   String sUniversityName = clearString(universityName);
  System.out.println(sUniversityName);          */

          /*
            String countryName = (String) scraper.getContext().get("universityCountry");
            if (countryName.equals("Russian Federation")) {
                try {
                    String universityName = (String) scraper.getContext().get("universityName");
                    database.addUniversity(new University(
                            universityName,
                            new GetUniversityCity().getCity(universityName),
                            "No description"
                    ));
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            */
        }
    }

    public void onExecutionEnd(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onProcessorExecutionFinished(Scraper scraper, BaseProcessor baseProcessor, Map map) {
        // System.out.println(map);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionError(Scraper scraper, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private String clearString(Variable incomed) {

        if (incomed != null) {
            if (incomed.toString().equals(""))
                return null;
            String toClear = incomed.toString();
            int a, b;
            while (toClear.indexOf('<') != (-1)) {
                a = toClear.indexOf('<');
                b = toClear.indexOf('>');
                if (b > a) {
                    toClear = toClear.substring(0, a) + toClear.substring(b, toClear.length());
                    toClear = toClear.replaceFirst(">", " ");
                }
            }
            toClear = toClear.replaceAll("\n", " ");
            toClear = toClear.replaceAll("\t", "");
            toClear = toClear.replaceAll(" {2,}", " ");
            toClear = toClear.replaceAll(" {1,}[.]", ".");
            toClear = toClear.replaceAll(" {1,}[,]", ",");
            return toClear;
        } else
            return null;
    }

}
