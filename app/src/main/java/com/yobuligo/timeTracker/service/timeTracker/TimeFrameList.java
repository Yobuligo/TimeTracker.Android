package com.yobuligo.timeTracker.service.timeTracker;

import java.util.ArrayList;

public class TimeFrameList implements ITimeFrameList {

    private ArrayList<ITimeFrame> timeFrames = new ArrayList<>();

    public void addTimeFrame(ITimeFrame timeFrame) {
        timeFrames.add(timeFrame);
    }

    public ArrayList<ITimeFrame> getTimeFrames() {
        return timeFrames;
    }

}
