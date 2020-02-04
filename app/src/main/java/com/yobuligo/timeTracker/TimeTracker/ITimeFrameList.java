package com.yobuligo.timeTracker.TimeTracker;

import java.util.ArrayList;

public interface ITimeFrameList {
    void addTimeFrame(ITimeFrame timeFrame);

    ArrayList<ITimeFrame> getTimeFrames();
}
