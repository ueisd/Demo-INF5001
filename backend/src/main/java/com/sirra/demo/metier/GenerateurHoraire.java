package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.options.HoraireOuvertureRequete;

import java.time.ZonedDateTime;

public interface GenerateurHoraire {
    public void initialiserRequete(ZonedDateTime DateDeb, ZonedDateTime dateFin, Departement dep);
    public HoraireOuvertureRequete generate();
}
