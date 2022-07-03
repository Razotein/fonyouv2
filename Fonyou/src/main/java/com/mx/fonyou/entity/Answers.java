package com.mx.fonyou.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answers {

    @Id
    @Column(name = "idAnswers")
    private String id;

    @Column(name = "questions_id")
    private String question;

    private String data;

    @Column(name = "value_answers")
    private Integer valueAnswers;

    public Answers(String id, String question, String data, Integer valueAnswers) {
        this.id = id;
        this.question = question;
        this.data = data;
        this.valueAnswers = valueAnswers;
    }

    public Integer getValueAnswers() {
        return valueAnswers;
    }

    public void setValueAnswers(Integer valueAnswers) {
        this.valueAnswers = valueAnswers;
    }

    public Answers(String id, String question, String data) {
        this.id = id;
        this.question = question;
        this.data = data;
    }

    public Answers() {}


    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
