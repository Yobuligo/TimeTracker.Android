package com.yobuligo.timeTracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yobuligo.timeTracker.Subject.ISubjectContext;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected ISubjectContext getSubjectContext() {
        CustomApplication customApplication = (CustomApplication) getApplication();
        return customApplication.getSubjectContext();
    }
}
