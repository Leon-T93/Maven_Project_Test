package org.example;

import jakarta.persistence.*;

@Entity
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UpisID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDPolaznik", referencedColumnName = "PolaznikID")
    private Polaznik polaznik;

    @ManyToOne
    @JoinColumn(name = "IDProgramObrazovanja", referencedColumnName = "ProgramObrazovanjaID")
    private ProgramObrazovanja programObrazovanja;

    public Upis() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public ProgramObrazovanja getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(ProgramObrazovanja programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }
}
