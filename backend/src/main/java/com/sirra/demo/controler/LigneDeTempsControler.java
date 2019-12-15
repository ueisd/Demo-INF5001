package com.sirra.demo.controler;

import com.sirra.demo.dao.LigneDeTempsDao;
import com.sirra.demo.model.LigneDeTemps;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Api(description = "Gestion des contacts")
@RestController
public class LigneDeTempsControler {
    @Autowired
    private LigneDeTempsDao ligneDeTempsDao;

    //Retourne la liste de tout les contacts
    @GetMapping(value = "lignesDeTemps")
    public List<LigneDeTemps> listeLigneDeTemps(){
        return ligneDeTempsDao.findAll();
    }

    @PostMapping(value = "lignesDeTemps")
    public ResponseEntity<Void> ajouterLigneDeTemps(@Valid @RequestBody LigneDeTemps ligneDeTemps) {


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

        List<LigneDeTemps> ligneDeTemps1 = ligneDeTempsDao.saveAll(ligneDeTemps);

        //LigneDeTemps ligneDeTemps1 = null;
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


}
