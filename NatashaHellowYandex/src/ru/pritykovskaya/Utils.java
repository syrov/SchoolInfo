package ru.pritykovskaya;

import java.io.*;

/**
 * User: pritykovskaya
 * Date: 24.09.11
 */
public class Utils {
    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();

        byte[] b = new byte[1024];
        int readBytesCount = is.read(b);
        while (readBytesCount != -1) {
            String s = new String(b, 0, readBytesCount);
            sb = sb.append(s);
            readBytesCount = is.read(b);
        }
        is.close();

        return sb.toString();
    }

    public static InputStream readToMemory(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pipe(is, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public static void pipe(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int readBytesCount = is.read(buffer);
        while (readBytesCount != -1) {
            os.write(buffer, 0, readBytesCount);
            readBytesCount = is.read(buffer);
        }
        is.close();
        os.close();
    }
}
