package com.studbet.service.entity;

import com.studbet.model.Subject;

import java.io.IOException;

public interface SubjectService {
    void createSubject(Subject subject);
    Subject getSubjectById(int id);
}
