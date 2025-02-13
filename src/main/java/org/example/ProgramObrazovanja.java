package org.example;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramObrazovanjaID")
    private Integer id;

    @Column(name = "Naziv")
    private String naziv;

    @Column(name = "CSVET")
    private Integer csvet;

    @OneToMany(mappedBy = "programObrazovanja")
    private List<Upis> upisi;



    public ProgramObrazovanja() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getCsvet() {
        return csvet;
    }

    public void setCsvet(Integer csvet) {
        this.csvet = csvet;
    }


}
