package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class LigneDeTemps {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @JsonIgnoreProperties(value = "ligneDeTemps", allowSetters = true)
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "employe_id")
    private Employe employe;

    private ZonedDateTime dateEntre;
    private ZonedDateTime dateSortie;

    public LigneDeTemps() { }

    public LigneDeTemps(Employe employe, ZonedDateTime dateEntre, ZonedDateTime dateSortie) {
        this.employe = employe;
        this.dateEntre = dateEntre;
        this.dateSortie = dateSortie;
    }

    public int getId() {
        return id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public ZonedDateTime getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(ZonedDateTime dateEntre) {
        this.dateEntre = dateEntre;
    }

    public ZonedDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(ZonedDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public long calculerDureeEnMinutes() {
        return ChronoUnit.MINUTES.between(this.dateEntre, this.dateSortie);
    }

    public void retirerMinutesFin(int minutes) {
        this.dateSortie = ChronoUnit.MINUTES.addTo(this.dateSortie, -minutes);
    }

    public void decalerADroiteDeSecondes(int secondes) {
        this.dateEntre = ChronoUnit.SECONDS.addTo(this.dateEntre, secondes);
        this.dateSortie = ChronoUnit.SECONDS.addTo(this.dateSortie, secondes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LigneDeTemps)) return false;
        LigneDeTemps that = (LigneDeTemps) o;
        return Objects.equals(getDateEntre(), that.getDateEntre()) &&
                Objects.equals(getDateSortie(), that.getDateSortie()) &&
                Objects.equals(getEmploye().getId(), that.getEmploye().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateEntre(), getDateSortie());
    }
}
