/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isoft.framecujdbc.domain.gui;

import com.isoft.framecujdbc.dao.DepartamentDaoIntf;
import com.isoft.framecujdbc.dao.impl.DepartamentDaoImpl;
import com.isoft.framecujdbc.domain.Departament;
import com.isoft.framecujdbc.domain.gui.models.DepartamenteListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import se.datadosen.component.RiverLayout;

/**
 *
 * @author iurasun
 */
public class DepartamentePanel extends JPanel{
    
    JPanel panelLeft =new JPanel();
    JList lista=new JList();
    
    private DepartamentDaoIntf departamentiDao;
    private DepartamenteListModel departamenteListModel;

    JTextField jTextFieldId = new JTextField(4);
    JTextField jTextFieldDenumire = new JTextField(10);
    
    JButton jButtonSave = new JButton("Save");
    JButton jButtonUpdate = new JButton("Update");
    JButton jButtonDelete = new JButton("Delete");
    JButton jButtonClear = new JButton("Clear");
    JButton jButtonFind = new JButton("Find");

    public DepartamentePanel() {

        initComponents();
        addListeners();

        setSize(300, 600);
    }
 private void initComponents() {

        jTextFieldId.setText("" + 0);
        panelLeft.setLayout(new RiverLayout());

        panelLeft.add("center", new JLabel("Registration form"));

        panelLeft.add("p left", new JLabel("Id"));
        panelLeft.add("tab", jTextFieldId);
        panelLeft.add("tab", jButtonFind);

        panelLeft.add("p left", new JLabel("Denumire"));
        panelLeft.add("tab hfill", jTextFieldDenumire);
        
        panelLeft.add("p left", jButtonSave);
        panelLeft.add("tab", jButtonUpdate);
        panelLeft.add("tab", jButtonDelete);
        panelLeft.add("tab", jButtonClear);

        
        setButoaneAcesibile(false);
        
        
        departamentiDao = new DepartamentDaoImpl();
        departamenteListModel = new DepartamenteListModel();
        lista.setModel(departamenteListModel);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        setLayout(new BorderLayout());
        add(panelLeft, BorderLayout.WEST);        
        add(new JScrollPane(lista), BorderLayout.CENTER);
        
    }

    private void addListeners() {

        jButtonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                try {
                    Departament departament  = readForm();
                    if(departament.getDenumire().length()>0){
                    departamentiDao.save(departament);
                    departamenteListModel = new DepartamenteListModel();
                    lista.setModel(departamenteListModel);
                          ArataText.showMesaj(DepartamentePanel.this, "Departament adaugat cu succes.", "Succes", true);
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(DepartamentePanel.class.getName()).log(Level.SEVERE, null, ex);
                     ArataText.showMesaj(DepartamentePanel.this, "Departament nu a fost adaugat."+ex, "Eroor", true);
                }
            }
        });

        jButtonFind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(jTextFieldId.getText());

                    if (id > 0) {
                        Departament departament = departamentiDao.findById(id);
                        if (departament != null) {
                            fillForm(departament);
                        } else {
                            ArataText.showMesaj(DepartamentePanel.this, "Nu este Departament cu id=" + id, "Error", false);
                        }
                    } else {
                        Departament departament = readForm();

                        List<Departament> listaDep = departamentiDao.findAll(departament);
                        if (listaDep.size() > 0) {
                            departamenteListModel = new DepartamenteListModel();
                            lista.setModel(departamenteListModel);
                        } 
                    }

                } catch (Exception ex) {
                    Logger.getLogger(DepartamentePanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(DepartamentePanel.this, "Nu este Departament " + ex.getMessage(), "Error", false);
                }
            }
        });

        jButtonClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        jButtonUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Departament departamentDinFormular = readForm();

                    int id = departamentDinFormular.getId();
                    Departament departament = departamentiDao.findById(id);
                    if (departament != null) {

                        departament.setDenumire(departamentDinFormular.getDenumire());
                        
                        departamentiDao.update(departament);

                        ArataText.showMesaj(DepartamentePanel.this, "Departament modificat cu succes.", "Succes", true);
                        departamenteListModel = new DepartamenteListModel();
                        lista.setModel(departamenteListModel);
                    }

                   
                } catch (Exception ex) {
                    Logger.getLogger(DepartamentePanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(DepartamentePanel.this, "Departament Nu a fost modificat " + ex.getMessage(), "Error", false);
                   
                }
            }
        });

        jButtonDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(jTextFieldId.getText());
                    Departament departament = departamentiDao.findById(id);
                    if (departament != null) {

                        departamentiDao.delete(departament);

                        ArataText.showMesaj(DepartamentePanel.this, "Departament sters cu succes.", "Succes", true);
                        
                        departamenteListModel = new DepartamenteListModel();
                        lista.setModel(departamenteListModel);
                        
                    }

                } catch (Exception ex) {
                    Logger.getLogger(DepartamentePanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(DepartamentePanel.this, "Departament Nu a fost sters " + ex.getMessage(), "Error", false);
                }
                
                
            }
        });
        
        
        lista.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               Departament dep=(Departament)lista.getSelectedValue();
                fillForm(dep);
            }
        });
    }

    public void clearForm() {
        jTextFieldId.setText("" + 0);
        jTextFieldDenumire.setText("");
                
        departamenteListModel = new DepartamenteListModel();
        lista.setModel(departamenteListModel);
    }

    public void setButoaneAcesibile(boolean mod) {
        clearForm();
        jButtonSave.setEnabled(mod);
        jButtonUpdate.setEnabled(mod);
        jButtonDelete.setEnabled(mod);
        jButtonClear.setEnabled(mod);
        jButtonFind.setEnabled(mod);

        lista.setEnabled(mod);

    }

    private Departament readForm() {
        Departament departament = new Departament();

        departament.setId(Integer.parseInt(jTextFieldId.getText()));
        departament.setDenumire(jTextFieldDenumire.getText());
        
        return departament;
    }

    private void fillForm(Departament departament) {
        if(departament == null){
          departament=new Departament();
         }
        jTextFieldId.setText("" + departament.getId());
        jTextFieldDenumire.setText(departament.getDenumire());
        
    }

    

    
}
