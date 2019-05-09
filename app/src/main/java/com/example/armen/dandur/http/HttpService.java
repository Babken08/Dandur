package com.example.armen.dandur.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class HttpService {
    public static byte[]doGet(String urll) throws IOException {

        URL url = new URL (urll);
        URLConnection urlConnection = url.openConnection();

        urlConnection.setRequestProperty("accesstoken", "3e265EER-wW85-8Po8-4w88-wWfhlOS61548");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] retSata = new byte[16384];

        while ((nRead = urlConnection.getInputStream().read(retSata, 0, retSata.length)) != -1) {
            buffer.write(retSata, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
}
