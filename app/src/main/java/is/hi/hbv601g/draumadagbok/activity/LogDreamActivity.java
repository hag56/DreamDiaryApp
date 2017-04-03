package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.manager.LogDreamManager;
import is.hi.hbv601g.draumadagbok.model.User;

import java.util.Date;

//TODO: Make  a manager who connects to server and send in dream
//TODO: Make arrangements for Guest dream
public class LogDreamActivity extends AppCompatActivity {
    private static final String DREAM = "is.hi.hbv601g.draumadagbok.dream";
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";

    LogDreamManager ldm = new LogDreamManager();
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_dream);

        mUser = (User) getIntent().getSerializableExtra(USER);

        Button mLogDreamButton = (Button)findViewById(R.id.submit_dream);
        mLogDreamButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                EditText titill = (EditText) findViewById(R.id.draumtitill);
                String title = titill.getText().toString();
                EditText texti = (EditText) findViewById(R.id.draumtexti);
                String innihald = texti.getText().toString();

                if (title.length() == 0 || innihald.length() == 0) {
                    // Setja villu: "fylla verður í báða reiti"
                }
                else {
                    Dream dream = new Dream();
                    dream.setName(title);
                    dream.setContent(innihald);
                    dream.setDate(new Date());
                    // meira?
                    new InsertDreamTask().execute(dream);
                }

                /*
                Intent i = ShowDreamActivity.DreamIntent(LogDreamActivity.this, title, innihald);
                startActivity(i);
                */
            }
        });
    }
    //for data transfer
    public static Intent nameIntent(Context packageContext, Bundle bndle){
        Intent i = new Intent(packageContext, LogDreamActivity.class);
        i.putExtra(DREAM, bndle.getSerializable(DREAM));
        return i;
    }

    //TODO: Make an async class to send in the dream
    private class InsertDreamTask extends AsyncTask<Dream,Void,Dream> {
        @Override
        protected User doInBackground(Dream... params) {
            return ldm.createDream(params[0]);
        }

        @Override
        protected void onPostExecute(Dream result) {
            if (/* villa */ false) {
                // villa
            }
            else {
                Bundle bndl = new Bundle();
                bndl.putSerializable(DREAM, result);

                Intent i = ShowDreamActivity.nameIntent(LogDreamActivity.this, bndl);
                startActivity(i);
            }
        }
    }
}
