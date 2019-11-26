package com.sirra.demo.dao;
import com.sirra.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface NoteDao extends JpaRepository<Note, Integer>{
    public Note findById(int id);
    List<Note> findByidGreaterThan(int noteLimit);

}
