package com.sirra.demo.controler;


import com.sirra.demo.dao.ContactDao;
import com.sirra.demo.exceptions.ContactIntrouvableException;
import com.sirra.demo.model.Contact;
import com.sirra.demo.model.Employe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api (description = "Gestion des contacts")
@RestController
public class ContactControler {

    @Autowired
    private ContactDao contactDao;

    //Retourne la liste de tout les contacts
    @GetMapping(value = "Contacts")
    public List<Contact> listeContacts(){
        return contactDao.findAll();

    }

    @ApiOperation(value = "Récupère un contact selon son ID.")
    @GetMapping(value = "Contacts/{id}")
    public Contact afficherUnContact(@PathVariable int id) throws ContactIntrouvableException {
        Contact contact = contactDao.findById(id);
        if(contact == null){ throw  new ContactIntrouvableException("Le contact avec l'id " + id + " est INTROUVABLE.");        }
        return contact;
    }

    @GetMapping(value = "Contacts/pour/{idIndividu}")
    public List<Object[]> testDeRequete(@PathVariable int idIndividu){

        return contactDao.chercherLesContactsDunIndividu(idIndividu);
    }


    @PostMapping(value = "Contacts")
    public ResponseEntity<Void> ajouterContact(@Valid @RequestBody Contact contact) {


        Contact contact1 = contactDao.save(contact);

        if(contact1 == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contact1.getContactId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
