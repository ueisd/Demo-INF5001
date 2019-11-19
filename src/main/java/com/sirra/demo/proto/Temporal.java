package com.sirra.demo.proto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temporal {


    private Date entre;

    private Date sortie;


//    private DateFormat formatJr = new SimpleDateFormat("dd");

    private DateFormat formatHrEtMin = new SimpleDateFormat("HH:mm");

    public Temporal(Date entre, Date sortie) {
        this.entre = entre;
        this.sortie = sortie;
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
        return "Date :" + entre.getDay()+ "/"+ entre.getMonth()+ "/" + (entre.getYear()+1900) +
                ", entre=" + formatHrEtMin.format(entre) +
                ", sortie=" + formatHrEtMin.format(sortie);
    }
}