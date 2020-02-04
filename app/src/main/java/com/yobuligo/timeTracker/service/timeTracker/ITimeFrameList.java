package com.yobuligo.timeTracker.service.timeTracker;

import java.util.ArrayList;

public interface ITimeFrameList {
    void addTimeFrame(ITimeFrame timeFrame);

    ArrayList<ITimeFrame> getTimeFrames();
}
