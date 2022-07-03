package com.mx.fonyou.business;

import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Students;

public interface StudentService {
    Students createStudent(Students student);

    void saveQuestions(ExamDTO examDTO, String studentId) throws Exception;
}
