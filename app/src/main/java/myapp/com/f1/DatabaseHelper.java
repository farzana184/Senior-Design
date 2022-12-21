package myapp.com.f1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Database Manager: facilitates communication with SQLite DB and MedApp
 * @author Rafid Ishrak Jahan, Farzana Rahman, Mohammed Al Aadhami
 * @version 1.0
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String db_name = "register.db";

    public static final String t_name = "users_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "username";
    public static final String col_3 = "password";


    private static final String DB_Table = "med_table";  //table name
    private static final String ID = "ID";   //id
    private static final String NAME = "NAME";

    private static final String COL3 = "alarm_time";
    private static final String COL4 = "status";


    private static final String note_t = "note_table";  //table name
    private static final String ID1 = "ID";   //id
    private static final String note = "note";

    public static final String userprofile_table = "userprofile_table"; // table name
    public static final String profile_col_0 = "id";
    public static final String profile_col_1 = "name1";
    public static final String profile_col_2 = "dateOfBirth";
    public static final String profile_col_3 = "gender";
    public static final String profile_col_4 = "phoneNum";
    public static final String profile_col_5 = "emailAddress";
    public static final String profile_col_6 = "bloodPressure";
    public static final String profile_col_7 = "bloodSugar";
    public static final String profile_col_8 = "allergies";
    public static final String profile_col_9 = "surgeries";
    public static final String profile_col_10 = "medications";





    public static final String Create_Table= "Create Table "+ DB_Table +
            "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT," +COL3 +" TEXT,"+COL4 +" TEXT)";



    public static final String Create_note= "Create Table "+ note_t +
            "(" + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            note + " TEXT " + " )" ;

    public static final String Create_Table1= "Create Table "+ userprofile_table +
            "(" + profile_col_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            profile_col_1 + " TEXT," +
            profile_col_2 + " TEXT," +
            profile_col_3 + " TEXT," +
            profile_col_4 + " TEXT," +
            profile_col_5 + " TEXT," +
            profile_col_6 + " TEXT," +
            profile_col_7 + " TEXT," +
            profile_col_8 + " TEXT," +
            profile_col_9 + " TEXT," +
            profile_col_10 + " TEXT)";


    public DatabaseHelper(Context context) {
        super (context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ("CREATE TABLE users_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT )");
//db.execSQL (reg_t);
        db.execSQL (Create_Table);
        db.execSQL (Create_note);
        db.execSQL(Create_Table1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL (" DROP TABLE IF EXISTS " + t_name);
        db.execSQL (" DROP TABLE IF EXISTS " + DB_Table  );
        db.execSQL (" DROP TABLE IF EXISTS " + note_t  );
        db.execSQL("DROP TABLE IF EXISTS "+ userprofile_table);
        onCreate (db);
    }

    /**
     * Inser medication data
     * @param name medication information
     * @param  alarm_time1 alarm time
     * @param status1 alarm status
     *@return boolean
     */
    public boolean insertData (String name,String alarm_time1, String status1)              ///method to insert data into database
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put (NAME,name);       //one for one column
        contentValues.put (COL3,alarm_time1);
        contentValues.put (COL4,status1);
        long result = db.insert (DB_Table,null, contentValues);
        return result != -1;


    }


    /**
     * Checks for drug interaction
     * @param n to be inserted medication information
     * @return cursor
     */
    public Cursor sr(String n) {

        SQLiteDatabase db = this.getWritableDatabase ();
        String query =  "Select * from "+ DB_Table + " WHERE " + NAME+ " like '%"+n+"%'" ;
        Cursor cursor = db.rawQuery (query,null);
        return cursor;
    }

    /**
     * Insert data for doctor's suggestion
     * @param nt to be inserted notes
     * @return cursor
     */
    public boolean insertNote(String nt)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put (note,nt);       //one for one column
        long result = db.insert (note_t,null, contentValues);
        return result != -1;

    }
    /**
     * Retrieves medication information
     * @return cursor
     */
    public Cursor viewData()              ///method to view data
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query =  "Select * from " + DB_Table;
        Cursor cursor = db.rawQuery (query,null);
        return cursor;


    }
    /**
     * Retrieves doctor's suggestion data
     * @return boolean
     */
    public Cursor viewNotes()              ///method to view data
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query =  "Select * from " + note_t;
        Cursor cursor = db.rawQuery (query,null);
        return cursor;
    }
    /**
     * Delete medication information from SQLite DB
     * @param n to be deleted medication information
     */
    public void deletedata(String n)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query = "DELETE FROM " + DB_Table + " WHERE " + NAME+ " = '" + n + "'";
        Log.d(TAG, "deletedata: Deleting " + n + " from database.");
        db.execSQL(query);
    }
    /**
     * Delete doctor's suggestion information from SQLite DB
     * @param n to be deleted doctor's suggestion information
     */
    public void deletenote(String n)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query = "DELETE FROM " + note_t + " WHERE " + note+ " = '" + n + "'";
        Log.d(TAG, "deletedata: Deleting " + n + " from database.");
        db.execSQL(query);
    }

    /**
     * add new user to SQLite DB
     * @param user to be inserted user name
     * @param password to be inserted password of the user
     * @return long
     */
    public long adduser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("username", user);
        contentValues.put ("password", password);
        long res = db.insert (t_name, null, contentValues);
        db.close ();
        return res;
    }

    /**
     * Check user for login
     * @param username username
     * @param password password
     * @return boolean
     */
    public boolean check(String username, String password) {
        String[] cols = {col_1};
        SQLiteDatabase db = getReadableDatabase ();
        String selection = col_2 + "=?" + " and " + col_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query (t_name, cols, selection, selectionArgs, null, null, null);
        int count = cursor.getCount ();
        cursor.close ();
        db.close ();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the ID of selected medication information
     * @param n1 selected information
     * @return Cursor
     */
    public Cursor medID(String n1)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query = "SELECT " + ID + " FROM " + DB_Table + " WHERE "
                + NAME + "= '" + n1 + "'" ;
        Cursor data = db.rawQuery (query,null);
        return data;

    }
    /**
     * Returns the ID of selected doctor's suggestion information
     * @param n1 selected information
     * @return Cursor
     */
    public Cursor noteID(String n1)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query = "SELECT " + ID1 + " FROM " + note_t + " WHERE "
                + note + "= '" + n1 + "'" ;
        Cursor data = db.rawQuery (query,null);
        return data;
    }


    /**
     * Update selected medication information
     * @param id ID of selected information
     * @param n Selected information
     * @return boolean
     */
    public  boolean updatemed(String id, String n)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put (NAME,n);
        db.update (DB_Table, contentValues, "ID = ?", new String[] {id});
        return true;
    }



    /**
     * Update selected doctor's suggestion information
     * @param id ID of selected information
     * @param n Selected information
     * @return boolean
     */
    public  boolean updatenote(String id, String n)
    {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put (note,n);
        db.update (note_t, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    /**
     * Retrieves patient alarm information
     * @return Cursor: returns alarm information from the SQLite db
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_Table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates an alarm time
     * @param newName new alarm time
     * @param id alarm id
     * @param oldName old alarm time
     */

    public void updateAlarm_Time(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + DB_Table + " SET " + COL3 +
                " = '" + newName + "' WHERE " + ID + " = '" + Integer.toString (id) + "'" +
                " AND " + COL3 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Updates an alarm status
     * @param newName new alarm status
     * @param id alarm id
     * @param oldName old alarm status
     */

    public void updateAlarm_status(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + DB_Table + " SET " + COL4 +
                " = '" + newName + "' WHERE " + ID + " = '" + Integer.toString (id) + "'" +
                " AND " + COL4 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Insert patient history data
     * @param name1 patient first and last name
     * @param dateOfBirth patient date of birth
     * @param gender patient gender
     * @param phoneNum patient phone number
     * @param emailAddress patient email address
     * @param bloodPressure patient blood pressure
     * @param bloodSugar patient blood sugar
     * @param allergies patient allergies
     * @param surgeries patient surgery information
     * @param medications patient medication information
     * @return boolean: result of the SQL query execution
     */
    public boolean insertProfileData (String name1, String dateOfBirth, String gender, String phoneNum,
                                      String emailAddress, String bloodPressure, String bloodSugar,
                                      String allergies, String surgeries, String medications) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues ();
        contentValues.put (profile_col_1, name1);
        contentValues.put (profile_col_2, dateOfBirth);
        contentValues.put (profile_col_3, gender);
        contentValues.put (profile_col_4, phoneNum);
        contentValues.put (profile_col_5, emailAddress);
        contentValues.put (profile_col_6, bloodPressure);
        contentValues.put (profile_col_7, bloodSugar);
        contentValues.put (profile_col_8, allergies);
        contentValues.put (profile_col_9, surgeries);
        contentValues.put (profile_col_10, medications);
        long result = db.insert (userprofile_table, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    /**
     * Retrieves patient history data
     * @return Cursor: returns patitent history from the SQLite db
     */
    public Cursor sr2 () {
        SQLiteDatabase db = this.getWritableDatabase ();
        String query =  "Select * from " + userprofile_table ;
        Cursor cursor = db.rawQuery (query, null);
        return cursor;
    }


}