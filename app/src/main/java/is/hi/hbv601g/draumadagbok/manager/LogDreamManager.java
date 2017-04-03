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
import java.util.Calendar;
import java.util.Date;

import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

import static android.content.ContentValues.TAG;
import static is.hi.hbv601g.draumadagbok.manager.ConnectionManager.getUrlString;

/**
 * Created by Halli on 18.3.2017.
 */

public class LogDreamManager {
    private static final String serverurl = "http://10.0.2.2:8080/mobdream";

    public LogDreamManager(){

    }


    public static Dream createDream(Dream postdata){

        Calendar cal = Calendar.getInstance();
        cal.setTime(postdata.getDate());

        try {
            String uri = Uri.parse(serverurl)
                    .buildUpon()
                    .appendQueryParameter("userId", String.valueOf(postdata.getUserId()))
                    .appendQueryParameter("title", postdata.getName())
                    .appendQueryParameter("content", postdata.getContent())
                    .appendQueryParameter("year", String.valueOf(cal.YEAR))
                    .appendQueryParameter("month", String.valueOf(cal.MONTH))
                    .appendQueryParameter("day", String.valueOf(cal.DAY_OF_MONTH))
                    .build().toString();
            String res = getUrlString(uri);


            JSONObject jsonob = new JSONObject(res);
            postdata.setInterpretation(jsonob.getString("interpretation"));

        }
        catch(Exception e){
            e.printStackTrace();

        }

        return postdata;
    }


}
