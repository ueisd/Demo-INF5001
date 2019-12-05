package com.sirra.demo.model;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

    @Entity
    public class Diplome {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private int id;

        @ManyToOne
        @JoinColumn(name = "id_Individu")
        private Individu individu;

        @Column(name = "statut_Diplome")
        @Length(min = 2, max = 30,message = "Caractère insuffisants ou trop longs")
        private String statutDiplome;

        @Column(name = "nom_Diplome")
        @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private String nomDiplome;

        @Column(name = "code_Diplome")
        @Length(min = 2,max = 30, message = "Caractère insuffisants ou trop longs" )
        private String codeStatutDiplome;

        @Column(name = "mention_Diplome")
        @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private String mentionDiplome;

        @Column(name = "lieu_Diplome")
        @Length(min = 2,max = 30, message = "Caractère insuffisants ou trop longs" )
        private String lieuObtention;

        @Column(name = "etablissement_Diplome")
        @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private String etablissementObtention;


    @Temporal(TemporalType.DATE)
    @Column(name = "date_Obtention")
    private Date dateObtention;

        @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
        private String creationPar;


    @Temporal(TemporalType.DATE)
    private Date creerLe;

        @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
        private String modificationPar;

    @Temporal(TemporalType.DATE)
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

    //    public void setIndividu(Individu individu) {
      //      this.individu = individu;
        //}

        public String getStatutDiplome() {
            return statutDiplome;
        }

        public void setStatutDiplome(String statutDiplome) {
            this.statutDiplome = statutDiplome;
        }

        public String getNomDiplome() {
            return nomDiplome;
        }

        public void setNomDiplome(String nomDiplome) {
            this.nomDiplome = nomDiplome;
        }

        public String getCodeStatutDiplome() {
            return codeStatutDiplome;
        }

        public void setCodeStatutDiplome(String codeStatutDiplome) {
            this.codeStatutDiplome = codeStatutDiplome;
        }

        public String getMentionDiplome() {
            return mentionDiplome;
        }

        public void setMentionDiplome(String mentionDiplome) {
            this.mentionDiplome = mentionDiplome;
        }

        public String getLieuObtention() {
            return lieuObtention;
        }

        public void setLieuObtention(String lieuObtention) {
            this.lieuObtention = lieuObtention;
        }

        public String getEtablissementObtention() {
            return etablissementObtention;
        }

        public void setEtablissementObtention(String etablissementObtention) {
            this.etablissementObtention = etablissementObtention;
        }

        public Date getDateObtention() {
            return dateObtention;
        }

        public void setDateObtention(Date dateObtention) {
            this.dateObtention = dateObtention;
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
            return "Diplome{" +
                    "id=" + id +
                    ", individu=" + individu +
                    ", statutDiplome='" + statutDiplome + '\'' +
                    ", nomDiplome='" + nomDiplome + '\'' +
                    ", codeStatutDiplome='" + codeStatutDiplome + '\'' +
                    ", mentionDiplome='" + mentionDiplome + '\'' +
                    ", lieuObtention='" + lieuObtention + '\'' +
                    ", etablissementObtention='" + etablissementObtention + '\'' +
                    ", dateObtention=" + dateObtention +
                    ", creationPar='" + creationPar + '\'' +
                    ", creerLe=" + creerLe +
                    ", modificationPar='" + modificationPar + '\'' +
                    ", modifierLe=" + modifierLe +
                    '}';
        }

        public Diplome() {
        }
    }
