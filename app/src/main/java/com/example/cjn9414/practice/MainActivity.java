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
import android.widget.Switch;
import android.widget.Toast;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context appContext;
    private Switch rootSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        appContext = getApplicationContext();
        rootSwitch = findViewById(R.id.root_priority_switch);
        final Button contactButton = this.findViewById(R.id.contact_button);
        final Button otherAppsButton = this.findViewById(R.id.other_app_button);
        final Button helpButton = this.findViewById(R.id.help_button);
        contactButton.setOnClickListener(this);
        otherAppsButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.contact_button:
                if (rootSwitch.isChecked()) {
                    Intent contactIntent = new Intent(this, ContactActivity.class);
                    startActivity(contactIntent);
                }
                break;
            case R.id.other_app_button:
//                Intent otherAppIntent = new Intent(this, OtherAppActivity.class);
//                startActivity(otherAppIntent);
                break;
            case R.id.help_button:
//                Intent helpIntent = new Intent(this, HelpActivty.class);
//                startActivity(helpIntent);
                break;
            default:
                break;
        }
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
