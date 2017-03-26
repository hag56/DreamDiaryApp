package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.User;

public class MainActivity extends AppCompatActivity {
    //TODO: layout
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
    User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get user from intent
        mUser = (User) getIntent().getSerializableExtra(USER);
        //display info on user
        TextView texti = (TextView) findViewById(R.id.textView);
        String welcomeUser = "Velkominn " + mUser.getName() ;
        texti.setText(welcomeUser);


        Button mNewDreamButton = (Button) findViewById(R.id.dream_button);
        Button mOldDreamsButton = (Button) findViewById(R.id.old_button);

        //event listeners
        mNewDreamButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle args = new Bundle();
                args.putSerializable(USER, mUser);
                Intent i = LogDreamActivity.nameIntent(MainActivity.this, args);
                startActivity(i);
            }
        });

        mOldDreamsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle args = new Bundle();
                args.putSerializable(USER, mUser);
                Intent i = ShowDiaryActivity.nameIntent(MainActivity.this, args);
                startActivity(i);
            }
        });
    }


    //for data transfer
    public static Intent nameIntent(Context packageContext, Bundle bndle){
        Intent i = new Intent(packageContext, MainActivity.class);
        i.putExtra(USER,bndle.getSerializable(USER));
        return i;
    }



}
