package com.yobuligo.timeTracker.TimeTracker;

import java.util.ArrayList;

public class TimeFrameList {

    private ArrayList<TimeFrame> timeFrames = new ArrayList<>();

    public void addTimeFrame(TimeFrame timeFrame) {
        timeFrames.add(timeFrame);
    }

    public ArrayList<TimeFrame> getTimeFrames() {
        return timeFrames;
    }

}
