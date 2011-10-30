package ru.compscicenter.schoolinfo.miner;

import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;
import ru.compscicenter.schoolinfo.storage.*;
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

            if ( universityName != null && !universityName.toString().equals("") ) {

//                String uni_city = universityName.toString();
//                if (uni_city.indexOf(" ") != -1) {
//                    uni_city = uni_city.substring(0, uni_city.indexOf(" "));
//                }

                Variable universityCountry = (Variable) scraper.getContext().get("universityCountry");

                University uni = new University(universityName.toString(), universityCountry.toString(), "");

                try {
                    database.addUniversity(uni);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Faculty faculty = new Faculty("mathematics", "");

                try {
                    database.addFaculty(faculty);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

               Speciality_faculty spec_fac = new Speciality_faculty(1,0);

                try {
                    database.addSpeciality_faculty(spec_fac);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Variable universityRank = (Variable) scraper.getContext().get("universityRank");
                int uniRank = universityRank.toInt();

                //System.out.println(uniRank);
                Ranking_result ranking_result = new Ranking_result(uniRank);

                try {
                    database.addRanking_result(ranking_result);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Variable universityRawInfo = (Variable) scraper.getContext().get("universityRating");
                double uniRawInfo = universityRawInfo.toDouble();

                //System.out.println(uniRawInfo);
                Ranking_raw_info_result ranking_raw_info_result = new Ranking_raw_info_result(uniRawInfo);

                try {
                    database.addRanking_raw_info_result(ranking_raw_info_result);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }

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