package com.isoft.framecujdbc.dao;

import com.isoft.framecujdbc.domain.Angajat;

import java.util.List;

/**
 *
 * @author iurasun
 */
public interface AngajatDaoIntf {

    
    List<Angajat> findAll(Angajat angajat) throws Exception;
    
    //CRUD  
    
    List<Angajat> findAll() throws Exception;
    //Angajat findById(int id)throws Exception;

    public void save(Angajat readForm);

    public Angajat findById(int id) throws Exception;

    public void update(Angajat angajat)throws Exception;

    public void delete(Angajat angajat)throws Exception;
    
}

