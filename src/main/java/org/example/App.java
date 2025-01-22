package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class App
{
    public static void main( String[] args )
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em = emf.createEntityManager();



        em.getTransaction().begin();



        Osoba osoba1 = em.find(Osoba.class,1);

        if (osoba1 != null) {
            System.out.println("Ime: " + osoba1.getIme());
            System.out.println("Prezime: " + osoba1.getPrezime());
            em.remove(osoba1);
        }else{
            System.out.println("Osoba ne postoji.");
        }



        em.getTransaction().commit();
    }
}
