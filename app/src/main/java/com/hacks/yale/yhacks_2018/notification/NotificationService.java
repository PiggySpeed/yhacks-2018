package com.hacks.yale.yhacks_2018.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hacks.yale.yhacks_2018.MainActivity;
import com.hacks.yale.yhacks_2018.R;

public class NotificationService extends IntentService {

    MainActivity mainActivity;
    NotificationManager notificationManager;
    /**
     * A constructor is required, and must call the super <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public NotificationService(MainActivity mainActivity1, NotificationManager notificationManager1) {
        super("NotificationService");
        mainActivity = mainActivity1;
        notificationManager = notificationManager1;
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        Log.i("NotificationService", "successful");
//        try {
//        } catch (e) {
//            // Restore interrupt status.
//            Thread.currentThread().interrupt();
//        }
    }

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(mainActivity, 0, new Intent(mainActivity, MainActivity.class), 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(mainActivity)
                .setTicker("Drug Shortage Report")
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Drug Shortage Report - Bisoprolol Fumarate")
                .setContentText("Out of stock - Resupply expected in January 2019. 1 patient(s) affected.")
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(0, notification);
    }
}