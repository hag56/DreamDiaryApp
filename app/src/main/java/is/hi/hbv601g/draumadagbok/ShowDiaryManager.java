package is.hi.hbv601g.draumadagbok;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import is.hi.hbv601g.draumadagbok.Dream;
import is.hi.hbv601g.draumadagbok.User;
import is.hi.hbv601g.draumadagbok.dreamList;

import static android.content.ContentValues.TAG;
import static is.hi.hbv601g.draumadagbok.LoginManager.getUrlString;

/**
 * Created by Halli on 20.3.2017.
 */

public class ShowDiaryManager {
    private static final String serverurl = "http://10.0.2.2:8080/dreams";

    public ShowDiaryManager(){

    }
    public User findDreams(String name, int userId){

        try {

            String uri = Uri.parse(serverurl)
                    .buildUpon()
                    .appendQueryParameter("userId", String.valueOf(userId))
                    .build().toString();
            String res = getUrlString(uri);
            Log.i(TAG, "Received: " + res);
            User user = new User();
            user.setName(name);
            user.setId(userId);
            JSONArray jsonar = new JSONArray(res);
            for(int i = 0; i < jsonar.length(); i++){
                JSONObject js = jsonar.getJSONObject(i);
                Dream dr = new Dream();
                dr.setUserId(userId);
                dr.setId(js.getInt("id"));
                dr.setName(js.getString("name"));
                dr.setContent(js.getString("content"));
                dr.setInterpretation(js.getString("interpretation"));

                user.addDream(dr);
            }

        return user;
        }
        catch(Exception e){
            e.printStackTrace();

        }

        return new User(userId, name,"");
    }

}
