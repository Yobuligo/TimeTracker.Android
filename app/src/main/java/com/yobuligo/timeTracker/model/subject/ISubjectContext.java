package com.yobuligo.timeTracker.model.subject;

import com.yobuligo.timeTracker.service.timeTracker.ITimeTracker;

public interface ISubjectContext {

    ISubjectList getSubjectList();

    ITimeTracker getTimeTracker();
}
