package com.yobuligo.timeTracker.Subject;

import com.yobuligo.timeTracker.TimeTracker.TimeTracker;

public class SubjectContext {

    private SubjectList subjectList = new SubjectList();
    private TimeTracker timeTracker = new TimeTracker();

    public SubjectList getSubjectList() {
        return subjectList;
    }

    public TimeTracker getTimeTracker() {
        return timeTracker;
    }
}
