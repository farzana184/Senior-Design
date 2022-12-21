package myapp.com.f1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
/**
 * Represents the login screen and validates user
 * @author Farzana Rahman
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button register;
    DatabaseHelper mydb;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        setContentView (R.layout.activity_main);
        mydb= new DatabaseHelper (this);
        username= findViewById (R.id.edittext_username);
        password= findViewById (R.id.edittext_password);
        login= findViewById (R.id.button_login);

        register= findViewById (R.id.button_register);
        sp= getSharedPreferences ("login",MODE_PRIVATE);
        if(sp.getBoolean ("logged",false))

        {
            Intent intent = new Intent (MainActivity.this,homeactivity.class);
            startActivity (intent);

        }

        register.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,RegisterActivity.class);
                startActivity (intent);
            }
        });


        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String user= username.getText ().toString ().trim ();
                String pwd = password.getText ().toString ().trim ();
                Boolean res = mydb.check (user,pwd);
                if(res==true){
                    Toast.makeText (MainActivity.this,"login successful",Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (MainActivity.this,homeactivity.class);
                    startActivity (intent);
                    sp.edit ().putBoolean ("logged",true).apply ();

                }
                else {
                    Toast.makeText (MainActivity.this,"login error",Toast.LENGTH_SHORT).show ();

                }

            }
        });

    }

    /**
     * Maintains the activity stack
     */
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
//Source: https://www.youtube.com/watch?v=mPhqDzO7PUU