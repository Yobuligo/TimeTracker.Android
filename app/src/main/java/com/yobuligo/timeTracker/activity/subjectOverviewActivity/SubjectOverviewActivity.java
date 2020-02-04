package com.yobuligo.timeTracker.activity.subjectOverviewActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.activity.BaseActivity;
import com.yobuligo.timeTracker.model.subject.SubjectLoader;

public class SubjectOverviewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubjectOverviewAdapter subjectOverviewAdapter = new SubjectOverviewAdapter(this, getSubjectContext());
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SubjectLoader subjectLoader = new SubjectLoader(subjectOverviewAdapter, getSubjectContext());
        subjectLoader.execute();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(subjectOverviewAdapter);
    }
}
