package miner;

import org.htmlcleaner.CompactXmlSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MyUniversalListener implements ScraperRuntimeListener  {
		
	public void onNewProcessorExecution(Scraper scraper, BaseProcessor arg1) {}
	public void onExecutionContinued(Scraper arg0) {}	
	public void onExecutionError(Scraper arg0, Exception arg1) {}
	public void onExecutionPaused(Scraper arg0) {}
	public void onExecutionStart(Scraper arg0) {}
	public void onExecutionEnd(Scraper scraper) {}


	DataCleaner fcl;
	HtmlCleaner cleaner;
	CompactXmlSerializer cxs;
	
	public MyUniversalListener(){
		 fcl = new DataCleaner();
 		 cleaner = new HtmlCleaner();
 		 cxs = new CompactXmlSerializer(cleaner.getProperties());
	}
	
	
	public void onProcessorExecutionFinished(Scraper scr, BaseProcessor bp, @SuppressWarnings("rawtypes") Map arg2) {
			
	//		Database.add(newEntry);          где Entry имеет тип UnitRecord
		}
	}