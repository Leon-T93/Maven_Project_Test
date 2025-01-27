package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;


public class App
{
    private static SessionFactory sessionFactory;

    public static void main( String[] args )
    {

        sessionFactory = new Configuration().configure().buildSessionFactory();



    }

    public static Product addProduct(String name, BigDecimal price) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Product product= new Product();
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(2000));

        try {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return product;
    }

    // upitno jel to to
    public static void selectProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Product managedProduct = session.find(Product.class, product.getId());
            System.out.println("Product is: " + managedProduct);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteUser(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Product managedProduct = session.find(Product.class, product.getId());
            session.remove(managedProduct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static Product updateProduct(String name, BigDecimal price) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return product;
    }


}
