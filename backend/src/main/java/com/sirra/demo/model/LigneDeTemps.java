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

    @Column(columnDefinition="TIMESTAMP")
    private ZonedDateTime dateEntre;
    @Column(columnDefinition="TIMESTAMP")
    private ZonedDateTime dateSortie;

    private String strDateEntre;
    private String strDateSortie;


    public String getStrDateEntre() {
        return strDateEntre;
    }

    public void setStrDateEntre(String strDateEntre) {
        this.strDateEntre = strDateEntre;
    }

    public String getStrDateSortie() {
        return strDateSortie;
    }

    public void setStrDateSortie(String strDateSortie) {
        this.strDateSortie = strDateSortie;
    }

    private int statut;

    public LigneDeTemps() { }

    public LigneDeTemps(Employe employe, ZonedDateTime dateEntre, ZonedDateTime dateSortie) {
        this.employe = employe;
        this.dateEntre = dateEntre;
        this.dateSortie = dateSortie;
    }

    public int getId() {
        return id;
    }

    // pour l'interface graphique
    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
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

    public void metreAjourDates() {
        if(this.getStrDateEntre() != null && this.getStrDateSortie() != null){
            this.setDateEntre(ZonedDateTime.parse(this.getStrDateEntre()));
            this.setDateSortie(ZonedDateTime.parse(this.getStrDateSortie()));
        }
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
