package org.example;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class App
{
    private static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main( String[] args )
    {

        createAuthor_Book_Publisher("Marko Maric", "Mrak po danu", "DC");
        createAuthor_Book_Publisher("Pero Peric", "Dan po noci", "Marvel");


    }



    public static void createAuthor_Book_Publisher(String authorName,String bookTitle,String publisherName) {
        Session session= sessionFactory.openSession();

        Author author= new Author();
        author.setName(authorName);


        Book book= new Book();
        book.setTitle(bookTitle);
        book.setAuthor(author);
        author.addBook(book);

        Publisher publisher= new Publisher();
        publisher.setName(publisherName);
        publisher.addAuthor(author);
        author.addPublisher(publisher);





        session.persist(author);
        session.persist(book);
        session.persist(publisher);
        session.close();
    }


    public static List dohvacanjeSvihAutoraIKnjiga () {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT FROM Author a LEFT OUTER JOIN Book b ON a.id=b.id");

        return query.list();
    }


    public static void azuriranjeKnjigePoIDu (Integer ID,String noviNaslov) {
        Session session= sessionFactory.openSession();

        Query query=session.createQuery("UPDATE Book b WHERE b.id=:idKnjigeZaUpdate SET b.title= :noviNaslov ");
        query.setParameter("idKnjigeZaUpdate",ID);
        query.setParameter("noviNaslov", noviNaslov);
        int queryResult= query.executeUpdate();

        session.close();
    }



    public static void brisanjeKnjigePoIDu (Integer ID) {
        Session session= sessionFactory.openSession();

        Query query=session.createQuery("DELETE FROM Book b WHERE b.id=:idKnjigeZaBrisanje");
        query.setParameter("idKnjigeZaBrisanje",ID);
        int queryResult= query.executeUpdate();


        session.close();
    }




}
