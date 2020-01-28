package com.yobuligo.timeTracker.Subject;

import java.util.ArrayList;

public class SubjectList {

    private ArrayList<Subject> subjects = new ArrayList<>();

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void AddSubject(Subject subject) {
        subjects.add(subject);
    }

}
