package is.hi.hbv601g.draumadagbok.manager;

import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import is.hi.hbv601g.draumadagbok.model.User;

import static android.content.ContentValues.TAG;

/**
 * Created by Halli on 25.3.2017.
 */
//Specialized connection manager for localhost server
public class ConnectionManager {

    public static final String serverurl = "http://10.0.2.2:8080/mobuser";
    public ConnectionManager(){

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




    // OBSOLETE!! KEEP FOR EDUCATIONAL PURPOSE
    // Attempt at using POST instead of GET
    public static User POSTData(String usl, User param){
        Log.i(TAG, "Started");
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            /*HttpEntity<String> response = restTemplate.exchange(serverurl, HttpMethod.POST, new HttpEntity<User>(param), String.class);

            String resultString = response.getBody();
            HttpHeaders headers = response.getHeaders();
            Log.i(TAG, resultString);*/
            return restTemplate.postForObject(serverurl, param, User.class);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User();

    }
}
