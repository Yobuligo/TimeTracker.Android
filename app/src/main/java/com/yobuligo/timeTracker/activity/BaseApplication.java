package com.yobuligo.timeTracker.activity;

import android.app.Application;

import com.yobuligo.timeTracker.model.subject.ISubjectContext;
import com.yobuligo.timeTracker.model.subject.SubjectContext;

public class BaseApplication extends Application {

    private ISubjectContext subjectContext = new SubjectContext();

    protected ISubjectContext getSubjectContext() {
        return subjectContext;
    }

}
