package myapp.com.f1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

/**
 * Intents camera and gallery, converts texts in an image into editable texts , adds medication information into database and checks for drug interaction
 * @author Farzana Rahman
 * @version 1.0
 */

public class medicationactivity extends AppCompatActivity {

    EditText mResultEt;
    ImageView mPreviewIv;
    Button add;
    Button view;
    DatabaseHelper db;


    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;


    String cameraPermission[];
    String storagePermission[];

    Uri image_uri;

    /**
     * Maintains activity stack
     */

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent (medicationactivity.this,homeactivity.class);
        startActivity (intent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_medicationactivity);

        db= new DatabaseHelper (this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar ();
        actionBar.setSubtitle ("Click on the icon to insert image");



        mResultEt = findViewById (R.id.resultEt);
        mPreviewIv= findViewById (R.id.imageIv);
        add= findViewById (R.id.add);
        view= findViewById (R.id.View);

        cameraPermission = new String[] {Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        view.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (medicationactivity.this,second.class);
                startActivity (intent);
            }
        });

        add.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String name = mResultEt.getText ().toString ();
name= name.toLowerCase ();
                //////messed up here
                if(!name.equals ("") && db.insertData (name,"0","0") ) {


                    if (name.contains ("clonidine"))


                    {
                        Cursor cursor = db.sr ("propranolol");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Clonidine has interaction with Propranolol. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }







                  else if (name.contains ("propranolol"))


                    {
                        Cursor cursor = db.sr ("clonidine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Propranolol has interaction with Clonidine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }






                    else if (name.contains ("fluoxetine"))


                    {
                        Cursor cursor = db.sr ("phenelzine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Fluoxetine has interaction with Phenelzine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("phenelzine"))


                    {
                        Cursor cursor = db.sr ("fluoxetine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Phenelzine has interaction with Fluoxetine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("digoxin"))


                    {
                        Cursor cursor = db.sr ("quinidine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Digoxin has interaction with Quinidine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("quinidine"))


                    {
                        Cursor cursor = db.sr ("digoxin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Quinidine has interaction with Digoxin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("sildenafil"))


                    {
                        Cursor cursor = db.sr ("isosorbide mononitrate");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Sildenafil has interaction with Isosorbide Mononitrate. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("isosorbide mononitrate"))


                    {
                        Cursor cursor = db.sr ("sildenafil");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Isosorbide Mononitrate has interaction with Sildenafil. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("spironolactone"))


                    {
                        Cursor cursor = db.sr ("potassium chloride");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Spironolactone has interaction with Potassium Chloride. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }





                    else if (name.contains ("potassium chloride"))


                    {
                        Cursor cursor = db.sr ("spironolactone");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Potassium Chloride has interaction with Spironolactone. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("warfarin"))


                    {
                        Cursor cursor = db.sr ("diflunisal");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Warfarin has interaction with Diflunisal. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("diflunisal"))


                    {
                        Cursor cursor = db.sr ("warfarin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Diflunisal has interaction with Warfarin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }







                    else if (name.contains ("theophylline"))


                    {
                        Cursor cursor = db.sr ("ciprofloxacin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Theophylline has interaction with Ciprofloxacin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("ciprofloxacin"))


                    {
                        Cursor cursor = db.sr ("theophylline");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Ciprofloxacin has interaction with Theophylline. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }







                    else if (name.contains ("pimozide"))


                    {
                        Cursor cursor = db.sr ("ketoconazole");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Pimozide has interaction with Ketoconazole. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }












                    else if (name.contains ("ketoconazole"))


                    {
                        Cursor cursor = db.sr ("pimozide");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Ketoconazole has interaction with Pimozide. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("methotrexate"))


                    {
                        Cursor cursor = db.sr ("probenecid");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Methotrexate has interaction with Probenecid. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("probenecid"))


                    {
                        Cursor cursor = db.sr ("methotrexate");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Probenecid has interaction with Methotrexate. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }






                    else if (name.contains ("pseudoephedrine"))


                    {
                        Cursor cursor = db.sr ("bromocriptine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Pseudoephedrine has interaction with Bromocriptine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("bromocriptine"))


                    {
                        Cursor cursor = db.sr ("pseudoephedrine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Bromocriptine has interaction with Pseudoephedrine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("lisinopril"))


                    {
                        Cursor cursor = db.sr ("valsartan");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Lisinopril has interaction with Valsartan. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("valsartan"))


                    {
                        Cursor cursor = db.sr ("lisinopril");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Valsartan has interaction with Lisinopril. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("losartan"))


                    {
                        Cursor cursor = db.sr ("naproxen");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Losartan has interaction with Naproxen. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("naproxen"))


                    {
                        Cursor cursor = db.sr ("losartan");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Naproxen has interaction with Losartan. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("amlodipine"))


                    {
                        Cursor cursor = db.sr ("diltiazem");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Amlodipine has interaction with Diltiazem. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("diltiazem"))


                    {
                        Cursor cursor = db.sr ("amlodipine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Diltiazem has interaction with Amlodipine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }






                    else if (name.contains ("nadolol"))


                    {
                        Cursor cursor = db.sr ("atenolol");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Nadolol has interaction with Atenolol. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("atenolol"))


                    {
                        Cursor cursor = db.sr ("nadolol");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Atenolol has interaction with Nadolol. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("rosiglitazone"))


                    {
                        Cursor cursor = db.sr ("liraglutide subcutaneous");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Rosiglitazone has interaction with Liraglutide Subcutaneous. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }

                    else if (name.contains ("liraglutide subcutaneous"))


                    {
                        Cursor cursor = db.sr ("rosiglitazone");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Liraglutide Subcutaneous has interaction with Rosiglitazone. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("saxagliptin"))


                    {
                        Cursor cursor = db.sr ("symbicort");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Saxagliptin has interaction with Symbicort. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("symbicort"))


                    {
                        Cursor cursor = db.sr ("saxagliptin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Symbicort has interaction with Saxagliptin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("levomilnacipran"))


                    {
                        Cursor cursor = db.sr ("celexa");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Levomilnacipran has interaction with Celexa. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }





                    else if (name.contains ("celexa"))


                    {
                        Cursor cursor = db.sr ("levomilnacipran");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Celexa has interaction with Levomilnacipran. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("metronidazole"))


                    {
                        Cursor cursor = db.sr ("antabuse");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Metronidazole has interaction with Antabuse. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }





                    else if (name.contains ("antabuse"))


                    {
                        Cursor cursor = db.sr ("metronidazole");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Antabuse has interaction with Metronidazole. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("metformin"))


                    {
                        Cursor cursor = db.sr ("synthroid");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Metformin has interaction with Synthroid. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("synthroid"))


                    {
                        Cursor cursor = db.sr ("metformin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Synthroid has interaction with Metformin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("pioglitazone"))


                    {
                        Cursor cursor = db.sr ("gemfibrozil");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Pioglitazone has interaction with Gemfibrozil. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }





                    else if (name.contains ("gemfibrozil"))


                    {
                        Cursor cursor = db.sr ("pioglitazone");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Gemfibrozil has interaction with Pioglitazone. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("glyburide"))


                    {
                        Cursor cursor = db.sr ("levofloxacin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Glyburide has interaction with Levofloxacin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("levofloxacin"))


                    {
                        Cursor cursor = db.sr ("glyburide");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Levofloxacin has interaction with Glyburide. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }












                    else if (name.contains ("aripiprazole"))


                    {
                        Cursor cursor = db.sr ("desvenlafaxine");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Aripiprazole has interaction with Desvenlafaxine. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("desvenlafaxine"))


                    {
                        Cursor cursor = db.sr ("aripiprazole");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Desvenlafaxine has interaction with Aripiprazole. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }






                    else if (name.contains ("moexipril"))


                    {
                        Cursor cursor = db.sr ("amiloride");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Moexipril has interaction with Amiloride. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("amiloride"))


                    {
                        Cursor cursor = db.sr ("moexipril");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Amiloride has interaction with Moexipril. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }



                    else if (name.contains ("apixaban"))


                    {
                        Cursor cursor = db.sr ("diclofenac");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Apixaban has interaction with Diclofenac. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }





                    else if (name.contains ("diclofenac"))


                    {
                        Cursor cursor = db.sr ("apixaban");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Diclofenac has interaction with Apixaban. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }


                    else if (name.contains ("rifampin"))


                    {
                        Cursor cursor = db.sr ("repaglinide");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Rifampin has interaction with Repaglinide. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }




                    else if (name.contains ("repaglinide"))


                    {
                        Cursor cursor = db.sr ("rifampin");
                        if (cursor.getCount () > 0) {


                            AlertDialog.Builder bld = new AlertDialog.Builder (medicationactivity.this);
                            //   bld.setMessage ("Clonidine has inteartion with Propranolol. Consult a doctor.").setNegativeButton ("Continue",null);
                            bld.setMessage ("Repaglinide has interaction with Rifampin. Consult a doctor.")
                                    .setPositiveButton ("Continue", new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                                            add.setText ("");
                                        }
                                    });


                            AlertDialog alt = bld.create ();
                            alt.show ();


                        }
                    }

















                    else {
                        Toast.makeText (medicationactivity.this, "ADDED", Toast.LENGTH_SHORT).show ();
                        add.setText ("");
                        //listItem.clear ();
                        //  viewData ();
                    }
                }
                else
                    Toast.makeText (medicationactivity.this,"not added",Toast.LENGTH_SHORT).show ();
            }
        });






    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }



    /**
     * Refers to action bar icons
     * @param item
     * @return boolean
     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if(id== R.id.addImage)
        {
            showImageImportDialog();

        }
        if(id== R.id.settings)
        {
            Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show ();

        }

        return super.onOptionsItemSelected (item);
    }


    /**
     * imports the dialog with options camera and gallery
     */


    private void showImageImportDialog() {
        String[] items= {"Camera","Gallery"};
        //options for picture
        AlertDialog.Builder dialog = new AlertDialog.Builder (this);

        dialog.setTitle ("select Image");
        dialog.setItems (items, new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    //if takes the first one need to check for camera permission if not granted yet
                    // if already granted pick camera
                    if(!checkCameraPermission())
                    {
                        requestCameraPermission();
                    }
                    else {
                        pickCamera();
                    }

                }
                if(which==1)
                {
                    //if takes the second one need to check for storage permission if not granted yet
                    //if already granted pick storage

                    if(!checkStoragePermission())
                    {
                        requestStoragePermission();
                    }
                    else {
                        pickGallery();
                    }

                }

            }
        });
        dialog.create ().show ();
    }

    /**
     * Intents gallery to select picture
     */

    private void pickGallery() {
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType ("image/*");
        startActivityForResult (intent, IMAGE_PICK_GALLERY_CODE);

    }
    /**
     * Intents camera to take picture
     */
    private void pickCamera() {
        ContentValues values = new ContentValues ();
        values.put (MediaStore.Images.Media.TITLE, "NewPic");
        values.put (MediaStore.Images.Media.DESCRIPTION, "Image to Text");
        image_uri = getContentResolver ().insert (MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        cameraIntent.putExtra (MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult (cameraIntent, IMAGE_PICK_CAMERA_CODE);

    }

    /**
     * Request for storage permission
     */

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions (this,storagePermission, STORAGE_REQUEST_CODE);

    }
// get the storage request permission
    //using package manager

    /**
     * Checks for storage permission
     * @return boolean
     */
    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result;
    }
//get the camera request permission

    /**
     * Request for camera permission
     */
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions (this,cameraPermission, CAMERA_REQUEST_CODE);
    }

    // checking the returning result
    /**
     * Checks for camera permission
     * @return boolean
     */
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission (this, Manifest.permission.CAMERA)==
                (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                (PackageManager.PERMISSION_GRANTED);
        return result && result1;

    }

    /**
     * Callback for the result from requesting permissions
     * @param requestCode the request code
     * @param permissions the requested permission
     * @param grantResults the result of corresponding permission
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0)
                {
                    //using package manager
                    //if accepted
                    //calling the pickCamera function if permission granted
                    boolean cameraAccepted = grantResults[0]==
                            PackageManager.PERMISSION_GRANTED;

                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted)
                    {
                        pickCamera ();
                    }
                    else {
                        Toast.makeText (this,"permission denied", Toast.LENGTH_SHORT).show ();

                    }

                }
                break;


            case STORAGE_REQUEST_CODE:



                if(grantResults.length>0)
                {
                    //using packagr manager
                    //getting storage permission
                    //if true will call pickGallary function
                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;


                    if( writeStorageAccepted)
                    {
                        pickGallery ();
                    }
                    else {
                        Toast.makeText (this,"permission denied", Toast.LENGTH_SHORT).show ();

                    }

                }

                break;



        }
    }

    /**
     * converts texts in an image to editable texts
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK)
        {
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity (data.getData ()).setGuidelines (CropImageView.Guidelines.ON).start (this);

            }
            if(requestCode == IMAGE_PICK_CAMERA_CODE){


                CropImage.activity (image_uri).setGuidelines (CropImageView.Guidelines.ON).start (this);


            }
        }
// need to get image uri
        // need to edit

        // using bitmap and bitmap drawable
        //need to create a frame
        //textrecognizer , sparse array and string builder required


        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

            CropImage.ActivityResult result = CropImage.getActivityResult (data);
            if(resultCode== RESULT_OK){

                Uri resultUri = result.getUri ();
                mPreviewIv.setImageURI (resultUri);
                // get the image uri
                //get the bitmap of the image

                BitmapDrawable bitmapDrawable = (BitmapDrawable)mPreviewIv.getDrawable ();

                Bitmap bitmap = bitmapDrawable.getBitmap ();
                //recognizing as block of text
                //will work when recognizer is operational

                TextRecognizer recognizer = new TextRecognizer.Builder (getApplicationContext ()).build ();
                if(!recognizer.isOperational ()){
                    Toast.makeText (this, "Error" , Toast.LENGTH_SHORT).show ();


                }

                else {
                    //bind the bitmap
                    //create frame object
                    //get the item at position
                    Frame frame = new Frame.Builder ().setBitmap (bitmap).build ();
                    SparseArray<TextBlock> items = recognizer.detect (frame);
                    StringBuilder sb = new StringBuilder ();
                    for(int i =0;i<items.size ();i++){
                        TextBlock myItem = items.valueAt (i);
                        sb.append (myItem.getValue ());
                        sb.append ("\n");

                    }

                    mResultEt.setText (sb.toString ());





                }


            }
            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError ();
                Toast.makeText (this,""+error, Toast.LENGTH_SHORT).show ();
            }

        }

    }

}

//source: https://www.youtube.com/watch?v=mmuz8qIWcL8&t=556s