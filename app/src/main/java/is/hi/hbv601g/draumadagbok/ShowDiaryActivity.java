package is.hi.hbv601g.draumadagbok;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);
    }

    private static final String USER_NAME = "is.hi.hbv601g.draumadagbok.uname";
    public static Intent nameIntent(Context packageContext, String name){
        Intent i = new Intent(packageContext, ShowDiaryActivity.class);
        i.putExtra(USER_NAME, name);
        return i;
    }
}
