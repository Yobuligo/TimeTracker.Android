package com.yobuligo.timeTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.yobuligo.timeTracker.Subject.SubjectContext;

public class TimeFrameListActivity extends AppCompatActivity {
    private SubjectContext subjectContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_frame_list);
        CustomApplication customApplication = (CustomApplication) getApplication();
        subjectContext = customApplication.getSubjectContext();
        subjectContext.getTimeTracker().getTimeFrameList();
    }
}