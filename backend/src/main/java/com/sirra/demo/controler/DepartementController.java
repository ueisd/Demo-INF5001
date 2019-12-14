package com.sirra.demo.controler;


import com.sirra.demo.configuration.AppConfig;
import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.exceptions.FdtException;
import com.sirra.demo.metier.*;
import com.sirra.demo.model.*;
import com.sirra.demo.model.options.FillOptions;
import com.sirra.demo.model.options.FillVerticalOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Api("Gestion pour departement")
@RestController
@CrossOrigin
public class DepartementController  {

    @Autowired
    DepartementDao departementDao;

    @Autowired
    EmployeDao employeDao;

    GenerateurLignesDeTemps generateurLignesDeTemps;
    GenerateurHoraire generateurHoraire;

    public DepartementController() {
        this.generateurLignesDeTemps = new GenerateurLignesDeTempsImp();
        this.generateurHoraire = new GenerateurHoraireImp();
    }

    public DepartementController(GenerateurLignesDeTemps genLigneTemps, GenerateurHoraire genHoraire) {
        this.generateurLignesDeTemps = genLigneTemps;
        this.generateurHoraire = genHoraire;
    }

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
    @GetMapping(value = "Departement/{id}/debut/{dateDebut}/dateFin/{dateFin}"
    + "/setFillMax/{setFillMax}/setFiilMinOnVoid/{setFiilMinOnVoid}/vOpt/{vOpt}")
    public ArrayList<LigneDeTemps> GenererFDT(@PathVariable int id,
                                              @PathVariable String dateDebut, @PathVariable String dateFin,
                                              @PathVariable int setFillMax, @PathVariable int setFiilMinOnVoid,
                                              @PathVariable int vOpt) throws FdtException {
        FillOptions fillOpt = new FillOptions();
        fillOpt.setFillMax(setFillMax);
        fillOpt.setVerticalOption(FillVerticalOptions.values()[vOpt]);
        fillOpt.setFiilMinOnVoid(setFiilMinOnVoid);

        Departement departement = departementDao.findById(id);
        if(departement == null) throw new FdtException("Le departement avec l'id " + id + " est INTROUVABLE.");

        ZonedDateTime dateLocaleDebut =  Instant.parse(dateDebut).atZone(AppConfig.ZONE_ID);
        ZonedDateTime dateLocaleFin =  Instant.parse(dateFin).atZone(AppConfig.ZONE_ID);

        this.generateurHoraire.initialiserRequete(dateLocaleDebut, dateLocaleFin, departement);
        ArrayList<HoraireOuvertureSemaine> horaireDep = this.generateurHoraire.generate();

        this.generateurLignesDeTemps.initialiserRequete(horaireDep, departement);

        return this.generateurLignesDeTemps.generate(fillOpt);
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
