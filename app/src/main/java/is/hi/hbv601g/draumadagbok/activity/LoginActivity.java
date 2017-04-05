package is.hi.hbv601g.draumadagbok.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import org.springframework.util.support.Base64;

import is.hi.hbv601g.draumadagbok.manager.LoginManager;
import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.User;


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
                if (texti.length() == 0 || pass.length() == 0) {
                    Toast.makeText(getBaseContext(), R.string.fylla_reiti, Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setName(nafn);
                    //word = stringEncrypt(word);

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
                Intent i = SignupActivity.nameIntent(LoginActivity.this);
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
                Toast.makeText(getBaseContext(), R.string.rangt_inn, Toast.LENGTH_SHORT).show();
            }
            else {
                Bundle args = new Bundle();
                args.putSerializable(USER, result);
                //Start next activity
                Intent i = MainActivity.nameIntent(LoginActivity.this, args);
                startActivity(i);
            }
        }
    }
}
