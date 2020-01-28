package com.yobuligo.timeTracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yobuligo.timeTracker.Subject.SubjectAdapter;
import com.yobuligo.timeTracker.Subject.Subject;
import com.yobuligo.timeTracker.Subject.SubjectList;
import com.yobuligo.timeTracker.Subject.SubjectLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SubjectList subjectList = new SubjectList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SubjectAdapter subjectAdapter = new SubjectAdapter(this, subjectList);

        SubjectLoader subjectLoader = new SubjectLoader(subjectAdapter, subjectList);
        subjectLoader.execute();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(subjectAdapter);
    }
}
