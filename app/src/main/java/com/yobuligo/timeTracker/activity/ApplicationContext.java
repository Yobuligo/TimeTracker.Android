package com.yobuligo.timeTracker.activity;

import com.yobuligo.timeTracker.model.subject.ISubjectContext;
import com.yobuligo.timeTracker.model.subject.SubjectContext;

public class ApplicationContext implements IApplicationContext {

    private static ApplicationContext applicationContext;
    private ISubjectContext subjectContext = new SubjectContext();
    private Boolean isSubjectContextLoaded = false;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    @Override
    public ISubjectContext getSubjectContext() {
        return subjectContext;
    }

    @Override
    public Boolean isSubjectContextLoaded() {
        return isSubjectContextLoaded;
    }

    @Override
    public void setSubjectContextLoaded(Boolean isSubjectContextLoaded) {
        this.isSubjectContextLoaded = isSubjectContextLoaded;
    }

}
