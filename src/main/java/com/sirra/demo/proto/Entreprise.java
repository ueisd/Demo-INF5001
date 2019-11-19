package com.sirra.demo.proto;

import com.sirra.demo.proto.manip.StockEmployeEtFDT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Entreprise {

    private Random  random = new Random();


    private int dernierIdDispo = 1;

    private int heureOuverture;

    private int heureFermeture;

    private ArrayList<EmployeProto> employeProtos = new ArrayList<>();

    //1-7 = lundi a dimanche
    private boolean[] journesOuvert = new boolean[7];

    private ArrayList<StockEmployeEtFDT> listDeFDT;



    public Entreprise(int heureOuverture, int heureFermeture) {
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;

    }



    public void peuplerEntreprise(int nbrEmploye){
        while(nbrEmploye > 0){
            employeProtos.add(new EmployeProto(dernierIdDispo,"mike"+ random.nextInt() ,14.50,24,new EtatEmploye()));
            dernierIdDispo++;
            nbrEmploye--;
        }
    }



    public ArrayList<EmployeProto> getEmployeProtos() {
        return employeProtos;
    }

    public void setEmployeProtos(ArrayList<EmployeProto> employeProtos) {
        this.employeProtos = employeProtos;
    }


    /*
    Cette methode intilise les journes ouvert de l'entreprise avec un STRING BINAIRE de 7 char ex. 0110110
     */
    public void initiliaserLesJr(String binaire7){
        if(binaire7.trim().length() == 7){
            for(int i = 0 ; i < binaire7.length()-1; i++){
                if(binaire7.charAt(i) == '0'){
                    journesOuvert[i] = false;
                } else if (binaire7.charAt(i) == '1') {
                    journesOuvert[i] = true;
                } else {
                    System.out.println("Votre binaire7 contient un charactere different de 0 ou 1");
                    System.exit(1);
                }
            }

        } else {
            System.out.println("Le binaire7 n'est pas de 7");
            System.exit(1);
        }

    }



    public void setLundi(boolean ouvert) {
        journesOuvert[0] = ouvert;
    }

    public void setMardi(boolean ouvert) {
        journesOuvert[1] = ouvert;
    }

    public void setMercredi(boolean ouvert) {
        journesOuvert[2] = ouvert;
    }

    public void setJeudi(boolean ouvert) {
        journesOuvert[3] = ouvert;
    }

    public void setVendredi(boolean ouvert) {
        journesOuvert[4] = ouvert;
    }

    public void setSamedi(boolean ouvert) {
        journesOuvert[5] = ouvert;
    }

    public void setDimanche(boolean ouvert) {
        journesOuvert[6] = ouvert;
    }


    public String getJr(int index) {
        switch (index) {
            case 0:
                return "Lundi";
            case 1:
                return "Mardi";
            case 2:
                return "Mercredi";
            case 3:
                return "Jeudi";
            case 4:
                return "Vendredi";
            case 5:
                return "Samedi";
            case 6:
                return "Dimanche";
        }
        return null;
    }

    public int getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(int heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public int getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(int heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public boolean[] getJournesOuvert() {
        return journesOuvert;
    }

    public void setJournesOuvert(boolean[] journesOuvert) {
        this.journesOuvert = journesOuvert;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "dernierIdDispo=" + dernierIdDispo +
                ", heureOuverture=" + heureOuverture +
                ", heureFermeture=" + heureFermeture + "\n"+
                ", employeProtos=" + employeProtos +
                ", journesOuvert=" + Arrays.toString(journesOuvert) +
                '}';
    }
}
