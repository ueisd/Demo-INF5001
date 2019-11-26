package com.sirra.demo.dao;

import com.sirra.demo.model.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosteDao extends JpaRepository <Poste, Integer> {
        public Poste findById(int id);
        List<Poste> findByidGreaterThan(int noteLimit);
}
