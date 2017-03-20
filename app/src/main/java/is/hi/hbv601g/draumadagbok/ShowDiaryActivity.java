package is.hi.hbv601g.draumadagbok;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class ShowDiaryActivity extends AppCompatActivity {
    TextView texti;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShowDiaryManager diaryManager = new ShowDiaryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary );
        new FetchDreamsTask().execute();
        texti = (TextView) findViewById(R.id.textView8);

    }

    private static final String USER_NAME = "is.hi.hbv601g.draumadagbok.uname";

    public static Intent nameIntent(Context packageContext, String name) {
        Intent i = new Intent(packageContext, ShowDiaryActivity.class);
        i.putExtra(USER_NAME, name);
        return i;
    }

    private class FetchDreamsTask extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... params) {
            Context context = getApplicationContext();
            SharedPreferences sharedPref = context.getSharedPreferences("info", Context.MODE_PRIVATE);
            String userName = sharedPref.getString("name", "Looser");
            int userId = sharedPref.getInt("id",-1);

            return diaryManager.findDreams(userName, userId);
        }

        @Override
        protected void onPostExecute(User result) {

            if(result.getId() != -1){

                texti.setText("Fann: " + result.getDreams().toString());//put data into view

            }

        }
    }
}

