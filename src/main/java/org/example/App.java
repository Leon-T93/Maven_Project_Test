package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.List;


public class App
{
    public static void main( String[] args )
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em = emf.createEntityManager();



        em.getTransaction().begin();


        Contract contract= new Contract();


        Person person1 = new Person();
        person1.setName("Marko Maric");
        contract.addPerson(person1);
        Person person2 = new Person();
        person2.setName("Filip Filic");
        contract.addPerson(person2);
        Person person3 = new Person();
        person3.setName("Ana Anic");
        contract.addPerson(person3);
        Person person4 = new Person();
        person4.setName("Petra Petric");
        contract.addPerson(person4);

        Company company1= new Company();

        company1.setName("Tokic");


        contract.setCompany(company1);

        person3.setName("Ana Maric");

        em.merge(person3);

        em.remove(person2);



        em.getTransaction().commit();
    }
}
