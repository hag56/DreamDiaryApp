package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import is.hi.hbv601g.draumadagbok.R;

public class ShowDreamActivity extends AppCompatActivity {
    //TODO: Connect to list of dreams
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dream);

        String mDreamTitle = getIntent().getStringExtra(DREAM_TITLE); //skiptum út fyrir Draum
        TextView title = (TextView) findViewById(R.id.d_title);
        title.setText(mDreamTitle);

        String mDreamContent = getIntent().getStringExtra(DREAM_CONTENT);
        TextView text = (TextView) findViewById(R.id.d_text);
        text.setText(mDreamContent);
    }

    private static final String DREAM_TITLE = "is.hi.hbv601g.draumadagbok.dreamtitle";
    private static final String DREAM_CONTENT = "is.hi.hbv601g.draumadagbok.dreamcontent";
    public static Intent nameIntent(Context packageContext, String title, String content){
        Intent i = new Intent(packageContext, ShowDreamActivity.class);
        i.putExtra(DREAM_TITLE, title);
        i.putExtra(DREAM_CONTENT, content);
        return i;
    }

}
