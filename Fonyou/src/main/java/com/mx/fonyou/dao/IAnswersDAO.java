package com.mx.fonyou.dao;

import com.mx.fonyou.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAnswersDAO extends JpaRepository<Answers, String> {

    @Query("select a from Answers a where a.question = :questionId")
    Optional<Answers> findByQuestionId(@Param("questionId") String questionId);
}
