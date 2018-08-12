package com.example.cjn9414.practice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    Context appContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        appContext = getApplicationContext();
        final NotificationCompat.Builder mBuilder = getNotificationCompat();
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final int notificationID = 12345;
        final Button contactButton = (Button) this.findViewById(R.id.contact_button);
        final Button otherAppsButton = (Button) this.findViewById(R.id.other_app_button);
        final Button helpButton = (Button) this.findViewById(R.id.help_button);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.notify(notificationID, mBuilder.build());

            }
        });
        otherAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing yet
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing yet
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_id);
            String description = getString(R.string.channel_desc);
            NotificationChannel channel = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID,
                    name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private NotificationCompat.Builder getNotificationCompat() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        return new NotificationCompat.Builder(appContext, NotificationChannel.DEFAULT_CHANNEL_ID)
                .setContentTitle("Notification Alert")
                .setContentText("Android Notification Detail..")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.small_notification_icon)
                .setAutoCancel(true);
    }
}
