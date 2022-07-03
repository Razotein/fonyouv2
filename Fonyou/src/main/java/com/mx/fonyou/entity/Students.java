package com.mx.fonyou.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "students")
public class Students implements Serializable {

    @Id
    @Column(name = "idStudents")
    private String idStudents;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "city")
    private String city;
    @Column(name = "locale")
    private String locale;
    @Column(name = "exam_id")
    private String examId;

    public Students(){}

    public Students(String idStudents, String name, Integer age, String city, String locale) {
        this.idStudents = idStudents;
        this.name = name;
        this.age = age;
        this.city = city;
        this.locale = locale;
    }
    public Students( String name, Integer age, String city, String locale) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.locale = locale;
    }

    public String getIdStudents() {
        return idStudents;
    }

    public void setIdStudents(String idStudents) {
        this.idStudents = idStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
