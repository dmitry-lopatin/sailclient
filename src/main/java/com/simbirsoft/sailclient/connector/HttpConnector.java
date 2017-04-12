package com.simbirsoft.sailclient.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector {

    private HttpConnector() {

    }

    public static HttpConnector createInstance() {
        return new HttpConnector();
    }

    public String executeGet(String targetUrl) throws Exception{
        HttpURLConnection connection;
        StringBuilder result = new StringBuilder();

        URL url = new URL(targetUrl);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();

        return result.toString();
    }
}
