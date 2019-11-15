package com.sirra.demo.proto;

import java.util.Date;

public class Temporal {

    private EmployeProto employeProto;


    private Date entre;

    private Date sortie;

    public Temporal(EmployeProto employeProto, Date entre, Date sortie) {
        this.employeProto = employeProto;
        this.entre = entre;
        this.sortie = sortie;
    }

    public EmployeProto getEmployeProto() {
        return employeProto;
    }

    public void setEmployeProto(EmployeProto employeProto) {
        this.employeProto = employeProto;
    }

    public Date getEntre() {
        return entre;
    }

    public void setEntre(Date entre) {
        this.entre = entre;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    @Override
    public String toString() {
        return "Temporal{" +
                "employeProto=" + employeProto +
                ", entre=" + entre +
                ", sortie=" + sortie +
                '}';
    }
}