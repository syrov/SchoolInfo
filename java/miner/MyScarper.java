package miner;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.IOException;

public class MyScarper {

	private final String path;
	private final String configFile;
		
	public MyScarper(String path, String configFile){
		this.path = path;
		this.configFile = configFile;
	}

	public void minerStart() throws IOException{
												
		ScraperConfiguration config = new ScraperConfiguration(path + configFile);
		MyUniversalListener listener = new MyUniversalListener();
		Scraper scraper = new Scraper(config, path);
		scraper.addRuntimeListener(listener);
		scraper.addVariableToContext("addToDB", 0);
		//If you have proxy - uncomment this line
		//scraper.getHttpClientManager().setHttpProxy("192.168.5.250", 3128);
		scraper.execute();
		
	}
}
