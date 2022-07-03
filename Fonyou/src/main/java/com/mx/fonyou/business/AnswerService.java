package com.mx.fonyou.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Answers;

public interface AnswerService {

    Answers saveAnswers(ExamDTO examDTO, String examId) throws Exception;
}
