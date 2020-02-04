package com.yobuligo.timeTracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yobuligo.timeTracker.Subject.SubjectAdapter;
import com.yobuligo.timeTracker.Subject.SubjectLoader;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SubjectAdapter subjectAdapter = new SubjectAdapter(this, getSubjectContext());

        SubjectLoader subjectLoader = new SubjectLoader(subjectAdapter, getSubjectContext());
        subjectLoader.execute();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(subjectAdapter);

        //BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationViewListener());
    }

    /*class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
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

    }*/
}
