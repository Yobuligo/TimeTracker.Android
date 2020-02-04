package com.yobuligo.timeTracker.model.subject;

public interface ISubject {

    String getDescription();

    Location getLocation();

    void setLocation(Location location);

    Boolean isLocationable();

    Boolean supportsVariableTimeTracking();

    String getColorCode();
}
