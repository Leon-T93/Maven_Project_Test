package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;


public class App
{

    public static void main( String[] args )
    {

        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Gaming PC", 50000.00);
        Product product2 = new Product("Gaming Monitor", 5000.00);

        products.add(product1);
        products.add(product2);

        addOrderWithProducts("Marko Markic", products);


        getAllOrdersWithProducts();

        updateProductInOrder(2L,6L,"Gaming TV", 10000.00);

        getAllOrdersWithProducts();

        deleteOrder(2L);

        getAllOrdersWithProducts();






    }


    public static void addOrderWithProducts(String customerName, List<Product> products) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            CustomerOrder order = new CustomerOrder();
            order.setCustomerName(customerName);
            session.persist(order);

            for (Product product : products) {
                product.setCustomerorder(order);
                session.persist(product);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }





    public static void getAllOrdersWithProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM CustomerOrder";
            Query<CustomerOrder> query = session.createQuery(hql, CustomerOrder.class);

            List<CustomerOrder> orders = query.list();

            for (CustomerOrder order : orders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Customer: " + order.getCustomerName());
                System.out.println("Products:");

                for (Product product : order.getProducts()) {
                    System.out.println("-Name " + product.getName() + " -Price " + product.getPrice()+" -ID " + product.getId());
                }
            }
        } finally {
            session.close();
        }
    }



    public static void updateProductInOrder(Long orderId, Long productId, String newName, Double newPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "FROM Product p WHERE p.customerorder.id = :orderId AND p.id = :productId";
            Product productToUpdate = session.createQuery(hql, Product.class)
                    .setParameter("orderId", orderId)
                    .setParameter("productId", productId)
                    .uniqueResult();


            productToUpdate.setName(newName);
            productToUpdate.setPrice(newPrice);
            session.merge(productToUpdate);


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    public static void deleteOrder(Long orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            CustomerOrder order = session.get(CustomerOrder.class, orderId);


            session.remove(order);


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }






}
