package myapp.com.f1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates list of all alarms and provides CANCEL and SET button
 * @author Rafid Ishrak Jahan
 * @version 1.0
 */

public class All_alarm extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<Boolean> status_data = new ArrayList<Boolean>();
    DatabaseHelper mDatabaseHelper;
    static int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_all_alarm);
        ListView lv = (ListView) findViewById(R.id.listview);
        mDatabaseHelper = new DatabaseHelper(this);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
    }

    /**
     * Maintains the activity stack
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent (All_alarm.this,homeactivity.class);
        startActivity (intent);
    }

    /**
     * Generate a list of medication name by retrieving from the SQLite
     */
    private void generateListContent() {

        Cursor cursor_data = mDatabaseHelper.getData();
        while(cursor_data.moveToNext()){
            if(cursor_data.getString(3).equalsIgnoreCase("1") ){
                status_data.add(false);
                data.add(cursor_data.getString(1) +" \nAlarm Enable "+"\nAlarm time:"+cursor_data.getString(2) );
            }
            else {
                data.add(cursor_data.getString(1) + " \nAlarm Disable"+"\nAlarm time:"+cursor_data.getString(2));
                status_data.add(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Refers to action bar icons
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Creates GUI for alarm page
     */
    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        int index=0;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.set_alarm = (Button) convertView.findViewById(R.id.list_item_set);
                viewHolder.cancel_alarm = (Button) convertView.findViewById(R.id.list_item_cancel);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();

            mainViewholder.set_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String alarm_info = Integer.toString(position) + "," + Integer.toString(1);
                    Intent intent = new Intent(All_alarm.this, Alarm_set.class);
                    intent.putExtra("alarm_code",alarm_info);
                    Toast.makeText(getContext(), "Button was enabled for list item " + position, Toast.LENGTH_SHORT).show();
                    data.clear ();
                    startActivity(intent);
                }
            });
            mainViewholder.cancel_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String alarm_info = Integer.toString(position) + "," + Integer.toString(0);
                    Intent intent = new Intent(All_alarm.this, Alarm_set.class);
                    intent.putExtra("alarm_code",alarm_info);
                    Toast.makeText(getContext(), "Button was disabled for list item " + position, Toast.LENGTH_SHORT).show();
                    data.clear ();
                    startActivity(intent);
                }
            });

            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }

    /**
     * Creates items for each alarm item
     */
    public class ViewHolder {
        ImageView thumbnail;
        TextView title;
        Button set_alarm;
        Button cancel_alarm;
    }
}

//Source: https://github.com/jonndavis1993/Android-Tutorials/tree/master/app