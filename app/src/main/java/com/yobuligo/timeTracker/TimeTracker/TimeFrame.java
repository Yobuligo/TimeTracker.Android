package com.yobuligo.timeTracker.TimeTracker;

import android.util.Log;

import com.yobuligo.timeTracker.Subject.Location;
import com.yobuligo.timeTracker.Subject.Subject;

import java.util.Date;

public class TimeFrame {

    private Boolean isRunning = false;
    private Subject subject;
    private Date startTime;
    private Date endTime;
    private Location location;

    public TimeFrame(Subject subject) {
        this.subject = subject;
        this.location = subject.getLocation();
    }

    public Subject getSubject() {
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
