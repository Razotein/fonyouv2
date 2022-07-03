package com.mx.fonyou.controller;

import com.mx.fonyou.business.StudentServiceImpl;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.dto.StudentDTO;
import com.mx.fonyou.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(path = "/fonyou/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentsService;

    @PostMapping
    public ResponseEntity<Students> create(@RequestBody StudentDTO studentDTO) {
        final Students student = studentsService.createStudent(new Students(
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getCity(),
                studentDTO.getLocale()
        ));
        return new ResponseEntity<Students>(student, HttpStatus.CREATED);
    }

    @PostMapping("/answer/exam")
    public ResponseEntity<String> answerExam(@RequestParam String studentId,
                                             @RequestBody ExamDTO examDTO) throws Exception {
        studentsService.saveQuestions(examDTO, studentId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
