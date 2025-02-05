package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");




    public static EntityManager getEntityManagerFactory() {
        EntityManager em = emf.createEntityManager();
        return em;
    }

}


