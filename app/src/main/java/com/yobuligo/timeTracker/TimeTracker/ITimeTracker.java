package com.yobuligo.timeTracker.TimeTracker;

import com.yobuligo.timeTracker.Subject.ISubject;

public interface ITimeTracker {
    void startTracking(ISubject subject);

    void stopTracking();

    Boolean isSubjectActive(ISubject subject);

    ITimeFrameList getTimeFrameList();
}
