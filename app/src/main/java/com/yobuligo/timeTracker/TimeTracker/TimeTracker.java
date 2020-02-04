package com.yobuligo.timeTracker.TimeTracker;

import android.util.Log;

import com.yobuligo.timeTracker.Subject.Subject;

public class TimeTracker {

    private TimeFrame activeTimeFrame;
    private TimeFrameList timeFrameList = new TimeFrameList();

    public void startTracking(Subject subject) {
        if (activeTimeFrame != null) {
            if (activeTimeFrame.getSubject() == subject) {
                return;
            }
            stopActiveTimeFrame();
        }
        startNewTimeFrame(subject);
    }

    public void stopTracking() {
        stopActiveTimeFrame();
    }

    public Boolean isSubjectActive(Subject subject) {
        if (activeTimeFrame == null) {
            return false;
        }

        return activeTimeFrame.getSubject() == subject;
    }

    public TimeFrameList getTimeFrameList() {
        return timeFrameList;
    }

    private void stopActiveTimeFrame() {
        if (activeTimeFrame == null) {
            return;
        }

        activeTimeFrame.stop();
        activeTimeFrame = null;
        printTimeFrameList();
    }

    private void startNewTimeFrame(Subject subject) {
        TimeFrame timeFrame = new TimeFrame(subject);
        timeFrameList.addTimeFrame(timeFrame);
        activeTimeFrame = timeFrame;
        activeTimeFrame.start();
        printTimeFrameList();
    }

    private void printTimeFrameList() {
        for (TimeFrame timeFrame :
                timeFrameList.getTimeFrames()) {
            Log.i(getClass().toString(), "printTimeFrame: "
                    + timeFrame.getSubject().getDescription() + " "
                    + timeFrame.getLocation() + " "
                    + timeFrame.getStartTime() + " "
                    + timeFrame.getEndTime()
            );
        }
    }
}
