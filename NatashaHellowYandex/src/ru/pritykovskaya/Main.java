package ru.pritykovskaya;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import static ru.pritykovskaya.Utils.inputStreamToString;
import static ru.pritykovskaya.Utils.readToMemory;

/**
 * User: pritykovskaya
 * Date: 25.09.11
 */
public class Main {
    public static void main(String[] args) {
        try {
            String url = "http://maps.yandex.ru/sprav/1038501709/";
            InputStream is = UrlUtils.getByUrl(url);

//            System.out.print(inputStreamToString(is));
            // we explicitly specify SAXParserFactory implementation as tagsoup
            System.setProperty("javax.xml.parsers.SAXParserFactory", "org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            MyHandler handler = new MyHandler();
            InputStream isInMemory = readToMemory(is);

            saxParser.parse(isInMemory, handler);

            System.out.println(handler.getCompanyName());

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

class MyHandler extends DefaultHandler {
    private static final String COMPANY_NAME_TAG_CLASS = "b-page-title__title";
    private static final String CLASS_ATTR = "class";
    private static final String H2_TAG = "h2";

    private boolean inCompanyNameTag = false;

    private StringBuilder companyName = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (H2_TAG.equals(qName) && COMPANY_NAME_TAG_CLASS.equals(attributes.getValue(CLASS_ATTR))) {
            inCompanyNameTag = true;
        }
    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
        if (inCompanyNameTag) {
            inCompanyNameTag = false;
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if (inCompanyNameTag) {
            companyName.append(chars, start, length);
        }
    }

    public String getCompanyName() {
        return companyName.toString();
    }
}
