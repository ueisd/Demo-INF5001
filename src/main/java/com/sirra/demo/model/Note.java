package com.sirra.demo.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "note_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Individu", referencedColumnName = "Individu_Id")
    private Individu individu;


    @Column(name = "Note")
    @Length(min=6,max = 150,message = "Veuillez revoir la longueur de la note (entre 6 et 150 charactere)")
    private String note;


    @Column(name = "Note_Par")
    @Length(min=3,max = 25)
    private String notePar;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String creationPar;

    private Date creerLe;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String modificationPar;

    private Date modifierLe;

    public int getNoteId() {
        return id;
    }

    public void setNoteId(int noteId) {
        this.id = noteId;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNotePar() {
        return notePar;
    }

    public void setNotePar(String notePar) {
        this.notePar = notePar;
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
        return "Note{" +
                "noteId=" + id +
                ", individu=" + individu +
                ", note='" + note + '\'' +
                ", notePar='" + notePar + '\'' +
                ", creationPar='" + creationPar + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }
}



