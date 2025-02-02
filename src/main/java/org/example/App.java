package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;


public class App
{
    private static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main( String[] args )
    {

        createProductOnCentralStorage(1,"Banana", BigDecimal.valueOf(1000));
        createProductOnStorageArena(1,"Banana", BigDecimal.valueOf(500));

        createProductOnCentralStorage(2,"Kiwi", BigDecimal.valueOf(1500));
        createProductOnStorageArena(2,"Kiwi", BigDecimal.valueOf(100));

        createProductOnCentralStorage(3,"Brokula", BigDecimal.valueOf(100));
        createProductOnStorageArena(3,"Brokula", BigDecimal.valueOf(2));





        Thread thread1 = new Thread(() -> {
            System.out.println("[Thread 1] Stanje prije: " + findCentralProductByProductID(3));
            System.out.println("[Thread 1] Stanje prije: " + findArenaProductByProductID(3));
            transferProductfromCentralToArena(3,BigDecimal.valueOf(20));
            try {
                Thread.sleep(5000); // Čekaj 5 sekundi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[Thread 1] Stanje nakon 5 sekundi: " + findCentralProductByProductID(3));
            System.out.println("[Thread 1] Stanje nakon 5 sekundi: " + findArenaProductByProductID(3));
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("[Thread 2] Stanje prije: " + findArenaProductByProductID(1));
            System.out.println("[Thread 2] Stanje prije: " + findCentralProductByProductID(1));
            transferProductfromArenaToCentralno(1,BigDecimal.valueOf(100));
            try {
                Thread.sleep(3000); // Čekaj 3 sekunde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[Thread 2] Stanje nakon 5 sekundi: " + findArenaProductByProductID(1));
            System.out.println("[Thread 2] Stanje nakon 5 sekundi: " + findCentralProductByProductID(1));
        });

        thread1.start();
        thread2.start();






    }


    public static void createProductOnCentralStorage(Integer proizvodID,String nazivProizvoda,BigDecimal kolicinaProizvodaNaStanju) {
        Session session= sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        CentralnoSkladiste centralnoSkladiste= new CentralnoSkladiste(proizvodID,nazivProizvoda,kolicinaProizvodaNaStanju);
        session.persist(centralnoSkladiste);
        tx.commit();
    }

    public static void createProductOnStorageArena(Integer proizvodID,String nazivProizvoda,BigDecimal kolicinaProizvodaNaStanju) {
        Session session= sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        SkladisteArena skladisteArena= new SkladisteArena(proizvodID,nazivProizvoda,kolicinaProizvodaNaStanju);
        session.persist(skladisteArena);
        tx.commit();
    }

    public static CentralnoSkladiste findCentralProductByProductID(Integer proizvodID) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CentralStorage WHERE proizvodID = ?";
            return session.createQuery(hql, CentralnoSkladiste.class)
                    .setParameter("1", proizvodID)
                    .uniqueResult();
        }
    }

    public static SkladisteArena findArenaProductByProductID(Integer proizvodID) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM StorageArena WHERE proizvodID = ?";
            return session.createQuery(hql, SkladisteArena.class)
                    .setParameter("1", proizvodID)
                    .uniqueResult();
        }
    }


    public static void transferProductfromCentralToArena(Integer proizvodID, BigDecimal kolicinaZaPrijenos) {

        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();


            CentralnoSkladiste fromSkladiste = findCentralProductByProductID(proizvodID);
            SkladisteArena toSkladiste = findArenaProductByProductID(proizvodID);

            if (fromSkladiste.getKolicinaProizvodaNaStanju().compareTo(kolicinaZaPrijenos) < 0) {
                System.out.println("Nedovoljno stanje na skladištu.");
                tx.rollback();
                return;
            }


            fromSkladiste.setKolicinaProizvodaNaStanju(fromSkladiste.getKolicinaProizvodaNaStanju().subtract(kolicinaZaPrijenos));
            toSkladiste.setKolicinaProizvodaNaStanju(toSkladiste.getKolicinaProizvodaNaStanju().add(kolicinaZaPrijenos));
            session.update(fromSkladiste);
            session.update(toSkladiste);


            tx.commit();
            System.out.println("Prijenos uspješan: " + kolicinaZaPrijenos + " prenesena sa Centralnog skladišta na skladište Arena.");
        }
    }

    public static void transferProductfromArenaToCentralno(Integer proizvodID, BigDecimal kolicinaZaPrijenos) {

        try (Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();


            SkladisteArena fromSkladiste = findArenaProductByProductID(proizvodID);
            CentralnoSkladiste toSkladiste = findCentralProductByProductID(proizvodID);

            if (fromSkladiste.getKolicinaProizvodaNaStanju().compareTo(kolicinaZaPrijenos) < 0) {
                System.out.println("Nedovoljno stanje na skladištu.");
                tx.rollback();
                return;
            }


            fromSkladiste.setKolicinaProizvodaNaStanju(fromSkladiste.getKolicinaProizvodaNaStanju().subtract(kolicinaZaPrijenos));
            toSkladiste.setKolicinaProizvodaNaStanju(toSkladiste.getKolicinaProizvodaNaStanju().add(kolicinaZaPrijenos));
            session.update(fromSkladiste);
            session.update(toSkladiste);


            tx.commit();
            System.out.println("Prijenos uspješan: " + kolicinaZaPrijenos + " prenesena sa skladišta Arena na Centralno skladište.");
        }
    }




}
