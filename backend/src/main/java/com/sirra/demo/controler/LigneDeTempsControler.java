package com.sirra.demo.controler;

import com.sirra.demo.dao.LigneDeTempsDao;
import com.sirra.demo.exceptions.FdtException;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.StatutLigneTemps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Api(description = "Gestion des contacts")
@RestController
@CrossOrigin
public class LigneDeTempsControler {
    @Autowired
    private LigneDeTempsDao ligneDeTempsDao;



    @GetMapping(value = "lignesDeTemps")
    public List<LigneDeTemps> listeLigneDeTemps(){
        return ligneDeTempsDao.findAll();
    }


    @ApiOperation(value = "Obtien une liste de lignes de temps d'un departement dans un interval déterminé")
    @GetMapping(value = "lignesDeTemps/departement/{depId}/dateDebut/{dateDebut}/dateFin/{dateFin}")
    public ArrayList<LigneDeTemps> GenererFDT(@PathVariable int depId,
                                              @PathVariable String dateDebut, @PathVariable String dateFin
    ) throws FdtException {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();

        ZonedDateTime dateDebutD = ZonedDateTime.parse(dateDebut);
        ZonedDateTime dateFinD = ZonedDateTime.parse(dateFin);



        lignesDeTemps = (ArrayList<LigneDeTemps>) ligneDeTempsDao.getLignesDeTempsDep(depId, dateDebutD, dateFinD);
        return lignesDeTemps;
    }


    @PostMapping(value = "lignesDeTemps")
    public ResponseEntity<Void> ajouterLigneDeTemps(@Valid @RequestBody LigneDeTemps ligneDeTemps) {

        ligneDeTemps.metreAjourDates();

        LigneDeTemps ligneDeTemps1 = ligneDeTempsDao.save(ligneDeTemps);

        if(ligneDeTemps1 == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ligneDeTemps1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "lignesDeTemps/addAll")
    public ResponseEntity<Void> ajouterPlusieursLigneDeTemps(@Valid @RequestBody ArrayList<LigneDeTemps> ligneDeTemps) {

        List<LigneDeTemps> ligneDeTempsSupprimer = new ArrayList<LigneDeTemps>();

        Iterator<LigneDeTemps> iter = ligneDeTemps.listIterator();
        while(iter.hasNext()) {
            LigneDeTemps ligne = iter.next();
            if(ligne.getStatut() == StatutLigneTemps.DELETE_SAVED.ordinal()) {
                iter.remove();
                //ligneDeTempsSupprimer.add(ligne);
                if(ligne.getId()!= 0 && ligneDeTempsDao.existsById(ligne.getId())) {
                    ligneDeTempsDao.deleteById(ligne.getId());
                }
            }else if(ligne.getStatut() == StatutLigneTemps.APROVED.ordinal()) {
                ligne.metreAjourDates();
                ligne.setStatut(StatutLigneTemps.SAVED.ordinal());
            }
        }
        List<LigneDeTemps> ligneDeTemps1 = new ArrayList<>();
        if(!ligneDeTemps.isEmpty()) {
            ligneDeTemps1 = ligneDeTempsDao.saveAll(ligneDeTemps);
        }

        if(ligneDeTemps1.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "lignesDeTemps/Delete/{id}")
    public void supprimerLigneDeTemps(@PathVariable int id) {
        ligneDeTempsDao.deleteById(id);
    }

    @ApiOperation(value = "Obtien une liste de lignes de temps d'un departement dans un interval déterminé")
    @GetMapping(value = "lignesDeTemps/employe/{depId}/dateDebut/{dateDebut}/dateFin/{dateFin}")
    public ArrayList<LigneDeTemps> getLignesDeTempsEmp(@PathVariable int depId,
                                              @PathVariable String dateDebut, @PathVariable String dateFin
    ) throws FdtException {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();

        ZonedDateTime dateDebutD = ZonedDateTime.parse(dateDebut);
        ZonedDateTime dateFinD = ZonedDateTime.parse(dateFin);

        lignesDeTemps = (ArrayList<LigneDeTemps>) ligneDeTempsDao.getLignesDeTempsEmp(depId, dateDebutD, dateFinD);
        return lignesDeTemps;
    }


}
