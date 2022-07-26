package com.dmp.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleJob(View view){
        ComponentName  componentName = new ComponentName(this, ExpJobService.class);
        JobInfo info = new JobInfo.Builder(111,componentName)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        JobScheduler jobScheduler =     (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "scheduled Job success: ");
        }
        else {
            Log.d(TAG, "scheduledJob Failed");
        }
    }

    public void cancelJob(View v){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(111);
        Log.d(TAG, "Job canceled");
    }
}