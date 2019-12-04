package com.sirra.demo.controler;


import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.exceptions.FdtException;
import com.sirra.demo.exceptions.IndividuIntrouvableException;
import com.sirra.demo.metier.GenerationFeuilleTmp;
import com.sirra.demo.model.Departement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(description = "Gestion des FDT")
@RestController
public class FdtController {
    @Autowired
    private DepartementDao departementDao;


    @ApiOperation(value = "Récupère un Departement selon son ID.")
    @GetMapping(value = "Departement/{id}/Semaine/{sem}")
    public ArrayList<Object[]> GenererFDT(@PathVariable int id, @PathVariable int sem) throws FdtException{
        ArrayList<Object[]> list = new ArrayList<>();


        Departement departement = departementDao.findById(id);
        if(departement==null) {
            throw new FdtException("Le departement avec l'id " + id + " est INTROUVABLE.");
        }
        if(sem < 1 ){
            throw new  FdtException("Les semaines doivent etre superieur a 0");
        }

        list = GenerationFeuilleTmp.declencherGeneartionAvecControleur(departementDao.findById(id),sem) ;

        return list;
    }

}

