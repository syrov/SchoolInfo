package ru.compscicenter.schoolinfo.miner;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String mainDirectory;

        if (args.length == 1) {
            mainDirectory = args[0].toString();
        } else {
            System.out.println("Please specify an input directory");
            return;
        }

        //Connect to DB
//		Database.connectToDirtyBase();
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

        String configFilePath = " /Users/alex/SchoolInfo/src/topcoder.xml";
        ScraperConfiguration config = new ScraperConfiguration(configFilePath);
        TopCoderListener listener = new TopCoderListener();
        Scraper scraper = new Scraper(config, ".");
        scraper.addRuntimeListener(listener);
        scraper.execute();
    }
}
	
	