package is.hi.hbv601g.draumadagbok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mLoginButton = (Button) findViewById(R.id.login_button);
        Button mSkipButton = (Button) findViewById(R.id.skip_button);
        Button mSignupButton = (Button) findViewById(R.id.signup_button);


        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText texti = (EditText) findViewById(R.id.editText);
                String nafn = texti.getText().toString();
                Intent i = MainActivity.nameIntent(LoginActivity.this, nafn);
                startActivity(i);

            }
        });

        mSkipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = LogDreamActivity.nameIntent(LoginActivity.this, "guest");
                startActivity(i);
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = SignupActivity.nameIntent(LoginActivity.this, "guest");
                startActivity(i);
            }
        });
    }
}
