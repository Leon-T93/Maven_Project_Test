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
import java.util.Scanner;


public class App
{

    public static void main( String[] args )
    {

        Scanner scanner= new Scanner(System.in);

        textZaIzbor();

        int odabir;
        odabir= scanner.nextInt();

        while (odabir < 6) {
            switch (odabir) {
                case 1:
                    unosNovogPolaznika();

                    break;

                case 2:
                    unosNovogProgramaObrazovanja();

                    break;

                case 3:
                    upisPolaznikaNaProgramObrazovanja();

                    break;

                case 4:
                    prebacivanjePolaznikaIzProgramaUProgram();

                    break;

                case 5:
                    ispisInformacijaOPolaznicimaUOdredenomProgramu();

                    break;


            }

            textZaIzbor();
            odabir = scanner.nextInt();

        }








    }

    private static void textZaIzbor() {
        System.out.println("\n" +"Odaberite jednu od sljedečih opcija: " + "\n" +
                "(1) za unos novog polaznika" + "\n" +
                "(2) za unos novog programa obrazovanja" + "\n" +
                "(3) za upis polaznika na program obrazovanja" + "\n" +
                "(4) za prebacivanje polaznika iz jednog u drugi program obrazovanja" + "\n" +
                "(5) za ispis informacija za određeni program obrazovanja" + "\n" +
                "(6) za izlaz");
    }

    public static void unosNovogPolaznika() {
        Scanner scanner = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Unesite ime novog polaznika: ");
            String ime = scanner.next();
            System.out.println("Unesite prezime novog polaznika: ");
            String prezime = scanner.next();

            Polaznik polaznik = new Polaznik();
            polaznik.setIme(ime);
            polaznik.setPrezime(prezime);

            session.persist(polaznik);

            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private static void unosNovogProgramaObrazovanja() {
        Scanner scanner = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Unesite naziv novog programa: ");
            String naziv = scanner.next();
            System.out.println("Unesite CSVET broj: ");
            int csvet = scanner.nextInt();

            ProgramObrazovanja programObrazovanja= new ProgramObrazovanja();
            programObrazovanja.setNaziv(naziv);
            programObrazovanja.setCsvet(csvet);

            session.persist(programObrazovanja);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void upisPolaznikaNaProgramObrazovanja() {
        Scanner scanner = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ispisSvihPolaznika();
            System.out.println("Odaberite ID polaznika kojeg želite upisati: ");
            int idPolaznika= scanner.nextInt();

            ispisSvihProgramaObrazovanja();
            System.out.println("Odaberite ID programa u koji želite upisati polaznika: ");
            int idProgramaObrazovanja= scanner.nextInt();

            Polaznik polaznik = session.get(Polaznik.class,idPolaznika);
            ProgramObrazovanja programObrazovanja= session.get(ProgramObrazovanja.class,idProgramaObrazovanja);

            Upis upis = new Upis();
            upis.setPolaznik(polaznik);
            upis.setProgramObrazovanja(programObrazovanja);

            session.persist(upis);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private static void prebacivanjePolaznikaIzProgramaUProgram () {
        Scanner scanner = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ispisSvihPolaznika();
            System.out.println("Unesite ID polaznika kojem želite promjeniti program: ");
            int idPolaznika= scanner.nextInt();

            ispisSvihProgramaObrazovanja();
            System.out.println("Unesite ID programa iz kojeg želite ISPISATI polaznika: ");
            int idProgramaZaIspis= scanner.nextInt();

            ispisSvihProgramaObrazovanja();
            System.out.println("Unesite ID programa u koji želite UPISATI polaznika: ");
            int idProgramaZaUpis= scanner.nextInt();

            Polaznik polaznik = session.get(Polaznik.class, idPolaznika);
            ProgramObrazovanja programZaIspis = session.get(ProgramObrazovanja.class, idProgramaZaIspis);
            ProgramObrazovanja programZaUpis = session.get(ProgramObrazovanja.class, idProgramaZaUpis);


            String hql = "FROM Upis WHERE polaznik = :polaznik AND programObrazovanja = :programZaIspis";
            Upis upis = session.createQuery(hql, Upis.class)
                    .setParameter("polaznik", polaznik)
                    .setParameter("programZaIspis", programZaIspis)
                    .uniqueResult();

            upis.setProgramObrazovanja(programZaUpis);
            session.persist(upis);

            transaction.commit();


        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void ispisInformacijaOPolaznicimaUOdredenomProgramu() {
        Scanner scanner = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            ispisSvihProgramaObrazovanja();
            System.out.println("Unesite ID od programa za koji želite ispis informacija: ");
            int idProgramaObrazovanja= scanner.nextInt();

            String hql = "SELECT p.Ime+' '+p.Prezime AS Polaznik, po.Naziv AS ProgramObrazovanja, po.CSVET AS CSVET FROM Upis AS u LEFT JOIN Polaznik AS p ON p.PolaznikID= u.IDPolaznik LEFT JOIN ProgramObrazovanja AS po ON po.ProgramObrazovanjaID = u.IDProgramObrazovanja WHERE u.IDProgramObrazovanja = :idProgramaObrazovanja";


        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }



















    private static void ispisSvihPolaznika() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM Polaznik";
            Query<Polaznik> query = session.createQuery(hql, Polaznik.class);

            List<Polaznik> polaznici = query.getResultList();

            System.out.println("POLAZNICI SU: ");
            for (Polaznik polaznik : polaznici) {
                System.out.println("ID: " + polaznik.getId() + " ---- Polaznik: " + polaznik.getIme() + " " + polaznik.getPrezime());
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private static void ispisSvihProgramaObrazovanja() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM ProgramObrazovanja";
            Query<ProgramObrazovanja> query = session.createQuery(hql, ProgramObrazovanja.class);

            List<ProgramObrazovanja> programi = query.getResultList();

            System.out.println("PROGRAMI SU: ");
            for (ProgramObrazovanja program : programi) {
                System.out.println("ID: " + program.getId() + " ----  Naziv: " + program.getNaziv() + " ----  CSVET: " + program.getCsvet());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
