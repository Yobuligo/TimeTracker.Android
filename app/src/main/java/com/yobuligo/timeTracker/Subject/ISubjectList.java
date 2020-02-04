package com.yobuligo.timeTracker.Subject;

import java.util.ArrayList;

public interface ISubjectList {

    ArrayList<ISubject> getSubjects();

    void AddSubject(ISubject subject);
}
