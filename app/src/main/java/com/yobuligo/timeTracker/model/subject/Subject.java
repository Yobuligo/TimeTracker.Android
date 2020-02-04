package com.yobuligo.timeTracker.model.subject;

import java.util.Date;

public class Subject implements ISubject {

    private Long id;
    private String description;
    private Date createdAt;
    private Date changedAt;
    private Location location;
    private Boolean locationable;
    private Boolean supportsVariableTimeTracking;
    private String colorCode;

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

    public String getColorCode() {
        return colorCode;
    }
}
