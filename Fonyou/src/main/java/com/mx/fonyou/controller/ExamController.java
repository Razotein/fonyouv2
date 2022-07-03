package com.mx.fonyou.controller;

import com.mx.fonyou.business.ExamService;
import com.mx.fonyou.dto.AssignExamDTO;
import com.mx.fonyou.dto.AssignExamResponse;
import com.mx.fonyou.dto.CorrectQuestionsDTO;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/fonyou/api/exam")
public class ExamController {

    @Autowired
    private ExamService examnService;

    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody ExamDTO examDTO) throws Exception {
        final Exam examn = examnService.createExam(examDTO);
        return new ResponseEntity<Exam>(examn, HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignExamResponse> assign(@RequestBody AssignExamDTO assignExamnDTO) throws Exception {
        final AssignExamResponse assignExamResponse = examnService.assingExamnToStudent(assignExamnDTO);
        return new ResponseEntity<AssignExamResponse>(assignExamResponse, HttpStatus.OK);
    }

    @PostMapping("/qualify")
    public ResponseEntity<CorrectQuestionsDTO> qualify(@RequestParam String examId) throws Exception {
        CorrectQuestionsDTO response = examnService.qualifyExam(examId);
        return new ResponseEntity<CorrectQuestionsDTO>(response, HttpStatus.OK);
    }
}
