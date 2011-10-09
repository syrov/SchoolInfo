package java.ru.compscicenter.schoolinfo.miner;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

//This class cleans mined data
public class DataCleaner extends DefaultHandler {

	private ArrayList<String> goodTags;
	private StringBuffer result;
	
	public DataCleaner(){
		super();
		result = new StringBuffer();
		goodTags = new ArrayList<String>();
		goodTags.add("i");
		goodTags.add("table");
		goodTags.add("tbody");
		goodTags.add("tr");
		goodTags.add("td");
		goodTags.add("div");
		goodTags.add("span");
		goodTags.add("b");
		goodTags.add("p");
		goodTags.add("u");
		goodTags.add("ul");
		goodTags.add("ol");
		goodTags.add("li");
		goodTags.add("br");
		goodTags.add("strong");
		
	}
	
    public void startElement(String uri, String localName, String qName,  Attributes attributes){
    		
    		if(goodTags.contains(qName.toLowerCase())){
    			if(!qName.toLowerCase().equals("br"))
    				result.append("<" + qName + " class='my" + qName + "'> ");
    			else
    				result.append("<" + qName + "/> ");
    		}
    }
    
    public void endElement (String uri, String name, String qName){
    	
    	if(goodTags.contains(qName.toLowerCase()) && !qName.toLowerCase().equals("br"))
			result.append("</" + qName + "> ");
    	
    }
    
    public void characters (char ch[], int start, int length) {
    		result.append(ch, start, length);
    }
	
    public String getData(){ return result.toString(); }
    public void clear(){ result.setLength(0); }
    
    
}
