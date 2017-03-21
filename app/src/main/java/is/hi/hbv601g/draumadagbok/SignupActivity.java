package is.hi.hbv601g.draumadagbok;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button mSignupButton = (Button) findViewById(R.id.signup_button);

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: validate info from signup form

            }
        });
    }
    private static final String USER_NAME = "is.hi.hbv601g.draumadagbok.uname";
    public static Intent nameIntent(Context packageContext, String name){
        Intent i = new Intent(packageContext, SignupActivity.class);
        i.putExtra(USER_NAME, name);
        return i;
    }

    //TODO: Make async class for network work
    //TODO: Create Manager class for network work
    //TODO: OnPostExecute
}
