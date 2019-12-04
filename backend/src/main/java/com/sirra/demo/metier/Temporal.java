package com.sirra.demo.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Temporal {



    private Date entre;

    private Date sortie;

    private Calendar calendar = Calendar.getInstance();

//    private DateFormat formatJr = new SimpleDateFormat("dd");

    private DateFormat formatHrEtMin = new SimpleDateFormat("HH:mm");

    public Temporal(Date entre, Date sortie) {
        this.entre = entre;
        this.sortie = sortie;

        calendar.setTime(entre);
    }

    public int nombreHrExercer(){
        return sortie.getHours() - entre.getHours();
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
        return "Date :" + calendar.get(Calendar.DAY_OF_MONTH)+ "/"+ calendar.get(Calendar.MONTH)+ "/" + (entre.getYear()+1900) +
                ", entre=" + formatHrEtMin.format(entre) +
                ", sortie=" + formatHrEtMin.format(sortie);
    }
}