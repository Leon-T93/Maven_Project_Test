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

        createProductOnCentralStorage(1,"Banana",1000);
        createProductOnStorageArena(1,"Banana",500);

        createProductOnCentralStorage(2,"Kiwi", 1500);
        createProductOnStorageArena(2,"Kiwi", 100);

        createProductOnCentralStorage(3,"Brokula", 100);
        createProductOnStorageArena(3,"Brokula", 2);






    }


    public static void createProductOnCentralStorage(Integer proizvodID,String nazivProizvoda,Integer kolicinaProizvodaNaStanju) {
        Session session= sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        CentralnoSkladiste centralnoSkladiste= new CentralnoSkladiste(proizvodID,nazivProizvoda,kolicinaProizvodaNaStanju);
        session.persist(centralnoSkladiste);
        tx.commit();
    }

    public static void createProductOnStorageArena(Integer proizvodID,String nazivProizvoda,Integer kolicinaProizvodaNaStanju) {
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


    public void transferProduct(Integer proizvodID, Integer kolicinaZaPrijenos) {
        CentralnoSkladiste centralno = null;
        SkladisteArena arena;

        centralno.setId(proizvodID);
        Integer stanjeProizvodaIzCentralong= centralno.getKolicinaProizvodaNaStanju();
    }




}
