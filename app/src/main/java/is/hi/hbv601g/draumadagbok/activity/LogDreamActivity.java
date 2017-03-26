package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.User;

//TODO: Make  a manager who connects to server and send in dream
//TODO: Make arrangements for Guest dream
public class LogDreamActivity extends AppCompatActivity {
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_dream);

        Button mLogDreamButton = (Button)findViewById(R.id.submit_dream);
        mLogDreamButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                EditText titill = (EditText) findViewById(R.id.draumtitill);
                String title = titill.getText().toString();
                EditText texti = (EditText) findViewById(R.id.draumtexti);
                String innihald = texti.getText().toString();

/*                Intent i = ShowDreamActivity.DreamIntent(LogDreamActivity.this, title, innihald);
                startActivity(i);*/
            }
        });
    }
    //for data transfer
    public static Intent nameIntent(Context packageContext, Bundle bndle){
        Intent i = new Intent(packageContext, LogDreamActivity.class);
        i.putExtra(USER, bndle.getSerializable(USER));
        return i;
    }

    //TODO: Make an async class to send in the dream
}
