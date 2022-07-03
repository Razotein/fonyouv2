package com.mx.fonyou.dao;

import com.mx.fonyou.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamDAO extends JpaRepository<Exam, String> {
}
