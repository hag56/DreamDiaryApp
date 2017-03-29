package is.hi.hbv601g.draumadagbok.manager;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

import static android.content.ContentValues.TAG;

/**
 * Created by Halli on 18.3.2017.
 */

public class SignupManager {
    private static final String serverurl = "http://10.0.2.2:8080/mobsignup";

    public SignupManager(){

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
            User user = new User(jsonob.getInt("id"),jsonob.getString("name"),"");
            JSONArray jsonA = jsonob.getJSONArray("dreams");
            for(int i = 0; i < jsonA.length(); i++){
                JSONObject jsDream = jsonA.getJSONObject(i);

                Dream dream = new Dream();
                dream.setName(jsDream.getString("name"));
                dream.setId(jsDream.getInt("id"));
                dream.setUserId(jsDream.getInt("userId"));
                //dream.setDate((Date)jsDream.getJSONObject("date")); VESEN!!!!!
                dream.setContent(jsDream.getString("content"));
                dream.setInterpretation(jsDream.getString("interpretation"));

                user.addDream(dream);
            }
            return user;


            //User res = POSTData(serverurl, postdata);
            //return res;
        }
        catch(Exception e){
            e.printStackTrace();

        }

        return postdata;
    }

    //TODO: Make specific ConnectionManager
    // Attempt at using POST instead of GET
    public static User POSTData(String usl, User param){
        Log.i(TAG, "Started");
        try {
            Log.i(TAG, "trying");
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
