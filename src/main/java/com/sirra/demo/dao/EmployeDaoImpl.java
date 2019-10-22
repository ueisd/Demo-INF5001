package com.sirra.demo.dao;

import com.sirra.demo.model.Employe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeDaoImpl implements EmployeDao {

    public static List<Employe> employes = new ArrayList<Employe>();

    static {
        employes.add(new Employe(1, "René", "de Possel 2", 15));
        employes.add(new Employe(2, "Claude", "Pair", 17));
        employes.add(new Employe(3, "Blaise", "Pascal", 4));
    }
    @Override
    public List<Employe> findAll() {
        return employes;
    }

    @Override
    public Employe getById(int id) {

        for (Employe employe : employes) {
            if(employe.getId() == id) {
                return employe;
            }
        }

        return null;
    }

    @Override
    public Employe save(Employe employe) {

        employes.add(employe);
        return employe;

    }
}
