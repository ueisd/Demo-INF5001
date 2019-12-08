package com.sirra.demo.controler;


import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.exceptions.FdtException;
import com.sirra.demo.metier.GenerationFeuilleTmp;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Diplome;
import com.sirra.demo.model.Employe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(value = "Departement")
    public ResponseEntity<Void> ajouterDepartement(@Valid @RequestBody Departement departement){

        List<Employe> listeEmployes = departement.getEmployes();
        List<Employe> listEmpRet = new ArrayList<Employe>();

        departement.setEmployes(listEmpRet);

        Departement d1= departementDao.save(departement);

        for(int i =0; i < listeEmployes.size(); i++) {
            Employe employe = listeEmployes.get(i);
            if(employeDao.existsById(employe.getId())) {
                employe.setDepartement(d1);
                employeDao.save(employe);
            }
        }

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
    @GetMapping(value = "Departement/{id}/Semaine/{sem}")
    public ArrayList<Object[]> GenererFDT(@PathVariable int id, @PathVariable int sem) throws FdtException {
        ArrayList<Object[]> list = new ArrayList<>();


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


        List<Employe> listeEmployes = departement.getEmployes();
        List<Employe> listEmpRet = new ArrayList<Employe>();

        departement.setEmployes(listEmpRet);

        employeDao.removeDepartementForEmployes(departement);

        Departement d1= departementDao.save(departement);

        for(int i =0; i < listeEmployes.size(); i++) {
            Employe employe = listeEmployes.get(i);
            if(employeDao.existsById(employe.getId())) {
                employe.setDepartement(d1);
                employeDao.save(employe);
            }
        }


        //departementDao.save(departement);
    }


}
