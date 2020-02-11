package com.yobuligo.timeTracker.activity.timeFrameOverviewActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.activity.BaseActivity;

public class TimeFrameOverviewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_frame_list);

        TimeFrameOverviewAdapter timeFrameOverviewAdapter = new TimeFrameOverviewAdapter(this, getSubjectContext());
        RecyclerView recyclerView = findViewById(R.id.ui_recycler_view_time_frame);
        recyclerView.setAdapter(timeFrameOverviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}