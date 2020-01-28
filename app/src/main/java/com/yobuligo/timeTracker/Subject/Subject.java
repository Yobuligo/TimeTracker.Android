package com.yobuligo.timeTracker.Subject;

import java.util.Date;

public class Subject {

    private Long id;
    private String description;
    private Date createdAt;
    private Date changedAt;
    private Location location;
    private Boolean locationable;
    private Boolean supportsVariableTimeTracking;

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean isLocationable() {
        return locationable;
    }

    public Boolean supportsVariableTimeTracking() {
        return supportsVariableTimeTracking;
    }

}