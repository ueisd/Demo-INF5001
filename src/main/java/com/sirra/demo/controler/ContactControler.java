package com.sirra.demo.controler;


import com.sirra.demo.dao.ContactDao;
import com.sirra.demo.model.Contact;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
