package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;


public class App
{
    private static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main( String[] args )
    {


        createAuthor_Book_Publisher("Marko Maric", "Mrak po danu", "DC");
        createAuthor_Book_Publisher("Pero Peric", "Dan po noci", "Marvel");


        dohvacanjeSvihAutoraIKnjiga();

        azuriranjeKnjigePoIDu(1L,"Mrak po danu");

        dohvacanjeSvihAutoraIKnjiga();

        brisanjeKnjigePoIDu(2L);

        dohvacanjeSvihAutoraIKnjiga();



    }



    public static void createAuthor_Book_Publisher(String authorName,String bookTitle,String publisherName) {
        EntityManager em= JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        Author author= new Author();
        author.setName(authorName);



        Book book= new Book();
        book.setTitle(bookTitle);
        book.setAuthor(author);
        author.addBook(book);


        Publisher publisher= new Publisher();
        publisher.setName(publisherName);
        publisher.addBook(book);
        book.addPublisher(publisher);



        em.persist(author);
        em.persist(book);
        em.persist(publisher);


        em.getTransaction().commit();
        em.close();
    }


    public static List<Author> dohvacanjeSvihAutoraIKnjiga () {
        EntityManager em= JpaUtil.getEntityManagerFactory();

        String jpql="SELECT a FROM Author a LEFT OUTER JOIN FETCH a.books ";
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        List<Author> authors = query.getResultList();
        em.close();

        for (Author author : authors) {
            System.out.println("Id: " + author.getId());
            System.out.println("Author: " + author.getName());
            if (author.getBooks() != null && !author.getBooks().isEmpty()) {
                for (Book book : author.getBooks()) {
                    System.out.println("    Id: " + book.getId());
                    System.out.println("    Book: " + book.getTitle());
                }
            } else {
                System.out.println("    Ovaj autor nema knjiga.");
            }
        }
        return authors;
    }


    public static void azuriranjeKnjigePoIDu (Long ID,String noviNaslov) {
        EntityManager em= JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        String jpql = "UPDATE Book b SET b.title= :noviNaslov WHERE b.id= :id";
        Query query= em.createQuery(jpql);
        query.setParameter("noviNaslov",noviNaslov);
        query.setParameter("id",ID);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }



    public static void brisanjeKnjigePoIDu (Long ID) {
        EntityManager em= JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        String jpql = "DELETE FROM Book b WHERE b.id = :id";
        Query query=em.createQuery(jpql);
        query.setParameter("id",ID);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }




}
