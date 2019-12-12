package com.sirra.demo.controler;


import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.exceptions.FdtException;
import com.sirra.demo.metier.GenerationFeuilleTmp;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Diplome;
import com.sirra.demo.model.Employe;
import com.sirra.demo.model.LigneDeTemps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Api("Gestion pour departement")
@RestController
@CrossOrigin
public class DepartementController  {

    @Autowired
    DepartementDao departementDao;

    @Autowired
    EmployeDao employeDao;

    @PostMapping(value = "Departement")
    public ResponseEntity<Void> ajouterDepartement(@Valid @RequestBody Departement departement){

        ArrayList<Integer> ids = departement.getDepartementsIds();
        departement.setEmployes(null);
        Departement d1= departementDao.save(departement);

        if(!ids.isEmpty()) employeDao.addDepartementForEmployes(ids, departement);

        if(d1 == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(d1.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @ApiOperation(value = "Genere une Feuille de temps avec Id et NbrSemaine")
    @GetMapping(value = "Departement/{id}/Semaine/{sem}/debut/{dateDebut}")
    public ArrayList<LigneDeTemps> GenererFDT(@PathVariable int id, @PathVariable int sem, @PathVariable String dateDebut) throws FdtException {
        ArrayList<LigneDeTemps> list = new ArrayList<>();

        Instant instant = Instant.parse(dateDebut);

        TimeZone timezone = TimeZone.getTimeZone("America/Montreal");


        Departement departement = departementDao.findById(id);
        if(departement==null) {
            throw new FdtException("Le departement avec l'id " + id + " est INTROUVABLE.");
        }
        if(sem < 1 ){
            throw new  FdtException("Les semaines doivent etre superieur a 0");
        }

        list = GenerationFeuilleTmp.declencherGeneartionAvecControleur(departement,sem) ;

        return list;
    }

    @GetMapping(value = "Departement")
    public List<Departement> listeDepartement() {
        return departementDao.findAll();
    }
    @GetMapping(value = "Departement/{id}")
    public Departement afficherDepartement(@PathVariable int id){
        Departement departement = departementDao.findById(id);
        return departement;
    }

    @DeleteMapping(value = "Departement/Delete/{id}")
    public void supprimerDepartement(@PathVariable int id) {
        Departement departement = departementDao.findById(id);
        employeDao.removeDepartementForEmployes(departement);
        departementDao.deleteDepartmentS(id);
    }

    @PutMapping (value = "Departement/modifier")
    public void updateDepartement(@RequestBody Departement departement) {

        ArrayList<Integer> ids = departement.getDepartementsIds();
        departement.setEmployes(null);

        employeDao.removeDepartementForEmployes(departement);
        Departement d1= departementDao.save(departement);
        if(!ids.isEmpty()) employeDao.addDepartementForEmployes(ids, departement);
    }


}
