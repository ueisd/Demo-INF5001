package com.sirra.demo.proto.manip;

import com.sirra.demo.proto.EmployeProto;
import com.sirra.demo.proto.Entreprise;
import com.sirra.demo.proto.Temporal;

import java.util.ArrayList;
import java.util.Date;

public class GenerationFeuilleTmp {

    public static  void main(String[] args) {

        initialiserLentreprise(7,18,5,"0011110");


    }



    private EmployeProto employeProto;

    private ArrayList<Temporal> tabTemp;



    public static  void initialiserLentreprise(int hrOuvert,int hrFerme, int nbrEmploye,String journeOuvertEnBinaire){
        Entreprise entreprise = new Entreprise(hrOuvert, hrFerme);
        entreprise.initiliaserLesJr(journeOuvertEnBinaire);
        entreprise.peuplerEntreprise(nbrEmploye);

    }



    public void genererFDT(){




    }

    public void chosirNombreSem(int nbrSemaine){
        for (int i = nbrSemaine * 7 ;i>0;i--){


        }
    }



}


