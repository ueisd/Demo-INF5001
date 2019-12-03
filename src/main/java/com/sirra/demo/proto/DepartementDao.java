package com.sirra.demo.proto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementDao extends JpaRepository <Entreprise, Integer> {
        public Entreprise findById(int id);
        List<Entreprise> findByidGreaterThan(int noteLimit);
}
