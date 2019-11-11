package com.sirra.demo.dao;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividuDao  extends JpaRepository<Individu, Integer> {

    public Individu findById(int id);

    List<Employe> findByIdGreaterThan(int noteLimit);

}
