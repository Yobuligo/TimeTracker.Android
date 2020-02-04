package com.yobuligo.timeTracker.TimeTracker;

import com.yobuligo.timeTracker.Subject.ISubject;
import com.yobuligo.timeTracker.Subject.Location;

import java.util.Date;

public interface ITimeFrame {

    ISubject getSubject();

    Date getStartTime();

    Date getEndTime();

    Location getLocation();

    void start();

    void stop();
}
