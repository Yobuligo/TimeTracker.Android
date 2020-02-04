package com.yobuligo.timeTracker.service.timeTracker;

import android.util.Log;

import com.yobuligo.timeTracker.model.subject.ISubject;

public class TimeTracker implements ITimeTracker {

    private ITimeFrame activeTimeFrame;
    private ITimeFrameList timeFrameList = new TimeFrameList();

    public void startTracking(ISubject subject) {
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

    public Boolean isSubjectActive(ISubject subject) {
        if (activeTimeFrame == null) {
            return false;
        }

        return activeTimeFrame.getSubject() == subject;
    }

    public ITimeFrameList getTimeFrameList() {
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

    private void startNewTimeFrame(ISubject subject) {
        ITimeFrame timeFrame = new TimeFrame(subject);
        timeFrameList.addTimeFrame(timeFrame);
        activeTimeFrame = timeFrame;
        activeTimeFrame.start();
        printTimeFrameList();
    }

    private void printTimeFrameList() {
        for (ITimeFrame timeFrame :
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
