package com.sirra.demo.dao;

import com.sirra.demo.model.LigneDeTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneDeTempsDao extends JpaRepository<LigneDeTemps, Integer> {
    public LigneDeTemps findById(int id);
}