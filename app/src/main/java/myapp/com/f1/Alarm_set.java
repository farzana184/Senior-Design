package myapp.com.f1;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

/**
 * Helps to set or cancel an alarms
 * @author Rafid Ishrak Jahan
 * @version 1.0
 */
public class Alarm_set extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    TimePicker timePicker;
    String alarm_info;

    private ArrayList<String> old_alarm_time = new ArrayList<String>();
    private ArrayList<String> old_status_data= new ArrayList<String>();
    String medication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        mDatabaseHelper = new DatabaseHelper(this);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        Intent in=getIntent();
        alarm_info = in.getStringExtra("alarm_code");

        String[] arrOfStr = alarm_info.split(",");

        final int alarm_position = Integer.parseInt(arrOfStr[0])+100;
        final int alarm_state = Integer.parseInt(arrOfStr[1]);

        findViewById(R.id.buttonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                } else {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

                Cursor cursor_data = mDatabaseHelper.getData();
                while(cursor_data.moveToNext()){
                    medication = cursor_data.getString (1);
                    old_alarm_time.add(cursor_data.getString(2));
                    old_status_data.add(cursor_data.getString(3));
                }

                if (alarm_state == 0 ) {
                    cancelAlarm(calendar.getTimeInMillis(), alarm_position);
                    mDatabaseHelper.updateAlarm_Time("0",alarm_position-100+1,old_alarm_time.get(alarm_position-100));
                    mDatabaseHelper.updateAlarm_status(Integer.toString(alarm_state),alarm_position-100+1,old_status_data.get(alarm_position-100));
                }
                else {
                    setAlarm(calendar.getTimeInMillis(), alarm_position);
                    int hour = timePicker.getCurrentHour ();
                    int min = timePicker.getCurrentMinute ();

                    mDatabaseHelper.updateAlarm_Time(Integer.toString (hour)+":"+Integer.toString (min),alarm_position-100+1,old_alarm_time.get(alarm_position-100));
                    mDatabaseHelper.updateAlarm_status(Integer.toString(alarm_state),alarm_position-100+1,old_status_data.get(alarm_position-100));
                }
                Intent intent = new Intent (Alarm_set.this,All_alarm.class);
                startActivity (intent);

            }
        });
        findViewById(R.id.buttonCancelAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                } else {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

                Cursor cursor_data = mDatabaseHelper.getData();
                while(cursor_data.moveToNext()){
                    medication = cursor_data.getString (1);
                    old_alarm_time.add(cursor_data.getString(2));
                    old_status_data.add(cursor_data.getString(3));
                }

                if (alarm_state == 0 ) {
                    cancelAlarm(calendar.getTimeInMillis(), alarm_position);
                    mDatabaseHelper.updateAlarm_Time("0",alarm_position-100+1,old_alarm_time.get(alarm_position-100));
                    mDatabaseHelper.updateAlarm_status(Integer.toString(alarm_state),alarm_position-100+1,old_status_data.get(alarm_position-100));
                }
                else {
                    setAlarm(calendar.getTimeInMillis(), alarm_position);
                    int hour = timePicker.getCurrentHour ();
                    int min = timePicker.getCurrentMinute ();
                    mDatabaseHelper.updateAlarm_Time(Integer.toString (hour)+":"+Integer.toString (min),alarm_position-100+1,old_alarm_time.get(alarm_position-100));
                    mDatabaseHelper.updateAlarm_status(Integer.toString(alarm_state),alarm_position-100+1,old_status_data.get(alarm_position-100));
                }
                Intent intent = new Intent (Alarm_set.this,All_alarm.class);
                startActivity (intent);

            }
        });

    }

    /**
     * Set alarm using AlarmManager
     * @param timeInMillis: time left for that particular alarm
     * @param alarm_code: unique code for an alarm
     */

    private void setAlarm(long timeInMillis,int alarm_code) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,MyAlarm.class);
        intent.putExtra("alarm_code",Integer.toString(alarm_code));
        intent.putExtra ("medication",medication);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,alarm_code,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent); //RTC_WAKEUP = works without running & INTERVAL_DAY
        Toast.makeText(this,"Alarm is set",Toast.LENGTH_SHORT).show();
    }

    /**
     * Cancel alarm by destroying a pending intent
     * @param timeInMillis: time left for that particular alarm
     * @param alarm_code: unique code for an alarm
     */
    private void cancelAlarm(long timeInMillis,int alarm_code) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,MyAlarm.class);
        intent.putExtra("alarm_code",Integer.toString(alarm_code));
        intent.putExtra ("medication",medication);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,alarm_code,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent); //RTC_WAKEUP = works without running & INTERVAL_DAY
        Toast.makeText(this,"Alarm is cancel",Toast.LENGTH_SHORT).show();
        alarmManager.cancel(pendingIntent);

    }


}


/* Source

https://www.youtube.com/watch?v=-Q5MFwgXIcc
https://www.simplifiedcoding.net/android-scheduled-task-example/
https://www.youtube.com/watch?v=1fV9NmvxXJo
https://www.youtube.com/watch?v=f4eYJI-5a0w
https://www.concretepage.com/android/android-alarm-clock-tutorial-to-schedule-and-cancel-alarmmanager-pendingintent-and-wakefulbroadcastreceiver-example

 */