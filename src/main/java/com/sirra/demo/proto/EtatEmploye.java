package com.sirra.demo.proto;

public class EtatEmploye {

    private boolean actif = true;

    private TypeEtat typeEtat = TypeEtat.Normal;

    private int jrCongeRestant = 14;

    private EmployeProto employeProto;

    public EtatEmploye(boolean actif, TypeEtat typeEtat, EmployeProto employeProto) {
        this.actif = actif;
        this.typeEtat = typeEtat;
        this.employeProto = employeProto;
            }

    public EmployeProto getEmployeProto() {
        return employeProto;
    }

    public void setEmployeProto(EmployeProto employeProto) {
        this.employeProto = employeProto;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public TypeEtat getTypeEtat() {
        return typeEtat;
    }

    public void setTypeEtat(TypeEtat typeEtat) {
        this.typeEtat = typeEtat;
    }

    public int getJrCongeRestant() {
        return jrCongeRestant;
    }

    public void setJrCongeRestant(int jrCongeRestant) {
        this.jrCongeRestant = jrCongeRestant;
    }

    public void enlever1JrConge(){
        this.jrCongeRestant = jrCongeRestant-1;
        if(jrCongeRestant < 0 ) {
            System.out.println("L'employe numero " + employeProto.getId()  + " a epuise tout ces jours de conges." +
                    " Veuillez aviser un superieur");
        }
    }

    @Override
    public String toString() {
        return "EtatEmploye{" +
                "actif=" + actif +
                ", typeEtat=" + typeEtat +
                ", jrCongeRestant=" + jrCongeRestant +
                ", employeProto=" + employeProto +
                '}';
    }
}
