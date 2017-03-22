package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import is.hi.hbv601g.draumadagbok.R;

public class MainActivity extends AppCompatActivity {
    //TODO: layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //insert username to UI
        final String mUserName = getIntent().getStringExtra(USER_NAME);
        TextView texti = (TextView) findViewById(R.id.textView);
        String welcomeUser = "Velkominn " + mUserName ;
        texti.setText(welcomeUser);

        Button mNewDreamButton = (Button) findViewById(R.id.dream_button);
        Button mOldDreamsButton = (Button) findViewById(R.id.old_button);

        mNewDreamButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = LogDreamActivity.nameIntent(MainActivity.this, mUserName);
                startActivity(i);
            }
        });


        mOldDreamsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = ShowDiaryActivity.nameIntent(MainActivity.this, mUserName);
                startActivity(i);
            }
        });
    }


    private static final String USER_NAME = "is.hi.hbv601g.draumadagbok.uname";
    public static Intent nameIntent(Context packageContext, String name){
        Intent i = new Intent(packageContext, MainActivity.class);
        i.putExtra(USER_NAME, name);
        return i;
    }



}
