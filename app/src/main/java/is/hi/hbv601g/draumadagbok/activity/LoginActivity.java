package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import org.springframework.util.support.Base64;

import is.hi.hbv601g.draumadagbok.manager.LoginManager;
import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.User;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
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
                TextView villa = (TextView) findViewById(R.id.textView2);
                if (texti.length() == 0 || pass.length() == 0) {
                    villa.setText("Villa: Reitir þurfa að vera útfylltir.");
                }
                else {
                    User user = new User();
                    user.setName(nafn);
                    word = stringEncrypt(word);

                    user.setPassword(word);
                    new FetchUserTask().execute(user);
                }


            }
        });
        //event listeners
        mSkipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle b = new Bundle();
                b.putSerializable(USER, new User());
                Intent i = LogDreamActivity.nameIntent(LoginActivity.this, b);
                startActivity(i);
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: Make a signupActivity and signupManager
                Intent i = SignupActivity.nameIntent(LoginActivity.this, "guest");
                startActivity(i);
            }
        });
    }

    protected String stringEncrypt(String pass) {
        String bytesEncoded = Base64.encodeToString(pass.getBytes(), 0);
        String reverse = new StringBuilder(bytesEncoded).reverse().toString();
        reverse = reverse + "HenryErBestur";
        String bytesEncoded2 = Base64.encodeToString(reverse.getBytes(), 0);
        return bytesEncoded2;
    }

    protected String stringDecrypt(String secret) {
        byte[] valueDecoded= Base64.decode(secret.getBytes(), 0);
        String pass = new String(valueDecoded);
        pass = pass.substring(0,(pass.length() - 13));
        String reverse = new StringBuilder(pass).reverse().toString();
        byte[] valueDecoded2= Base64.decode(reverse.getBytes(), 0);
        return new String(valueDecoded2);
    }

    //Async class to get User from Server
    private class FetchUserTask extends AsyncTask<User,Void,User> {
        @Override
        protected User doInBackground(User... params) {
            return lm.loginUser(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
            if (result.getId() == 0) { // ID er 0 ef user er ekki í gagnagrunninum
                TextView villa = (TextView) findViewById(R.id.textView2);
                villa.setText("Villa: Notandanafn eða lykilorð er rangt.");
            }
            else {
                //Saving basic user info
/*                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                sharedPref.edit()
                        .putString("name", result.getName())
                        .putInt("id", result.getId())
                        .apply();
*/
                Log.i("Result", "" + result.toString());
                Bundle args = new Bundle();
                args.putSerializable(USER, result);
                //Start next activity
                Intent i = MainActivity.nameIntent(LoginActivity.this, args);
                startActivity(i);
            }
        }
    }
}
