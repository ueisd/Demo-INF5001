package com.sirra.demo.dao;

import com.sirra.demo.model.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiplomeDao extends JpaRepository <Diplome, Integer>{
    public Diplome findById(int id);
    List<Diplome> findByidGreaterThan(int noteLimit);
}
