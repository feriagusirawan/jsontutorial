package com.luis.jsontutorial;

/**
 * Created by Luis on 12/5/2016.
 */

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;




public class HttpHandlerPost {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandlerPost() {
    }

    public String makePost(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);


            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("patient_name", "test7");
            jsonObject.accumulate("room_no", "test");
            jsonObject.accumulate("username", "x7"); //unique
            jsonObject.accumulate("password", "test");
            jsonObject.accumulate("doc_id", "583dcc7e5a034e0519757632");
            jsonObject.accumulate("doc_name", "Dr Luis");
            jsonObject.accumulate("doc_username", "luis");
            jsonObject.accumulate("department", "Neurology");
            jsonObject.accumulate("nurse_id", "583dccc05a034e0519757633");
            jsonObject.accumulate("nurse_name", "Nr Laura");
            jsonObject.accumulate("nurse_username", "eli");


            String mJsonString = jsonObject.toString();

            OutputStream os = conn.getOutputStream();
            os.write(mJsonString.getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}