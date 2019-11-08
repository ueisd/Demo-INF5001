package com.sirra.demo.dao;
import com.sirra.demo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface DocumentDao extends JpaRepository <Document, Integer>{
    public Document findById(int id);
    List<Document> findByidGreaterThan(int noteLimit);
}
