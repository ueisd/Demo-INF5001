package com.sirra.demo.dao;

import com.sirra.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer> {

    public Contact findById(int id);

    List<Contact> findByidGreaterThan(int noteLimit);


    @Query(value =  "SELECT prenom, nom, telperso, telpro, adresse, codePostal FROM Contact c WHERE c.individu.id = :idParam", nativeQuery = true)
    List<Object[]>  chercherLesContactsDunIndividu(@Param("idParam") int id);
}
