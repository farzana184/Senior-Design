package myapp.com.f1;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Maintain user profile activity
 * @author Mohammed Al Aadhami
 * @version 1.0
 */

public class UserProfileEditActivity extends AppCompatActivity {

    DatabaseHelper userProfileDBObj;

    EditText nameET;
    String name1;

    EditText dateOfBirthET;
    String dateOfBirth;

    EditText genderET;
    String gender;

    EditText phoneNumET;
    String phoneNum;

    EditText emailAddressET;
    String emailAddress;

    EditText bloodPressureET;
    String bloodPressure;

    EditText bloodSugarET;
    String bloodSugar;

    EditText allergiesET;
    String allergies;

    EditText surgeriesET;
    String surgeries;

    EditText medicationsET;
    String medications;

    Button doneEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);
        userProfileDBObj = new DatabaseHelper(this);

        // 1
        nameET = (EditText) findViewById(R.id.nameEditText);
        name1 = (String)nameET.getText().toString();

        // 2
        dateOfBirthET = (EditText) findViewById(R.id.dateOfBirthEditText);
        dateOfBirth = (String)dateOfBirthET.getText().toString();

        // 3
        genderET = (EditText) findViewById(R.id.genderEditText);
        gender = (String)genderET.getText().toString();

        // 4
        phoneNumET = (EditText) findViewById(R.id.phoneNumEditText);
        phoneNum = (String)phoneNumET.getText().toString();

        // 5
        emailAddressET = (EditText) findViewById(R.id.emailAddressEditText);
        emailAddress = (String)emailAddressET.getText().toString();

        // 6
        bloodPressureET = (EditText) findViewById(R.id.bloodPressureEditText);
        bloodPressure = (String)bloodPressureET.getText().toString();

        // 7
        bloodSugarET = (EditText) findViewById(R.id.bloodSugarEditText);
        bloodSugar = (String)bloodSugarET.getText().toString();

        // 8
        allergiesET = (EditText) findViewById(R.id.allergiesEditText);
        allergies = (String)allergiesET.getText().toString();

        // 9
        surgeriesET = (EditText) findViewById(R.id.surgeriesEditText);
        surgeries = (String)surgeriesET.getText().toString();

        // 10
        medicationsET = (EditText) findViewById(R.id.medicationsEditText);
        medications = (String)medicationsET.getText().toString();

        doneEditButton = (Button) findViewById(R.id.doneEditButton);

        AddData();

    } // end of onCreate()

    /**
     * Adding and retreiving user profile data from the SQLite DB
     */
    public void AddData() {
        doneEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = userProfileDBObj.insertProfileData(nameET.getText().toString(),
                        dateOfBirthET.getText().toString(), genderET.getText().toString(),
                        phoneNumET.getText().toString(), emailAddressET.getText().toString(),
                        bloodPressureET.getText().toString(), bloodSugarET.getText().toString(),
                        allergiesET.getText().toString(), surgeriesET.getText().toString(),
                        medicationsET.getText().toString() );


                if (isInserted == true)
                    Toast.makeText(UserProfileEditActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UserProfileEditActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        Cursor cursor = userProfileDBObj.sr2();

        while (cursor.moveToNext()) {
            nameET.setText(cursor.getString(1));

            dateOfBirthET.setText(cursor.getString(2));

            genderET.setText(cursor.getString(3));

            phoneNumET.setText(cursor.getString(4));

            emailAddressET.setText(cursor.getString(5));

            bloodPressureET.setText(cursor.getString(6));

            bloodSugarET.setText(cursor.getString(7));

            allergiesET.setText(cursor.getString(8));

            surgeriesET.setText(cursor.getString(9));

            medicationsET.setText(cursor.getString(10));
        }


    } // end AddData()


}
