package com.yobuligo.timeTracker.Subject;

public interface ISubject {

    String getDescription();

    Location getLocation();

    void setLocation(Location location);

    Boolean isLocationable();

    Boolean supportsVariableTimeTracking();

    String getColorCode();
}
