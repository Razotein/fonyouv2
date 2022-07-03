package com.mx.fonyou.dao;

import com.mx.fonyou.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IQuestionsDAO extends JpaRepository<Questions, String> {
    Optional<Questions> findByAnswer(String id);
}
