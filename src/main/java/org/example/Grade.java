package org.example;

import jakarta.persistence.*;

public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private Integer ocjena;

    private Integer datumOcjenjivanja;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "classID")
    private Class aclass;

    public Grade() {
    }

    public Grade(Integer ocjena, Integer datumOcjenjivanja) {
        this.ocjena = ocjena;
        this.datumOcjenjivanja = datumOcjenjivanja;

    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    public Integer getDatumOcjenjivanja() {
        return datumOcjenjivanja;
    }

    public void setDatumOcjenjivanja(Integer datumOcjenjivanja) {
        this.datumOcjenjivanja = datumOcjenjivanja;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class getAclass() {
        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }
}
