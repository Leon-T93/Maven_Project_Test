package org.example;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
    @Table(name="Contract")
    public class Contract{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Date LocalDate;

        private Integer trajanjeUgovoraUGodinama;

        private BigDecimal iznosPlace;

        @ManyToOne
        @JoinColumn(name = "company_id")
        private Company company;


        @ManyToMany(mappedBy = "contracts")
        private Set<Person> persons;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getLocalDate() {
            return LocalDate;
        }

        public void setDate(Date date) {
            LocalDate = date;
        }

        public Integer getTrajanjeUgovoraUGodinama() {
            return trajanjeUgovoraUGodinama;
        }

        public void setTrajanjeUgovoraUGodinama(Integer trajanjeUgovoraUGodinama) {
            this.trajanjeUgovoraUGodinama = trajanjeUgovoraUGodinama;
        }

        public BigDecimal getIznosPlace() {
            return iznosPlace;
        }

        public void setIznosPlace(BigDecimal iznosPlace) {
            this.iznosPlace = iznosPlace;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Set<Person> getPersons() {
            return persons;
        }

        public void setPersons(Set<Person> persons) {
            this.persons = persons;
        }

        public void addPerson(Person person) {
            this.persons.add(person);
        }
}
