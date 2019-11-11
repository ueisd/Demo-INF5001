package com.sirra.demo.controler;


import com.sirra.demo.dao.ContactDao;
import com.sirra.demo.exceptions.ContactIntrouvableException;
import com.sirra.demo.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
