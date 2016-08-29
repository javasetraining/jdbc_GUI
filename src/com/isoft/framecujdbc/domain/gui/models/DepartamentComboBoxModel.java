/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isoft.framecujdbc.domain.gui.models;

import com.isoft.framecujdbc.dao.DepartamentDaoIntf;
import com.isoft.framecujdbc.dao.impl.DepartamentDaoImpl;
import com.isoft.framecujdbc.domain.Departament;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author iurasun
 */
public class DepartamentComboBoxModel extends DefaultComboBoxModel{
    
    List<Departament> departamente;
    DepartamentDaoIntf departamenteDao;

    
    
    public DepartamentComboBoxModel() {
        
        departamenteDao =  new DepartamentDaoImpl();
        try {
            departamente= departamenteDao.findAll();
            
            Departament departament=new Departament();
            departament.setDenumire("Selecteza..");
            
            super.addElement(departament);
            for (Departament dep : departamente) {
                super.addElement(dep);
            }
        } catch (Exception ex) {
            Logger.getLogger(DepartamentComboBoxModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
