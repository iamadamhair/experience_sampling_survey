package edu.tamu.adamhair.experiencesamplingsurvey;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.view.View;

/**
 * Created by adamhair on 12/23/2015.
 */
public class NotificationHandler extends AppCompatActivity {

    // From tutorial on Vogella.com
    public void createNotification(View view) {
        Intent intent = new Intent(this, SurveyScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(this).setContentTitle("Time to record your current activity")
                .setContentIntent(pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0,notification);
    }

}
