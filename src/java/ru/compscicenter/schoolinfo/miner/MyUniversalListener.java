package java.ru.compscicenter.schoolinfo.miner;

import org.htmlcleaner.CompactXmlSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;


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