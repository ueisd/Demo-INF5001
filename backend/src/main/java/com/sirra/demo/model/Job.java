package com.sirra.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Job {

    public Job () {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "job_id")
    private int id;

    @Column(name = "CodeJob")
    @Length(min=2,max = 30)
    private String codeJob;

    //TBL POSTE ID (NON CREER)

    @Column(name = "JobName")
    @Length(min = 3, max = 40)
    private String jobName;

    @Length(min=6,max=100, message = "Veuillez verifier que la description est entre 6 et 100 charactere")
    private String jobDescription;

    private Date dateEffet;

    private Date dateExp;

    @Column(name = "codeCategorie")
    @Length(min=2,max=10, message = "Veuillez verifier que le code est entre 2 et 10 charactere")
    private String codeCategorie;

    private double salaireHorMin;

    private double salaireHorMax;



    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String creationPar;

    private Date creerLe;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String modificationPar;

    private Date modifierLe;

    public int getId() {
        return id;
    }

    public void setId(int jobId) {
        this.id = jobId;
    }

    public String getCodeJob() {
        return codeJob;
    }

    public void setCodeJob(String codeJob) {
        this.codeJob = codeJob;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public double getSalaireHorMin() {
        return salaireHorMin;
    }

    public void setSalaireHorMin(double salaireHorMin) {
        this.salaireHorMin = salaireHorMin;
    }

    public double getSalaireHorMax() {
        return salaireHorMax;
    }

    public void setSalaireHorMax(double salaireHorMax) {
        this.salaireHorMax = salaireHorMax;
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
        return "Job{" +
                "id=" + id +
                ", codeJob='" + codeJob + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", dateEffet=" + dateEffet +
                ", dateExp=" + dateExp +
                ", codeCategorie='" + codeCategorie + '\'' +
                ", salaireHorMin=" + salaireHorMin +
                ", salaireHorMax=" + salaireHorMax +
                ", creationPar='" + creationPar + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }
}
