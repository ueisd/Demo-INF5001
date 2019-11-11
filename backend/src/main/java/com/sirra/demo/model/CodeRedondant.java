package com.sirra.demo.model;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;

public class CodeRedondant {

    public static ArrayList<Object> stampModification(String nomModif, Date dateModif){


        @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
         String modificationPar = nomModif;

         Date modifierLe = dateModif;

            ArrayList<Object> tab = new ArrayList<>();

            tab.add(0,modificationPar);
            tab.add(1,modifierLe);

        return tab;
    }



}
