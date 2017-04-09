package is.hi.hbv601g.draumadagbok.manager;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;

import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

import static is.hi.hbv601g.draumadagbok.manager.ConnectionManager.getUrlString;

/**
 * Created by Halli on 18.3.2017.
 */
//Manager for handling signup for the DreamDiary service
public class SignupManager {
    private static final String serverurl = "http://10.0.2.2:8080/mobsignup";

    public SignupManager(){

    }

    public static User createUser(User postdata){

        try {
            //prepare url
            String uri = Uri.parse(serverurl)
                    .buildUpon()
                    .appendQueryParameter("name", postdata.getName())
                    .appendQueryParameter("pass", postdata.getPassword())
                    .build().toString();

            //GET request
            String res = getUrlString(uri);

            //parse response
            JSONObject jsonob = new JSONObject(res);
            User user = new User(jsonob.getInt("id"),jsonob.getString("name"),"");
            JSONArray jsonA = jsonob.getJSONArray("dreams");
            for(int i = 0; i < jsonA.length(); i++){
                JSONObject jsDream = jsonA.getJSONObject(i);

                Dream dream = new Dream();
                dream.setName(jsDream.getString("name"));
                dream.setId(jsDream.getInt("id"));
                dream.setUserId(jsDream.getInt("userId"));

                JSONObject jsdate = jsDream.getJSONObject("date");
                int yr = jsdate.getInt("year");
                int day = jsdate.getInt("dayOfMonth");
                int mnth = jsdate.getInt("monthValue");
                Calendar cal = Calendar.getInstance();
                cal.set(yr,mnth,day);
                Date date = cal.getTime();
                dream.setDate(date);

                dream.setContent(jsDream.getString("content"));
                dream.setInterpretation(jsDream.getString("interpretation"));

                user.addDream(dream);
            }
            return user;

        }
        catch(Exception e){
            e.printStackTrace();

        }

        return postdata;
    }


}
