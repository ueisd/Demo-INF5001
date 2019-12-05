package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;

import java.util.ArrayList;

public class StockEmployeEtFDT {

    private ArrayList<Temporal> temporals;

    private Employe employeProto;

    private double salaireDeCetteHoraire = 0;

    public StockEmployeEtFDT(ArrayList<Temporal> temporals, Employe employeProto) {
        this.temporals = temporals;
        this.employeProto = employeProto;
       // calculerSalaire();
       // System.out.println(salaireDeCetteHoraire+"ddddddddd"+employeProto.getId());
    }


    /*public void calculerSalaire(){
        for (Temporal tmp: temporals
             ) {
            salaireDeCetteHoraire = salaireDeCetteHoraire + (tmp.nombreHrExercer() * employeProto.getSalaire());
        }
    }
*/


    public Employe getEmployeProto() {
        return employeProto;
    }

    public void setEmployeProto(Employe employeProto) {
        this.employeProto = employeProto;
    }

    public ArrayList<Temporal> getTemporals() {
        return temporals;
    }

    public void setTemporals(ArrayList<Temporal> temporals) {
        this.temporals = temporals;
    }

    @Override
    public String toString() {
        return "\n**\n*\n*:StockEmployeEtFDT{" +
                "temporals=" + temporals +
                ", employeProto=" + employeProto.getId() +
                '}';
    }
}
