package com.mx.fonyou.dao;

import com.mx.fonyou.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentsDAO extends JpaRepository<Students, String> {
    Optional<Students> findByExamId(String id);
}
