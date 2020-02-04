package com.yobuligo.timeTracker.service.timeTracker;

import com.yobuligo.timeTracker.model.subject.ISubject;

public interface ITimeTracker {
    void startTracking(ISubject subject);

    void stopTracking();

    Boolean isSubjectActive(ISubject subject);

    ITimeFrameList getTimeFrameList();
}
