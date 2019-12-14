package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;

public interface AligneurVertical {

    public void initialiserRequete(Employe employe, FillOptions fillOptions);
    public LigneDeTemps generateVLine(IntervalTempsZoneLocale interval);

}
