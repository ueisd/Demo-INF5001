package com.sirra.demo.model;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Document {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Individu", referencedColumnName = "id")
    private Individu individu;
    @Column(name = "noReference")
    @Length(min=3, message = "Veillez entrer un numero de réference vailde")
    private String noReference;
    @Column(name = "nom_Document")
    @Length(min=3,max=20,message = "Nom du document trop court ou trop long")
    private String nomDocument;
    @Column(name = "repertoire")
    @Length(min=2,message = "Nom du repertoire trop court")
    private String repertoire;
    @Column(name = "pays_Emetteur")
    @Length(min=3,message = "Nom du pays emetteur trop court")
    private String paysEmetteur;
    @Column(name = "organisme_Emetteur")
    @Length(min=2,message = "Organisme emetteur trop court")
    private String organismeEmetteur;
    @Column(name = "format_Document")
    private String formatDocument;
    @Column(name = "fin_Validite")
    private Date finValidite;
    @Column(name = "debut_Validite")
    private Date debutValidite;
    @Column(name ="creer_Le")
    private Date creerLe;
    @Column(name = "moification_Par")
    private String modificationPar;
    @Column(name = "modifier_Le")
    private Date modifierLe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public String getNoReference() {
        return noReference;
    }

    public void setNoReference(String noReference) {
        this.noReference = noReference;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public String getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }

    public String getPaysEmetteur() {
        return paysEmetteur;
    }

    public void setPaysEmetteur(String paysEmetteur) {
        this.paysEmetteur = paysEmetteur;
    }

    public String getOrganismeEmetteur() {
        return organismeEmetteur;
    }

    public void setOrganismeEmetteur(String organismeEmetteur) {
        this.organismeEmetteur = organismeEmetteur;
    }

    public String getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(String formatDocument) {
        this.formatDocument = formatDocument;
    }

    public Date getFinValidite() {
        return finValidite;
    }

    public void setFinValidite(Date finValidite) {
        this.finValidite = finValidite;
    }

    public Date getDebutValidite() {
        return debutValidite;
    }

    public void setDebutValidite(Date debutValidite) {
        this.debutValidite = debutValidite;
    }
    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", individu=" + individu +
                ", noReference='" + noReference + '\'' +
                ", nomDocument='" + nomDocument + '\'' +
                ", repertoire='" + repertoire + '\'' +
                ", paysEmetteur='" + paysEmetteur + '\'' +
                ", organismeEmetteur='" + organismeEmetteur + '\'' +
                ", formatDocument='" + formatDocument + '\'' +
                ", finValidite=" + finValidite +
                ", debutValidite='" + debutValidite + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }
}
