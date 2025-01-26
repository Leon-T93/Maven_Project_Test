package org.example;

import jakarta.persistence.*;
import org.hibernate.mapping.Set;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class App
{
    public static void main( String[] args )
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em = emf.createEntityManager();



        em.getTransaction().begin();


        Class class1=new Class();
        class1.setNazivPredmeta("matematika");
        class1.setOpisPredmeta("napradna matematika");
        class1.setProfesor("prof. Juric");

        Class class2=new Class();
        class2.setNazivPredmeta("fizika");
        class2.setOpisPredmeta("napradna fizika");
        class2.setProfesor("prof. Peric");

        Class class3=new Class();
        class3.setNazivPredmeta("informatika");
        class3.setOpisPredmeta("napradna informatika");
        class3.setProfesor("prof. Bilic");

        Class class4=new Class();
        class4.setNazivPredmeta("biologija");
        class4.setOpisPredmeta("napradna biologija");
        class4.setProfesor("prof. Filic");



        Student student1 = new Student();
        student1.setIme("Marko");
        student1.setPrezime("Maric");

        Student student2 = new Student();
        student2.setIme("Ana");
        student2.setPrezime("Anic");

        Student student3 = new Student();
        student3.setIme("Petar");
        student3.setPrezime("Peric");



        Grade grade1= new Grade();
        grade1.setOcjena(1);
        grade1.setDatumOcjenjivanja(20010901);

        Grade grade2= new Grade();
        grade2.setOcjena(2);
        grade2.setDatumOcjenjivanja(20010901);

        Grade grade3= new Grade();
        grade3.setOcjena(3);
        grade3.setDatumOcjenjivanja(20010901);

        Grade grade4= new Grade();
        grade4.setOcjena(4);
        grade4.setDatumOcjenjivanja(20010901);

        Grade grade5= new Grade();
        grade5.setOcjena(5);
        grade5.setDatumOcjenjivanja(20010901);


        List<Grade> gradesMarko= new ArrayList<>();
        gradesMarko.add(grade5);
        gradesMarko.add(grade4);
        gradesMarko.add(grade3);
        List<Class> classesMarko= new ArrayList<>();
        classesMarko.add(class4);
        classesMarko.add(class3);
        classesMarko.add(class2);


        List<Grade> gradesAna= new ArrayList<>();
        gradesAna.add(grade5);
        gradesAna.add(grade5);
        gradesAna.add(grade5);
        List<Class> classesAna= new ArrayList<>();
        classesAna.add(class1);
        classesAna.add(class2);
        classesAna.add(class3);


        List<Grade> gradesPetar= new ArrayList<>();
        gradesPetar.add(grade3);
        gradesPetar.add(grade3);
        gradesPetar.add(grade3);
        List<Class> classesPetar= new ArrayList<>();
        classesPetar.add(class4);
        classesPetar.add(class2);
        classesPetar.add(class3);


        student1.setGrades(gradesMarko);
        student1.setClasses(classesMarko);

        student2.setGrades(gradesAna);
        student2.setClasses(classesAna);

        student3.setGrades(gradesPetar);
        student3.setClasses(classesMarko);


        em.getTransaction().commit();



        TypedQuery<Student> query1 = em.createQuery("SELECT * FROM Student AS s LEFT OUTER JOIN Class AS c ON s.Id WHERE c.nazivPredmeta = 'informatika'  ", Student.class);
        for (Student s : query1.getResultList()) {
            System.out.println("Ucenik: " + s.getIme()+ " " + s.getPrezime());
        }

        TypedQuery<Grade> query2 = em.createQuery("SELECT * FROM Grade AS g LEFT OUTER JOIN Student AS s ON g.Id WHERE s.Ime='Marko'  ", Grade.class);
        for (Grade g : query2.getResultList()) {
            System.out.println("Ocjena: " + g.getOcjena());
        }






        em.close();
        emf.close();
    }
}
