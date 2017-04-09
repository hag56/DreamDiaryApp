package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.manager.ConnectionManager;
import is.hi.hbv601g.draumadagbok.model.User;
import is.hi.hbv601g.draumadagbok.manager.SignupManager;

//Activity for signing up for the DreamDiary service
public class SignupActivity extends AppCompatActivity {
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";

    SignupManager sm = new SignupManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button mSignupButton = (Button) findViewById(R.id.signup_button);

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText username = (EditText) findViewById(R.id.userName);
                EditText pass = (EditText) findViewById(R.id.password1);
                EditText passconfirm = (EditText) findViewById(R.id.password2);
                String p1 = pass.getText().toString();
                String p2 = passconfirm.getText().toString();


                if (username.length() < 4){
                    Toast.makeText(getBaseContext(), R.string.stutt_notandanafn, Toast.LENGTH_SHORT).show();
                }
                else if(pass.length() < 4 ){
                    Toast.makeText(getBaseContext(), R.string.stutt_lykilorð, Toast.LENGTH_SHORT).show();
                }
                else if (!p1.equals(p2)) {
                    Toast.makeText(getBaseContext(), R.string.ekki_sama_lykilorð, Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setName(username.getText().toString());
                    user.setPassword(ConnectionManager.Encrypt(pass.getText().toString()));
                    new InsertUserTask().execute(user);
                }
            }
        });
    }

    //For starting this activity
    public static Intent nameIntent(Context packageContext){
        Intent i = new Intent(packageContext, SignupActivity.class);
        return i;
    }

    //Async task for sending info to server
    private class InsertUserTask extends AsyncTask<User,Void,User> {
        @Override
        protected User doInBackground(User... params) {
            return sm.createUser(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
            if (result.getId() == 0) { // ID er 0 ef user er til
                Toast.makeText(getBaseContext(), R.string.notandi_til, Toast.LENGTH_SHORT).show();
            }
            else {
                Bundle bndl = new Bundle();
                bndl.putSerializable(USER, result);

                //Start next activity
                Intent i = MainActivity.nameIntent(SignupActivity.this, bndl);
                startActivity(i);
            }
        }
    }
}
