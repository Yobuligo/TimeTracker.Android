package com.yobuligo.timeTracker.activity.subjectOverviewActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.activity.BaseActivity;
import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.activity.subjectOverviewActivity.SubjectOverviewActivity.SubjectAdapter;
import com.yobuligo.timeTracker.model.subject.SubjectLoader;

public class SubjectOverviewActivity extends BaseActivity {

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
    }
}
