package com.mx.fonyou.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.fonyou.dao.IExamDAO;
import com.mx.fonyou.dao.IQuestionsDAO;
import com.mx.fonyou.dao.IStudentsDAO;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Exam;
import com.mx.fonyou.entity.Questions;
import com.mx.fonyou.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private IStudentsDAO studentsDAO;

    @Autowired
    private IQuestionsDAO questionsDAO;

    @Autowired
    private IExamDAO examDAO;

    @Override
    public Students createStudent(final Students student) {
        student.setIdStudents(UUID.randomUUID().toString());
        return studentsDAO.save(student);
    }

    @Override
    public void saveQuestions(ExamDTO examDTO, String studentId) throws Exception {
        Optional<Students> student = studentsDAO.findById(studentId);
        student.orElseThrow(() -> new Exception("Student not found with id " + studentId));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examDTO);

        Questions question = new Questions(
                UUID.randomUUID().toString(),
                json
        );
        questionsDAO.save(question);

        Optional<Exam> exam = examDAO.findById(student.get().getExamId());
        exam.orElseThrow(() -> new Exception("Examn not found with id " + student.get().getExamId()));
        exam.get().setQuestions(question.getId());
        examDAO.saveAndFlush(exam.get());
    }
}
