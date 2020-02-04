package com.yobuligo.timeTracker.service.timeTracker;

import android.util.Log;

import com.yobuligo.timeTracker.model.subject.ISubject;
import com.yobuligo.timeTracker.model.subject.Location;

import java.util.Date;

public class TimeFrame implements ITimeFrame {

    private Boolean isRunning = false;
    private ISubject subject;
    private Date startTime;
    private Date endTime;
    private Location location;

    public TimeFrame(ISubject subject) {
        this.subject = subject;
        this.location = subject.getLocation();
    }

    public ISubject getSubject() {
        return subject;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Location getLocation() {
        if (isRunning) {
            return subject.getLocation();
        } else {
            return location;
        }
    }

    public void start() {
        if (isRunning) {
            return;
        }
        startTime = new Date();
        isRunning = true;
        Log.i(getClass().toString(), "start: " + subject.getDescription());
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        endTime = new Date();
        isRunning = false;
        location = subject.getLocation();
        Log.i(getClass().toString(), "stop: " + subject.getDescription());
    }
}
