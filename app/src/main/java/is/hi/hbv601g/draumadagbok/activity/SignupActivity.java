package is.hi.hbv601g.draumadagbok.activity;

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
import android.widget.TextView;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.User;

public class SignupActivity extends AppCompatActivity {
    //SignupManager sm = new SignupManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button mSignupButton = (Button) findViewById(R.id.signup_button);

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: validate info from signup form
                //EditText texti = (EditText) findViewById(R.id.editText);
                EditText username = (EditText) findViewById(R.id.editText7);
                EditText pass = (EditText) findViewById(R.id.editText4);
                EditText passconfirm = (EditText) findViewById(R.id.editText5);
                TextView villa = (TextView) findViewById(R.id.textView4);

                if (pass != passconfirm) {
                    villa.setText("Villa: Lykilorð er ekki það sama í báðum reitum.");
                }
                else {
                    User user = new User();
                    user.setName(username.getText().toString());
                    user.setPassword(pass.getText().toString());
                    new InsertUserTask().execute(user);
                }
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
    private class InsertUserTask extends AsyncTask<User,Void,User> {
        @Override
        protected User doInBackground(User... params) {
            return sm.loginUser(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
            if (result.getId() == 0) { // ID er 0 ef user er ekki í gagnagrunninum
                TextView villa = (TextView) findViewById(R.id.textView2);
                villa.setText("Villa: Notandanafn eða lykilorð er rangt.");
            }
            else {
                //Saving basic user info
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                sharedPref.edit()
                        .putString("name", result.getName())
                        .putInt("id", result.getId())
                        .apply();

                Log.i("Result", "" + result.getId());

                //Start next activity
                Intent i = MainActivity.nameIntent(LoginActivity.this, result.getName());
                startActivity(i);
            }
        }
    }
}
