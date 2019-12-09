package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class Departement {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @JsonIgnoreProperties(value = {"departement"}, allowSetters = true)
    @OneToMany(
            mappedBy = "departement",
            fetch = FetchType.EAGER
    )
    private List<Employe> employes;


    private int heure_Ouverture;


    private int heure_Fermeture;

    @Length(min = 7, max = 7, message = "Binaire ne fait pas longueur 7")
    private String jrBinaire;
    //1-7 = Dimanche a Samedi
    private boolean[] journesOuvert = {false, false, false, false, false, false, false};

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public ArrayList<Integer> getDepartementsIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < this.employes.size(); i++) {
            Employe employe = this.employes.get(i);
            ids.add(employe.getId());
        }
        return ids;
    }

    public boolean[] getJournesOuvert() {
        return journesOuvert;
    }

    public void setJournesOuvert(boolean[] journesOuvert) {
        this.journesOuvert = journesOuvert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void initiliaserLesJr() {
        String binaire7 = getJrBinaire();
        if (binaire7.trim().length() == 7) {
            for (int i = 0; i < binaire7.length() - 1; i++) {
                if (binaire7.charAt(i) == '0') {
                    journesOuvert[i] = false;
                } else if (binaire7.charAt(i) == '1') {
                    journesOuvert[i] = true;
                } else {
                    System.out.println("Votre binaire7 contient un charactere different de 0 ou 1");
                    System.exit(1);
                }
            }

        } else {
            System.out.println("Le binaire7 n'est pas de 7");
            System.exit(1);
        }

    }

    public int getHeure_Ouverture() {
        return heure_Ouverture;
    }

    public void setHeure_Ouverture(int heure_Ouverture) {
        this.heure_Ouverture = heure_Ouverture;
    }

    public int getHeure_Fermeture() {
        return heure_Fermeture;
    }

    public void setHeure_Fermeture(int heure_Fermeture) {
        this.heure_Fermeture = heure_Fermeture;
    }

    public String getJrBinaire() {
        return jrBinaire;
    }

    public void setJrBinaire(String jrBinaire) {
        this.jrBinaire = jrBinaire;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", employes=" + employes +
                ", heure_Ouverture=" + heure_Ouverture +
                ", heure_Fermeture=" + heure_Fermeture +
                ", jrBinaire='" + jrBinaire + '\'' +
                ", journesOuvert=" + Arrays.toString(journesOuvert) +
                '}';
    }
}


