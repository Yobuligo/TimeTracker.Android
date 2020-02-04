package com.yobuligo.timeTracker.model.subject;

import com.yobuligo.timeTracker.service.timeTracker.ITimeTracker;
import com.yobuligo.timeTracker.service.timeTracker.TimeTracker;

public class SubjectContext implements ISubjectContext {

    private ISubjectList subjectList = new SubjectList();
    private ITimeTracker timeTracker = new TimeTracker();

    public ISubjectList getSubjectList() {
        return subjectList;
    }

    public ITimeTracker getTimeTracker() {
        return timeTracker;
    }
}
