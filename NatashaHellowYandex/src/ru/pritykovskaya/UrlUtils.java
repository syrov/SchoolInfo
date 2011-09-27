package ru.pritykovskaya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: pritykovskaya
 * Date: 25.09.11
 */
public class UrlUtils {
    public static InputStream getByUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        return httpConnection.getInputStream();

    }
}
