package org.example;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "CentralStorage")
public class CentralnoSkladiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer proizvodID;

    @Column(nullable = false)
    private String nazivProizvoda;

    @Column
    private BigDecimal kolicinaProizvodaNaStanju;

    public CentralnoSkladiste(Integer proizvodID, String nazivProizvoda, BigDecimal kolicinaProizvodaNaStanju) {
        this.proizvodID = proizvodID;
        this.nazivProizvoda = nazivProizvoda;
        this.kolicinaProizvodaNaStanju = kolicinaProizvodaNaStanju;
    }

    public CentralnoSkladiste() {
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

    public BigDecimal getKolicinaProizvodaNaStanju() {
        return kolicinaProizvodaNaStanju;
    }

    public void setKolicinaProizvodaNaStanju(BigDecimal kolicinaProizvodaNaStanju) {
        this.kolicinaProizvodaNaStanju = kolicinaProizvodaNaStanju;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CentralnoSkladiste{" +
                "proizvodID=" + proizvodID +
                ", nazivProizvoda='" + nazivProizvoda + '\'' +
                ", kolicinaProizvodaNaStanju=" + kolicinaProizvodaNaStanju +
                '}';
    }
}
