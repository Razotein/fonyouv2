package com.mx.fonyou.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.fonyou.dao.IAnswersDAO;
import com.mx.fonyou.dao.IExamDAO;
import com.mx.fonyou.dao.IQuestionsDAO;
import com.mx.fonyou.dto.ExamDTO;
import com.mx.fonyou.entity.Answers;
import com.mx.fonyou.entity.Exam;
import com.mx.fonyou.entity.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private IExamDAO examDAO;

    @Autowired
    private IQuestionsDAO questionsDAO;

    @Autowired
    private IAnswersDAO answersDAO;

    @Override
    public Answers saveAnswers(ExamDTO examDTO, String examId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(examDTO);

        Optional<Exam> exam = examDAO.findById(examId);
        exam.orElseThrow(() -> new Exception("Examn not found with id " + examId));

        Optional<Questions> question = questionsDAO.findById(exam.get().getQuestions());
        exam.orElseThrow(() -> new Exception("Questions not found with id " + question.get().getId()));

        Answers answers = answersDAO.save(new Answers(
                UUID.randomUUID().toString(),
                question.get().getId(),
                json,
                examDTO.getValueAnswer()
        ));

        question.get().setAnswer(answers.getId());
        questionsDAO.saveAndFlush(question.get());

        return answers;
    }


}
