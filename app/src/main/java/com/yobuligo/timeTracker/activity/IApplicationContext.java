package com.yobuligo.timeTracker.activity;

import com.yobuligo.timeTracker.model.subject.ISubjectContext;

public interface IApplicationContext {
    ISubjectContext getSubjectContext();

    Boolean isSubjectContextLoaded();

    void setSubjectContextLoaded(Boolean isSubjectContextLoaded);
}
