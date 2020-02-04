package com.yobuligo.timeTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.yobuligo.timeTracker.Subject.SubjectAdapter;
import com.yobuligo.timeTracker.Subject.Subject;
import com.yobuligo.timeTracker.Subject.SubjectContext;
import com.yobuligo.timeTracker.Subject.SubjectList;
import com.yobuligo.timeTracker.Subject.SubjectLoader;
import com.yobuligo.timeTracker.TimeTracker.TimeTracker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SubjectContext subjectContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication customApplication = (CustomApplication) getApplication();
        subjectContext = customApplication.getSubjectContext();

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SubjectAdapter subjectAdapter = new SubjectAdapter(this, subjectContext);

        SubjectLoader subjectLoader = new SubjectLoader(subjectAdapter, subjectContext);
        subjectLoader.execute();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(subjectAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationViewListener());
    }

    class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.menu_item_tracked_time) {
                Intent showTimeFrameListActivityIntent = new Intent(MainActivity.this, TimeFrameListActivity.class);
                startActivity(showTimeFrameListActivityIntent);
                return true;
            }

            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_tracked_time) {
            Intent showTimeFrameListActivityIntent = new Intent(this, TimeFrameListActivity.class);
            startActivity(showTimeFrameListActivityIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
