package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.HoraireOuvertureSemaine;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public interface GenerateurHoraire {
    public void initialiserRequete(ZonedDateTime DateDeb, ZonedDateTime dateFin, Departement dep);
    public ArrayList<HoraireOuvertureSemaine> generate();
}
