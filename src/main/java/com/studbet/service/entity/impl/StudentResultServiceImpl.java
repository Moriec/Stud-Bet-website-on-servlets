package com.studbet.service.entity.impl;

import com.studbet.dao.StudentResultDao;
import com.studbet.model.StudentResult;
import com.studbet.service.entity.StudentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentResultServiceImpl implements StudentResultService {

    private final StudentResultDao studentResultDao;

    @Override
    public void createStudentResult(StudentResult studentResult) {
        studentResultDao.save(studentResult);
    }
}

