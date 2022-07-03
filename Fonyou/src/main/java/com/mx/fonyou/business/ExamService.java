package com.mx.fonyou.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.fonyou.dto.AssignExamDTO;
import com.mx.fonyou.dto.AssignExamResponse;
import com.mx.fonyou.dto.CorrectQuestionsDTO;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Exam;

import java.util.Optional;

public interface ExamService {
    Optional<Exam> findByIdExam (String id);

    Exam createExam(ExamDTO examDTO) throws JsonProcessingException, Exception;

    AssignExamResponse assingExamnToStudent(AssignExamDTO assignExamnDTO) throws Exception;

    CorrectQuestionsDTO qualifyExam(String examId) throws Exception;
}
