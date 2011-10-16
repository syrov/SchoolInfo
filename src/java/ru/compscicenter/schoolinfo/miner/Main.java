package ru.compscicenter.schoolinfo.miner;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import ru.compscicenter.schoolinfo.storage.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {

    /*    String mainDirectory;

        if (args.length == 1) {
            mainDirectory = args[0].toString();
        } else {
            System.out.println("Please specify an input directory");
            return;
        }

        //Connect to DB
        try {
            Database.connectToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
//
//		//System.out.println(Database.getAllCities().size());
//		//System.in.read();
//
//		//Get a list of xml files in the input directory
//		File mainDir = new File(mainDirectory);
//		File[] listOfFiles = mainDir.listFiles(new XmlFileFilter());
//
//		//For each xml config file create Scraper object and run it
//		for (int i = 0; i < listOfFiles.length; i++) {
//
//			MyScarper myScrap = new MyScarper(mainDirectory,listOfFiles[i].getName());
//			myScrap.minerStart();
//
//		 }

        String configFilePath = "/home/natasha/SchoolInfo/src/topcoder.xml";
        ScraperConfiguration config = new ScraperConfiguration(configFilePath);
        TopCoderListener listener = new TopCoderListener();
        Scraper scraper = new Scraper(config, ".");
        scraper.addRuntimeListener(listener);
        scraper.execute();
    }
}
	
	