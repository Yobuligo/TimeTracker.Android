package com.yobuligo.timeTracker.model.subject;

import java.util.ArrayList;

public interface ISubjectList {

    ArrayList<ISubject> getSubjects();

    void AddSubject(ISubject subject);
}
