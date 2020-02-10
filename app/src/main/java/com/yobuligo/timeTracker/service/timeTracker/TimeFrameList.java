package com.yobuligo.timeTracker.service.timeTracker;

import java.util.ArrayList;

public class TimeFrameList implements ITimeFrameList {

    private ArrayList<ITimeFrame> timeFrames = new ArrayList<>();

    /*
    Adds a time Frame to the time frame list. The Element is set at the top of the list.
    Last in first out.
     */
    public void addTimeFrame(ITimeFrame timeFrame) {
        timeFrames.add(0, timeFrame);
    }

    public ArrayList<ITimeFrame> getTimeFrames() {
        return timeFrames;
    }

}
