package com.mx.fonyou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @Column(name = "idQuestions")
    private String id;

    @Column(name = "answer_id")
    private String answer;

    private String data;

    public Questions() {
    }

    public Questions(String id, String answer, String data) {
        this.id = id;
        this.answer = answer;
        this.data = data;
    }

    public Questions(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
