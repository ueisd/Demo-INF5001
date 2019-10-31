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


//mANQUE UNE QUERRY    List<Object[]>  chercherLeContactLePlusProchhe(@Param("prixLimit") int prix);
}
