package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Individu {

    public Individu() {

    }


    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @JsonBackReference(value="employe")
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade=CascadeType.ALL,
            optional = true
    )
    @JoinColumn(name = "employe_id", nullable = true)
    @ApiModelProperty(notes = "Property linking the userprofile with the user")
    private Employe employe;

    @JsonManagedReference(value = "individu")
    @OneToMany(
            mappedBy = "individu",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Contact> contact;

   @JsonBackReference(value = "note")
   @OneToMany(
           mappedBy = "individu",
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
   @ApiModelProperty(notes = "Property containing the notes of the individu")
   private List<Note> note;

    @JsonBackReference(value = "diplomes")
    @OneToMany(
            mappedBy = "individu",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ApiModelProperty(notes = "Property containing the diplomes of the individu")
    private List<Diplome> diplomes;


    @Column(name = "prenom")
    @Length(min=3, max=20, message="Nom trop long ou trop court")
    private String prenom;


    @Column(name = "nom")
    private String nom;

    @Column (name = "ville")
    @Length(min=5,max=30,message = "Veuillez verifier le nombre de charactere")
    private String ville;


    @Column (name="telProf")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telProfessionel;

    @Column (name="telPerso")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telPerso;

    @Column (name = "statut_Civil")
    private String statutCivil;

    @Column (name = "nom_Banque")
    private String succursalBanque;

    @Column (name = "sexe")
    private String sexe;

    @Column (name = "province")
    private String province;

    @Column (name = "numero_ass_social")
    private String nas;

    @Column (name = "language")
    private String languageCommunication;

    @Column (name = "handicap")
    private String handicap;// aucun ou description

    @Temporal(TemporalType.DATE)
    @Column (name = "date_de_naissance")
    private Date dateNaissance;

    @Column (name = "code_Postal")
    private String codePostal;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String creationPar;

    @Temporal(TemporalType.DATE)
    private Date creerLe;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String modificationPar;

    @Temporal(TemporalType.DATE)
    private Date modifierLe;

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public int getIndividuId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @ApiModelProperty(example = "1 450 999 9999")
    public String getTelProfessionel() {
        return telProfessionel;
    }

    public void setTelProfessionel(String telProfessionel) {
        this.telProfessionel = telProfessionel;
    }

    @ApiModelProperty(example = "1 450 999 9999")
    public String getTelPerso() {
        return telPerso;
    }

    public void setTelPerso(String telPerso) {
        this.telPerso = telPerso;
    }

    public String getStatutCivil() {
        return statutCivil;
    }

    public void setStatutCivil(String statutCivil) {
        this.statutCivil = statutCivil;
    }

    public String getSuccursalBanque() {
        return succursalBanque;
    }

    public void setSuccursalBanque(String succursalBanque) {
        this.succursalBanque = succursalBanque;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNas() {
        return nas;
    }

    public void setNas(String nas) {
        this.nas = nas;
    }

    public String getLanguageCommunication() {
        return languageCommunication;
    }

    public void setLanguageCommunication(String languageCommunication) {
        this.languageCommunication = languageCommunication;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCreationPar() {
        return creationPar;
    }

    public void setCreationPar(String creationPar) {
        this.creationPar = creationPar;
    }

    public Date getCreerLe() {
        return creerLe;
    }

    public void setCreerLe(Date creerLe) {
        this.creerLe = creerLe;
    }

    public String getModificationPar() {
        return modificationPar;
    }

    public void setModificationPar(String modificationPar) {
        this.modificationPar = modificationPar;
    }

    public Date getModifierLe() {
        return modifierLe;
    }

    public void setModifierLe(Date modifierLe) {
        this.modifierLe = modifierLe;
    }

    @Override
    public String toString() {
        return "Individu{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", telProfessionel='" + telProfessionel + '\'' +
                ", telPerso='" + telPerso + '\'' +
                ", statutCivil='" + statutCivil + '\'' +
                ", succursalBanque='" + succursalBanque + '\'' +
                ", sexe='" + sexe + '\'' +
                ", province='" + province + '\'' +
                ", nas='" + nas + '\'' +
                ", languageCommunication='" + languageCommunication + '\'' +
                ", handicap='" + handicap + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", codePostal='" + codePostal + '\'' +
                ", creationPar='" + creationPar + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }


    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }


}
