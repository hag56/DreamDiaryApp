package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.manager.LogDreamManager;
import is.hi.hbv601g.draumadagbok.model.User;

import java.util.Calendar;

import static is.hi.hbv601g.draumadagbok.manager.LogDreamManager.createDream;


public class LogDreamActivity extends AppCompatActivity {

    private static final String USER = "is.hi.hbv601g.draumadagbok.user";

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

                EditText titill = (EditText) findViewById(R.id.draumtexti);
                String title = titill.getText().toString();
                EditText texti = (EditText) findViewById(R.id.draumtitill);
                String innihald = texti.getText().toString();

                if (title.length() == 0)  {
                    Toast.makeText(getBaseContext(), R.string.tomur_titill, Toast.LENGTH_SHORT).show();
                }
                else if (innihald.length() == 0){
                    Toast.makeText(getBaseContext(), R.string.tomur_draumur, Toast.LENGTH_SHORT).show();
                }
                else {
                    Dream dream = new Dream();
                    dream.setName(title);
                    dream.setContent(innihald);
                    Calendar cal = Calendar.getInstance();
                    dream.setDate(cal.getTime());
                    dream.setUserId(mUser.getId());

                    new InsertDreamTask().execute(dream);
                }

            }
        });
    }
    //for data transfer into this activity from other activities
    public static Intent nameIntent(Context packageContext, Bundle bndle){
        Intent i = new Intent(packageContext, LogDreamActivity.class);
        i.putExtra(USER, bndle.getSerializable(USER));
        return i;
    }

    //for data transfer to server
    private class InsertDreamTask extends AsyncTask<Dream,Void,Dream> {
        @Override
        protected Dream doInBackground(Dream... params) {
            return createDream(params[0]);
        }

        @Override
        protected void onPostExecute(Dream result) {
            mUser.addDream(result);
            Bundle bndl = new Bundle();
            bndl.putSerializable(USER, mUser);

            Intent i = ShowDiaryActivity.nameIntent(LogDreamActivity.this, bndl);
            startActivity(i);

        }
    }
}
