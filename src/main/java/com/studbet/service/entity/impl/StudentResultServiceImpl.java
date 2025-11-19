package com.studbet.service.entity.impl;

import com.studbet.dao.StudentResultDao;
import com.studbet.model.StudentResult;
import com.studbet.service.entity.StudentResultService;

public class StudentResultServiceImpl implements StudentResultService {

    StudentResultDao studentResultDao;

    public StudentResultServiceImpl(StudentResultDao studentResultDao) {
        this.studentResultDao = studentResultDao;
    }

    @Override
    public void createStudentResult(StudentResult studentResult) {
        studentResultDao.save(studentResult);
    }
}

