package com.yobuligo.timeTracker.Subject;

import com.yobuligo.timeTracker.TimeTracker.ITimeTracker;
import com.yobuligo.timeTracker.TimeTracker.TimeTracker;

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
