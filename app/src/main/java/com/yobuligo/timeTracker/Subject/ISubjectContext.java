package com.yobuligo.timeTracker.Subject;

import com.yobuligo.timeTracker.TimeTracker.ITimeTracker;

public interface ISubjectContext {

    ISubjectList getSubjectList();

    ITimeTracker getTimeTracker();
}
