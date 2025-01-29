package org.example;


import jakarta.persistence.*;

@Entity
@Table(name = "StorageArena")
public class SkladisteArena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer proizvodID;

    @Column(nullable = false)
    private String nazivProizvoda;

    @Column
    private Integer kolicinaProizvodaNaStanju;


    public SkladisteArena(Integer proizvodID, String nazivProizvoda, Integer kolicinaProizvodaNaStanju) {
        this.proizvodID = proizvodID;
        this.nazivProizvoda = nazivProizvoda;
        this.kolicinaProizvodaNaStanju = kolicinaProizvodaNaStanju;
    }

    public SkladisteArena() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(Integer proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public Integer getKolicinaProizvodaNaStanju() {
        return kolicinaProizvodaNaStanju;
    }

    public void setKolicinaProizvodaNaStanju(Integer kolicinaProizvodaNaStanju) {
        this.kolicinaProizvodaNaStanju = kolicinaProizvodaNaStanju;
    }
}
