package myapp.com.f1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import android.widget.ExpandableListView;

/**
 * Create an usermanual for the app
 * @author Rafid Ishrak Jahan
 * @version 1.0
 */

public class UserManual extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }


    /**
     * Set user manual items
     */
    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Medication");
        listDataHeader.add("Medication List");
        listDataHeader.add("Reminder");
        listDataHeader.add("Doctor's note");
        listDataHeader.add("Patient History");

        List<String> medication = new ArrayList<>();
        medication.add("Medication feature provides medication name insertion facility. User can insert medication name manually " +
                "or automatically.\n\n" +
                "• \tAutomatic insertion: In the Medication insertion page, user can click on the camera icon on the upper right corner of " +
                "the screen. After clicking the camera icon, MedApp will show two options Camera and Gallery. Both options can be used " +
                "for medication name insertion. Camera option will open the device camera (it may ask for permission). After opening the " +
                "device camera, place the medication bottle in front of the camera (medication name should be visible on the camera). " +
                "After clicking a picture, it will show the captured image with a crop window. User can extract the desired portion of " +
                "the text by placing the crop window in the medication name. The crop button is in the upper right corner of the screen. " +
                "User may use the rotation feature to rotate the captured image by pressing the rotate icon on the top of the screen. User " +
                "can also flip the captured image by pressing the flip icon at the top of the screen. After selecting the desired portion " +
                "of the text, MedApp will show the extracted text in the text box of the screen. User may edit the text using the keyboard." +
                " User will be able to save medication name by clicking the ADD button. However, users can extract text from Gallery image " +
                "using the same procedure.\n\n" +
                "• \tManual insertion: Manual insertion is very similar to automatic insertion without using the camera or gallery option. In " +
                "the Medication insertion page, user can type medication name using the keyboard.\n\n" +
                "User will be able to view saved medication name from the Medication insertion page by clicking the VIEW button. View Medication " +
                "list page also provides options for DELETE or UPDATE medication name. To update or delete the medication name, the user first " +
                "needs to click desire medication name from the list. After clicking the medication name, MedApp will show medication name below " +
                "screen in the text field. Now, users can press UPDATE or DELETE button to change. View Medication page will show the update result " +
                "immediately on the screen.\n\n");

        List<String> medication_list = new ArrayList<>();
        medication_list.add("View medication page can also be accessed from the Homepage by pressing Medication list button. Medication list" +
                " button provides the same functionality mention above Medication section (only Medication list page portion).");

        List<String> reminder = new ArrayList<>();
        reminder.add("Using the reminder feature, users will be able to get reminders for medications. In other words, MedApp will remind " +
                "users about their medication. MedApp system provides reminder using default notification tone, vibration for 400 milliseconds," +
                " and notification. Users can begin setting a reminder by clicking the Reminder button. In the reminder page, MedApp shows alarm " +
                "list page. Alarm list page shows all previously saved medication name in the system. User can set alarm for each of the medication " +
                "individually.  Each of the item in the Reminder page contains two buttons such as SET or CANCEL. \n\n" +
                "• \tSET: User can set a reminder using the SET button. After clicking the SET button, MedApp shows a page with a clock dial" +
                " and two buttons (SET ALARM AND CANCEL ALARM). User can set alarm time using Clock dial. After placing the clock dial into" +
                " the desired position, user can click SET ALARM button to set an alarm.\n\n" +
                "• \tCANCEL: User can cancel reminder using CANCEL button. After clicking the CANCEL button, MedApp shows a page with a clock" +
                " dial and two buttons (SET ALARM AND CANCEL ALARM). User can cancel alarm time using Clock dial. User should place the clock dial" +
                " same time which previously used at the time of setting alarm. After placing the clock dial into the desired position, user can click " +
                "CANCEL ALARM button to cancel an alarm.\n\n" +
                "Reminder list page also shows each alarm status and alarm time.\n\n");

        List<String> doctors_note = new ArrayList<>();
        doctors_note.add("Using the doctor’s suggestion, users can store important notes in the MedApp. To add a doctor’s suggestion, click the doctor’s " +
                "suggestion on the homepage. MedApp will redirect the user to the Doctor’s suggestion page. User can write useful information in the given text" +
                " field. After writing doctor’s suggestions, users can click ADD button to save information in the MedApp.\n" +
                "To update a doctor’s suggestion, users can simply click the suggestion item. After clicking the suggestion item, MedApp will show" +
                " clicked item in the text box. After updating the item, users can update the item by clicking the UPDATE button. \n" +
                "User can delete suggestion by selecting a suggestion item. After selecting the suggested item, users can click on the DELETE button" +
                " to delete suggestions. \n");


        List<String> patient_history = new ArrayList<>();
        patient_history.add("The user, or the patient, can store and update their information. By the Profile page, the user enters their " +
                "information that the doctors want to know. The user can enter the 10 following items of their profile data by typing on " +
                "the smart phone's keyboard.\n" +
                "* User's first and last name\n" +
                "* Date of birth\n" +
                "* Gender\n" +
                "* Phone number\n" +
                "* E-mail address\n" +
                "* Blood pressure (systolic pressure / diastolic pressure)\n" +
                "* Blood sugar (more precisely, blood glucose )\n" +
                "* Allergies the user has\n" +
                "* Surgeries performed on the user\n" +
                "* List of medications the user is taking\n" +
                "The user can arrive at the User Profile page coming from the start page by tapping the User Profile icon. For each item, a relevant keyboard will be shown. For example, for the phone number field, the available keyboard will be numbers and parenthesis and so on for the rest of the profile items.\n");

        listHash.put(listDataHeader.get(0),medication);
        listHash.put(listDataHeader.get(1),medication_list);
        listHash.put(listDataHeader.get(2),reminder);
        listHash.put(listDataHeader.get(3),doctors_note);
        listHash.put(listDataHeader.get(4),patient_history);
    }
}
//Source: https://github.com/eddydn/ExpandableListView