package org.example;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.math.BigDecimal;


public class App
{
    private static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main( String[] args )
    {




    }



    public static void create2Authors(String name) {
        Session session= sessionFactory.openSession();

        Author author1= new Author();
        author1.setName("Marko Maric");
        Author author2 = new Author();
        author2.setName("Pero Peric");

        session.persist(author1);
        session.persist(author2);
        session.close();
    }







}
