package is.hi.hbv601g.draumadagbok;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import static android.content.ContentValues.TAG;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by Halli on 18.3.2017.
 */

public class LoginManager {
    private static final String serverurl = "http://10.0.2.2:8080/mobuser";

    public LoginManager(){

    }


    public static User loginUser(User postdata){
        try {

            String uri = Uri.parse(serverurl)
                    .buildUpon()
                    .appendQueryParameter("name", postdata.getName())
                    .appendQueryParameter("pass", postdata.getPassword())
                    .build().toString();
            String res = getUrlString(uri);
            Log.i(TAG, "Received: " + res);

            JSONObject jsonob = new JSONObject(res);
            return new User(jsonob.getInt("id"),jsonob.getString("name"),"");

            //User res = POSTData(serverurl, postData);
            //return res;
        }
        catch(Exception e){
            e.printStackTrace();

        }

        return postdata;
    }

    // Attempt at using POST instead of GET
    public static User POSTData(String usl, User param){
        Log.i(TAG, "Started");
        try {
            Log.i(TAG, "trying");
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpEntity<String> response = restTemplate.exchange(serverurl, HttpMethod.POST, new HttpEntity<User>(param), String.class);

            String resultString = response.getBody();
            HttpHeaders headers = response.getHeaders();
            Log.i(TAG, resultString);
            User user = restTemplate.postForObject(serverurl, param, User.class);
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User();

    }


    public static byte[] getUrlBytes(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(conn.getResponseMessage() + ": with " + uri);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally { conn.disconnect(); }
    }
    public static String getUrlString(String uri) throws IOException {
        return new String(getUrlBytes(uri));
    }
}
