package com.mx.fonyou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.fonyou.business.AnswerService;
import com.mx.fonyou.dao.IExamDAO;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Answers;
import com.mx.fonyou.entity.Exam;
import com.mx.fonyou.entity.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/fonyou/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/correct")
    public ResponseEntity<Answers> correct(@RequestParam String examId,
                                           @RequestBody ExamDTO examDTO) throws Exception {
        Answers answers = answerService.saveAnswers(examDTO, examId);
        return new ResponseEntity<Answers>(answers, HttpStatus.CREATED);
    }
}
