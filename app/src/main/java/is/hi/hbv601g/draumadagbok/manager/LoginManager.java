package is.hi.hbv601g.draumadagbok.manager;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

import static android.content.ContentValues.TAG;
import static is.hi.hbv601g.draumadagbok.manager.ConnectionManager.getUrlString;
import static is.hi.hbv601g.draumadagbok.manager.ConnectionManager.serverurl;

/**
 * Created by Halli on 18.3.2017.
 */

public class LoginManager {


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


}
