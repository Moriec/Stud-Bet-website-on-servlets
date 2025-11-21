package com.studbet.service.entity;

import com.studbet.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SubjectService {
    void createSubject(Subject subject);
    Subject getSubjectById(int id);
}
