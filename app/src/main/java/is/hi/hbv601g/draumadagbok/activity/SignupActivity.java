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
import is.hi.hbv601g.draumadagbok.manager.SignupManager;

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
                //TODO: validate info from signup form
                EditText username = (EditText) findViewById(R.id.userName);
                EditText pass = (EditText) findViewById(R.id.password1);
                EditText passconfirm = (EditText) findViewById(R.id.password2);
                String p1 = pass.getText().toString();
                String p2 = passconfirm.getText().toString();
                TextView villa = (TextView) findViewById(R.id.errorGluggi);

                Log.i("Bleh", p1);
                Log.i("Bleh", p2);

                if (!p1.equals(p2)) {
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
            return sm.createUser(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
            if (result.getId() == 0) { // ID er 0 ef user er til
                TextView villa = (TextView) findViewById(R.id.errorGluggi);
                villa.setText("Villa: Notandi er þegar til með þessu notendanafni.");
            }
            else {

                /*
                //Saving basic user info
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                sharedPref.edit()
                        .putString("name", result.getName())
                        .putInt("id", result.getId())
                        .apply();

                Log.i("Result", "" + result.getId());
                */

                Bundle bndl = new Bundle();
                bndl.putSerializable(USER, result);

                //Start next activity
                Intent i = MainActivity.nameIntent(SignupActivity.this, bndl);
                startActivity(i);
            }
        }
    }
}
