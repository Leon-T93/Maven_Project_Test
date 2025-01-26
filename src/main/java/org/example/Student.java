package org.example;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdStudenta;

    private String ime;

    private String prezime;

    @ManyToMany
    @JoinTable(
            name= "student_class",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<Class> classes;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    public Student(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public Student() {

    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
