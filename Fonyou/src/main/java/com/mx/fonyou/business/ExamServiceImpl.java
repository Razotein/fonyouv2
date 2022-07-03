package com.mx.fonyou.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.fonyou.dao.IAnswersDAO;
import com.mx.fonyou.dao.IExamDAO;
import com.mx.fonyou.dao.IQuestionsDAO;
import com.mx.fonyou.dao.IStudentsDAO;
import com.mx.fonyou.dto.*;
import com.mx.fonyou.entity.Answers;
import com.mx.fonyou.entity.Exam;
import com.mx.fonyou.entity.Questions;
import com.mx.fonyou.entity.Students;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private IExamDAO examDAO;

    @Autowired
    private IStudentsDAO studentsDAO;

    @Autowired
    private IQuestionsDAO questionsDAO;

    @Autowired
    private IAnswersDAO answersDAO;

    @Override
    public Optional<Exam> findByIdExam(String id) {
        return examDAO.findById(id);
    }

    @Override
    public Exam createExam(ExamDTO examDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examDTO);
        return examDAO.save(new Exam(UUID.randomUUID().toString(), json));
    }

    @Override
    public AssignExamResponse assingExamnToStudent(AssignExamDTO assignExamnDTO) throws Exception {
        Optional<Students> student = studentsDAO.findById(assignExamnDTO.getStudentId());
        Optional<Exam> exam = examDAO.findById(assignExamnDTO.getExamnId());

        student.orElseThrow(() -> new Exception("Student not found with id " + assignExamnDTO.getStudentId()));
        exam.orElseThrow(() -> new Exception("Examn not found with id " + assignExamnDTO.getExamnId()));

        LocalDate localDate = LocalDate.now(ZoneId.of(student.get().getLocale()));

        student.get().setExamId(exam.get().getIdExamn());
        exam.get().setStudents(student.get().getIdStudents());

        examDAO.save(exam.get());
        studentsDAO.save(student.get());

        return new AssignExamResponse(student.get().getName(), student.get().getCity(), localDate);
    }

    @Override
    public CorrectQuestionsDTO qualifyExam(String examId) throws Exception {
        int correctQuestions = 0;

        Optional<Exam> exam = examDAO.findById(examId);
        exam.orElseThrow(() -> new Exception("Examn not found with id " + examId));

        Optional<Questions> question = questionsDAO.findById(exam.get().getQuestions());
        exam.orElseThrow(() -> new Exception("Questions not found with id " + question.get().getId()));

        Optional<Answers> answers = answersDAO.findByQuestionId(question.get().getId());
        exam.orElseThrow(() -> new Exception("Answers not found with id " + question.get().getId()));

        ObjectMapper f = new ObjectMapper();
        ExamDTO questions = f.readValue(question.get().getData(), ExamDTO.class);
        ExamDTO answer = f.readValue(answers.get().getData(), ExamDTO.class);

        int valor = questions.getExamn().size();
        int count = 0;

        for(QuestionNumberDTO number: questions.getExamn()) {
            if (count <= valor) {
                List<Map<String, String>> map = number.getListQuestion().get(0).getListAnswers().get(0).getAnswerOptions();
                Map<String, String> response = map.get(0);
                correctQuestions = correctQuestions + validateAnswers(response, answer.getExamn().get(count));
                count++;
            }
        }

        int valueAnswers = correctQuestions * answers.get().getValueAnswers();

        return new CorrectQuestionsDTO(valueAnswers);

    }

    public Integer validateAnswers(Map<String, String> answer,  QuestionNumberDTO answersDTO) {
        int correctQuestions = 0;
        List<Map<String, String>> map = answersDTO.getListQuestion().get(0).getListAnswers().get(0).getAnswerOptions();
        Map<String, String> response = map.get(0);
        if (answer.equals(response)) {
            correctQuestions++;
        }
        return correctQuestions;
    }
}
