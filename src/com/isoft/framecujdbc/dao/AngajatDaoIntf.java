package com.isoft.framecujdbc.dao;

import com.isoft.framecujdbc.domain.Angajat;

import java.util.List;


public interface AngajatDaoIntf {


    List<Angajat> findAll(Angajat angajat) throws Exception;

    //CRUD  

    List<Angajat> findAll() throws Exception;
    //Angajat findById(int id)throws Exception;

    void save(Angajat readForm);

    Angajat findById(int id) throws Exception;

    void update(Angajat angajat) throws Exception;

    void delete(Angajat angajat) throws Exception;

}

