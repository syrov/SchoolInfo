package ru.pritykovskaya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: pritykovskaya
 * Date: 24.09.11
 */
public class Downloader {
    public static void main(String[] args) {
        try {
            String urlStr = "http://ya.ru";
            URL url = new URL(urlStr);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpConnection.getInputStream();
            String s = Utils.inputStreamToString(is);
            System.out.println(s);

            File file = new File("out.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

