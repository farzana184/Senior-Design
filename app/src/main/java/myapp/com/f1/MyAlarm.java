package myapp.com.f1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.os.Vibrator;

import android.app.Activity;

import android.content.Context;

import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Creates reminder using tone, vibration and notification
 * @author Rafid Ishrak Jahan
 * @version 1.0
 */

public class MyAlarm extends BroadcastReceiver {

    /**
     * Generates reminder after receiving a request from BroadcastReceiver
     * @param context: context
     * @param intent: pending intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {



        String message = intent.getStringExtra("alarm_code");
        String medication = intent.getStringExtra ("medication");
        int alarm_code = Integer.parseInt(message);
        //Adding tone
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();

        //Adding vibration
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);

        //Building notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent repeating_activity = new Intent(context,MainActivity.class);
        repeating_activity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,alarm_code, repeating_activity, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Medication time")
                .setContentText("Take your medication")
                .setAutoCancel(true);
        notificationManager.notify(alarm_code,builder.build());


    }
}
//Sources
//https://www.youtube.com/watch?v=QcF4M2yUpY4
//https://stackoverflow.com/questions/12785702/android-set-multiple-alarms