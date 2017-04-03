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

        Dream dream = new Dream();
        try {
            String uri = Uri.parse(serverurl)
                    .buildUpon()
                    .appendQueryParameter("userId", String.valueOf(postdata.getUserId()))
                    .appendQueryParameter("title", postdata.getName())
                    .appendQueryParameter("content", postdata.getContent())
                    .appendQueryParameter("year", String.valueOf(postdata.getDate().getYear()))
                    .appendQueryParameter("month", String.valueOf(postdata.getDate().getMonth()))
                    .appendQueryParameter("day", String.valueOf(postdata.getDate().getDay()))
                    .build().toString();
            String res = getUrlString(uri);
            Log.i(TAG, "Received: " + res);

            JSONObject jsonob = new JSONObject(res);
            dream.setName(jsonob.getString("title"));
            dream.setUserId(jsonob.getInt("userId"));
            dream.setContent(jsonob.getString("content"));
            dream.setId(jsonob.getInt("id"));
            dream.setInterpretation(jsonob.getString("interpretation"));

            JSONObject jsdate = jsonob.getJSONObject("date");
            int yr = jsdate.getInt("year");
            int day = jsdate.getInt("dayOfMonth");
            int mnth = jsdate.getInt("monthValue");
            String sd = String.valueOf(yr) + "-"  + String.valueOf(mnth)+ "-" + String.valueOf(day);
            Calendar cal = Calendar.getInstance();
            cal.set(yr,mnth,day);
            Date date = cal.getTime();

            dream.setDate(date);

            return dream;

           //User res = POSTData(serverurl, postdata);
            //return res;
        }
        catch(Exception e){
            e.printStackTrace();

        }

        return postdata;
    }


}
