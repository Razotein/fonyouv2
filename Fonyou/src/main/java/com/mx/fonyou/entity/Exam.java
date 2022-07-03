package com.mx.fonyou.entity;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @Column(name = "idExam")
    private String idExamn;

    @Column(name = "data")
    private String data;

    @Column(name = "student_id")
    private String students;

    @Column(name = "question_id")
    private String questions;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public Exam() {}

    public Exam(String idExamn, String data) {
        this.idExamn = idExamn;
        this.data = data;
    }

    public Exam(String data) {
        this.data = data;
    }

    public String getIdExamn() {
        return idExamn;
    }

    public void setIdExamn(String idExamn) {
        this.idExamn = idExamn;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
