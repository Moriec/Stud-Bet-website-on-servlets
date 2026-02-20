package com.studbet.service.entity.impl;

import com.studbet.dao.SubjectDao;
import com.studbet.model.Subject;
import com.studbet.service.entity.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDao  subjectDao;

    public void createSubject(Subject subject) {
        subjectDao.save(subject);
    }

    public Subject getSubjectById(int id) {
        return subjectDao.findById(id).orElse(null);
    }
}
