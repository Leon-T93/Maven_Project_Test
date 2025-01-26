package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdPredmeta;

    private String nazivPredmeta;

    private String opisPredmeta;

    private String profesor;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students;

    @OneToOne(mappedBy = "aclass")
    private Grade grade;

    public Class() {
    }

    public Class(String nazivPredmeta, String opisPredmeta, String profesor) {
        this.nazivPredmeta = nazivPredmeta;
        this.opisPredmeta = opisPredmeta;
        this.profesor = profesor;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getOpisPredmeta() {
        return opisPredmeta;
    }

    public void setOpisPredmeta(String opisPredmeta) {
        this.opisPredmeta = opisPredmeta;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
