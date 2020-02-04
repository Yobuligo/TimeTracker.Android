package com.yobuligo.timeTracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yobuligo.timeTracker.activity.subjectOverviewActivity.SubjectOverviewActivity;
import com.yobuligo.timeTracker.activity.timeFrameOverviewActivity.TimeFrameOverviewActivity;
import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.model.subject.ISubjectContext;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationViewListener());
    }

    protected ISubjectContext getSubjectContext() {
        CustomApplication customApplication = (CustomApplication) getApplication();
        return customApplication.getSubjectContext();
    }

    class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_home:
                    startActivity(new Intent(getApplicationContext(), SubjectOverviewActivity.class));
                    return true;
                case R.id.menu_item_tracked_time:
                    startActivity(new Intent(getApplicationContext(), TimeFrameOverviewActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }

}
