package com.yobuligo.timeTracker;

import android.app.Application;

import com.yobuligo.timeTracker.Subject.SubjectContext;

public class CustomApplication extends Application {

    private SubjectContext subjectContext = new SubjectContext();

    protected SubjectContext getSubjectContext() {
        return subjectContext;
    }
}
