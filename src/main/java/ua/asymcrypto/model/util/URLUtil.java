package ua.asymcrypto.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class URLUtil {
    public static String readJSONFromURL(String address) {
        //System.out.println(address);
        StringBuilder sb = new StringBuilder();

        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//
//        HttpURLConnection connection = null;
//        try {
//            if (url != null) {
//                connection = (HttpURLConnection) url.openConnection();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int code;
//
//        try {
//            connection.setRequestMethod("GET");
//            connection.setDoOutput(true);
//            code = connection.getResponseCode();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(url).openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }
}
