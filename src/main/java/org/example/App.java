package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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

        updateProductInOrder(1L,4L,"Gaming TV", 10000.00);

        deleteOrder(1L);







    }


    public static void addOrderWithProducts(String customerName, List<Product> products) {
        EntityManager em = JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(customerName);


        for (Product product : products) {
            product.setCustomerorder(order);
            em.persist(product);
        }

        em.persist(order);


        em.getTransaction().commit();
        em.close();
    }


    public static void getAllOrdersWithProducts() {
        EntityManager em = JpaUtil.getEntityManagerFactory();

        String jpql = "SELECT c FROM CustomerOrder c";

        TypedQuery<CustomerOrder> query = em.createQuery(jpql, CustomerOrder.class);
        List<CustomerOrder> orders = query.getResultList();

        for (CustomerOrder order : orders) {
            System.out.println("Order ID: " + order.getId() + ", Customer: " + order.getCustomerName());

            for (Product product : order.getProducts()) {
                System.out.println("    Product: " + product.getName() +", ID: "+ product.getId() + ", Price: " + product.getPrice());
            }
        }

        em.close();
    }


    public static void updateProductInOrder(Long orderId, Long productId, String newName, Double newPrice) {
        EntityManager em = JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        CustomerOrder order = em.find(CustomerOrder.class, orderId);

        Product product = em.find(Product.class, productId);

        product.setName(newName);
        product.setPrice(newPrice);

        em.merge(product);

        em.getTransaction().commit();
        em.close();

    }

    public static void deleteOrder(Long orderId) {
        EntityManager em = JpaUtil.getEntityManagerFactory();
        em.getTransaction().begin();

        CustomerOrder order = em.find(CustomerOrder.class, orderId);

        em.remove(order);

        em.getTransaction().commit();
        em.close();
    }






}
