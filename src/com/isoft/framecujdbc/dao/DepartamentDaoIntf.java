package com.isoft.framecujdbc.dao;

import com.isoft.framecujdbc.domain.Departament;

import java.util.List;

/**
 *
 * @author iurasun
 */
public interface DepartamentDaoIntf {

public List<Departament> findAll(Departament departament) throws Exception;
    
    //CRUD  
    
    List<Departament> findAll() throws Exception;
    //Departament findById(int id)throws Exception;

    public void save(Departament readForm) throws Exception;

    public Departament findById(int id) throws Exception;

    public void update(Departament departament)throws Exception;

    public void delete(Departament departament)throws Exception;
    
}

