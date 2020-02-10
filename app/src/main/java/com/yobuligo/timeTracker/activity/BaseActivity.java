package com.yobuligo.timeTracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.activity.subjectOverviewActivity.SubjectOverviewActivity;
import com.yobuligo.timeTracker.activity.timeFrameOverviewActivity.TimeFrameOverviewActivity;
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
        IApplicationContext applicationContext = ApplicationContext.getInstance();
        return applicationContext.getSubjectContext();
    }

    class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Class activityClass;
            switch (menuItem.getItemId()) {
                case R.id.menu_item_home:
                    activityClass = SubjectOverviewActivity.class;
                    break;
                case R.id.menu_item_tracked_time:
                    activityClass = TimeFrameOverviewActivity.class;
                    break;
                default:
                    return false;
            }

            Intent intent = new Intent(getApplicationContext(), activityClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
    }

}
