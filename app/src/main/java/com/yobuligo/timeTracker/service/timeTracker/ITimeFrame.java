package com.yobuligo.timeTracker.service.timeTracker;

import com.yobuligo.timeTracker.model.subject.ISubject;
import com.yobuligo.timeTracker.model.subject.Location;

import java.util.Date;

public interface ITimeFrame {

    ISubject getSubject();

    Date getStartTime();

    Date getEndTime();

    Location getLocation();

    void start();

    void stop();
}
