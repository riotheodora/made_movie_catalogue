package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ReminderActivity extends AppCompatActivity {
    Switch dailyReminder;
    Switch newMovieReminder;

    DailyAlarmReceiver dailyAlarmReceiver;
    NewMovieAlarmReceiver newMovieAlarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        dailyReminder = findViewById(R.id.sw_daily_reminder);
        newMovieReminder = findViewById(R.id.sw_new_reminder);
        dailyAlarmReceiver = new DailyAlarmReceiver();
        newMovieAlarmReceiver = new NewMovieAlarmReceiver();

        if (dailyAlarmReceiver.isAlarmSet(this)) {
            dailyReminder.setChecked(true);
        }

        if (newMovieAlarmReceiver.isAlarmSet(this)) {
            newMovieReminder.setChecked(true);
        }

        dailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    dailyAlarmReceiver.setDailyAlarm(getApplicationContext());
                } else {
                    dailyAlarmReceiver.cancelAlarm(getApplicationContext());

                }
            }
        });

        newMovieReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    newMovieAlarmReceiver.setNewMovieAlarm(getApplicationContext());
                } else {
                    newMovieAlarmReceiver.cancelAlarm(getApplicationContext());
                }
            }
        });
    }
}
