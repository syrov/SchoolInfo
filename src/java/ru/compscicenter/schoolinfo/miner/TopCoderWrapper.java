
import java.net.URL;
import java.io.*;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;


public class TopCoderWrapper {
    private final static String url = "http://community.topcoder.com/stat?c=school_avg_rating";
    private final static String tempPath = "src/";


    public static void downloadOneLink(String urlStr, String filePath) throws Exception {
        System.out.println("Downloading " + urlStr + " to " + filePath);
        URL url = new URL(urlStr);
        InputStream response = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        FileWriter fw = new FileWriter(new File(filePath + "\\TopCoderSchool.html"));
        String line = reader.readLine();
        while (line != null) {
            fw.write(line);
            line = reader.readLine();
        }
        reader.close();
        fw.close();
    }


    public void wrap() {
        try {
            downloadOneLink(url, tempPath);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        TopCoderWrapper wrapper = new TopCoderWrapper();
        wrapper.wrap();
    }


}
