package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import is.hi.hbv601g.draumadagbok.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button mSignupButton = (Button) findViewById(R.id.signup_button);

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String usrName   = ((EditText) findViewById(R.id.userName)).getText().toString();

                boolean nameTest = false;
                if(usrName.matches("^([a-zA-Z0-9_\\-])+$"))
                    nameTest = true;

                String password1 = ((EditText) findViewById(R.id.password1)).getText().toString();
                String password2 = ((EditText) findViewById(R.id.password2)).getText().toString();
                String email     = ((EditText) findViewById(R.id.email)).getText().toString();

                boolean emailTest = false;
                if(email.matches("^([a-zA-Z0-9_\\-])+\\@([a-zA-Z0-9_\\-])+\\.+([a-z])+$"))
                    emailTest = true;


                TextView villa = (TextView) findViewById(R.id.errorGluggi);
                if(usrName.length() == 0 || email.length() == 0 || password1.length() == 0) {
                    villa.setText("Villa: Allir reitir þurfa að vera útfylltir.");
                } else if(!nameTest){
                    villa.setText("Villa: notenda-nafn ekki á réttu formi");
                /*
                }else if(TODO: test hvort að notandanafn og/eða email sé frátekið){
                    villa.setText("Villa: frátekið stuff");
                */
                }else if(!password1.equals(password2)){
                    villa.setText("Villa: Lykilorðin stemma ekki ");
                } else if(password1.length() < 4){
                    villa.setText("Villa: Lykilorð ekki nógu langt");
                } else if(!emailTest){
                    villa.setText("Villa: email ekki á réttu formi");
                } else {
                    villa.setText("stuff works!");
                    //TODO: registera user hér
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
}
