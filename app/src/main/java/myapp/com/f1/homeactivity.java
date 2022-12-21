package myapp.com.f1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Creates the homepage of the application
 * @author Farzana Rahman
 * @version 1.0
 */

public class homeactivity extends AppCompatActivity {
    Button medication;
    Button reminder;
    Button history;
    Button notes;
    Button med_list;
    Button manual;
    SharedPreferences sp;
ImageButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_homeactivity);
        medication = findViewById (R.id.button_med);
        reminder= findViewById (R.id.button_reminder);
        history= findViewById (R.id.button_history);
        notes= findViewById (R.id.button_notes);
        med_list= findViewById (R.id.list);
        manual = findViewById (R.id.button_help);

        sp= getSharedPreferences ("login",MODE_PRIVATE);


        med_list.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (homeactivity.this,second.class);
                startActivity (intent);
            }
        });

        medication.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (homeactivity.this,medicationactivity.class);
                startActivity (intent);
            }
        });
        reminder.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (homeactivity.this,All_alarm.class);
                startActivity (intent);
            }
        });
        notes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (homeactivity.this,notesactivity.class);
                startActivity (intent);
            }
        });
        manual.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (homeactivity.this,UserManual.class);
                startActivity (intent);
            }
        });


        history.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (homeactivity.this,UserProfileEditActivity.class);
                startActivity (intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_home,menu);
        return true;
    }
    /**
     * Refers to action bar icon
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if(id==R.id.log_out)
        {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(homeactivity.this, MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected (item);
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

//Source: https://www.youtube.com/watch?v=5M4n3CVfrY0