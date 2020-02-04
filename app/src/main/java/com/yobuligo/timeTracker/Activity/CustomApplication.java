package com.yobuligo.timeTracker.Activity;

import android.app.Application;

import com.yobuligo.timeTracker.Subject.ISubjectContext;
import com.yobuligo.timeTracker.Subject.SubjectContext;

public class CustomApplication extends Application {

    private ISubjectContext subjectContext = new SubjectContext();

    protected ISubjectContext getSubjectContext() {
        return subjectContext;
    }
}
