package com.studbet.service.entity.impl;

import com.studbet.dao.SubjectDao;
import com.studbet.dao.impl.SubjectDaoImpl;
import com.studbet.model.Subject;
import com.studbet.service.entity.SubjectService;

public class SubjectServiceImpl implements SubjectService {

    SubjectDao  subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public void createSubject(Subject subject) {
        subjectDao.save(subject);
    }
}
