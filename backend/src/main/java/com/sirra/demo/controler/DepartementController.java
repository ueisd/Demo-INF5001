package com.sirra.demo.controler;


import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Diplome;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api("Gestion pour departement")
@RestController
public class DepartementController  {

    @Autowired
    DepartementDao departementDao;

    @PostMapping(value = "Departement")
    public ResponseEntity<Void> ajouterDepartement(@Valid @RequestBody Departement departement){
        Departement d1= departementDao.save(departement);
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

}
