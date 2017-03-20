package is.hi.hbv601g.draumadagbok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    LoginManager lm = new LoginManager();
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
                EditText pass = (EditText) findViewById(R.id.editText2);
                String word = pass.getText().toString();
                User user = new User();
                user.setName(nafn);
                user.setPassword(word);
                new FetchUserTask().execute(user);


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

    private class FetchUserTask extends AsyncTask<User,Void,User> {
        @Override
        protected User doInBackground(User... params) {
            return lm.loginUser(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
              //Log.i("TAG", );
            Context context = getApplicationContext();
            SharedPreferences sharedPref = context.getSharedPreferences("info", Context.MODE_PRIVATE);
            sharedPref.edit()
                    .putString("name", result.getName())
                    .putInt("id", result.getId())
                    .apply();
            Intent i = MainActivity.nameIntent(LoginActivity.this, result.getName());
            startActivity(i);
        }
    }
}
