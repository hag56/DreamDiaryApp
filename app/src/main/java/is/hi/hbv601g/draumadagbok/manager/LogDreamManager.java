package is.hi.hbv601g.draumadagbok.manager;

import android.net.Uri;
import org.json.JSONObject;
import java.util.Calendar;

import is.hi.hbv601g.draumadagbok.model.Dream;
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
