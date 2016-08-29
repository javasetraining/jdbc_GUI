package com.isoft.framecujdbc.domain.gui;

import com.isoft.framecujdbc.dao.AngajatDaoIntf;
import com.isoft.framecujdbc.domain.Angajat;
import com.isoft.framecujdbc.domain.Departament;
import com.isoft.framecujdbc.domain.gui.models.AngajatiTableModel;
import com.isoft.framecujdbc.domain.gui.models.DepartamentComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import se.datadosen.component.RiverLayout;

/**
 *
 * @author iurasun
 */
public class AngajatiPanel extends JPanel {

    private AngajatDaoIntf angajatiDao;
    private AngajatiTableModel angajatiTableModel;

    JTextField jTextFieldId = new JTextField(4);
    JTextField jTextFieldNume = new JTextField(10);
    JTextField jTextFieldPrenume = new JTextField(10);
    JTextField jTextFieldSalariu = new JTextField(6);

    JButton jButtonSave = new JButton(" Save ");
    JButton jButtonUpdate = new JButton("Update");
    JButton jButtonDelete = new JButton("Delete ");
    JButton jButtonClear = new JButton("Clear ");
    JButton jButtonFind = new JButton(" Find ");

    JComboBox jComboBoxDepartamente = new JComboBox();

    DepartamentComboBoxModel departamentComboBoxModel;

    public AngajatiPanel() {

        initComponents();
        addListeners();

        setSize(300, 600);
    }

    private void initComponents() {

        jTextFieldId.setText("" + 0);
        setLayout(new RiverLayout());

        add("center", new JLabel("Registration form"));

        add("p left", new JLabel("Id"));
        add("tab", jTextFieldId);
        add("tab", jButtonFind);

        add("p left", new JLabel("Nume"));
        add("tab hfill", jTextFieldNume);

        add("p left", new JLabel("Prenume"));
        add("tab hfill", jTextFieldPrenume);

        add("p left", new JLabel("Salariu"));
        add("tab", jTextFieldSalariu);

        add("p left", new JLabel("Departament"));
        add("tab hfill", jComboBoxDepartamente);

        add("p left", jButtonSave);
        add("tab", jButtonUpdate);
        add("tab", jButtonDelete);
        add("tab", jButtonClear);

        setButoaneAcesibile(false);
    }

    private void addListeners() {

        jButtonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                angajatiDao.save(readForm());
                try {
                    angajatiTableModel.refreshData(angajatiDao.findAll());
                        ArataText.showMesaj(AngajatiPanel.this, "Angajat adaugat cu succes.", "Succes", true);
                } catch (Exception ex) {
                    Logger.getLogger(AngajatiPanel.class.getName()).log(Level.SEVERE, null, ex);
                        ArataText.showMesaj(AngajatiPanel.this, "Angajat nu a fost adaugat." +ex, "Error", true);
                }
            }
        });

        jButtonFind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(jTextFieldId.getText());

                    if (id > 0) {
                        Angajat angajat = angajatiDao.findById(id);
                        if (angajat != null) {
                            fillForm(angajat);
                        } else {
                            ArataText.showMesaj(AngajatiPanel.this, "Nu este Angajat cu id=" + id, "Error", false);
                        }
                    } else {
                        Angajat angajat = readForm();

                        List<Angajat> lista = angajatiDao.findAll(angajat);
                        if (lista.size() > 0) {
                            angajatiTableModel.refreshData(lista);
                        } else {
                            angajatiTableModel.refreshData();
                        }
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AngajatiPanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(AngajatiPanel.this, "Nu este Angajat " + ex.getMessage(), "Error", false);
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

                    Angajat angajatDinFormular = readForm();

                    int id = angajatDinFormular.getId();
                    Angajat angajat = angajatiDao.findById(id);
                    if (angajat != null) {

                        angajat.setNume(angajatDinFormular.getNume());
                        angajat.setPrenume(angajatDinFormular.getPrenume());
                        angajat.setSalariu(angajatDinFormular.getSalariu());

                        angajatiDao.update(angajat);

                        ArataText.showMesaj(AngajatiPanel.this, "Angajat modificat cu succes.", "Succes", true);
                        angajatiTableModel.refreshData(angajatiDao.findAll());
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AngajatiPanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(AngajatiPanel.this, "Angajat Nu a fost modificat " + ex.getMessage(), "Error", false);
                }
            }
        });

        jButtonDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(jTextFieldId.getText());
                    Angajat angajat = angajatiDao.findById(id);
                    if (angajat != null) {

                        angajatiDao.delete(angajat);

                        ArataText.showMesaj(AngajatiPanel.this, "Angajat sters cu succes.", "Succes", true);
                        angajatiTableModel.refreshData(angajatiDao.findAll());
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AngajatiPanel.class.getName()).log(Level.SEVERE, null, ex);
                    ArataText.showMesaj(AngajatiPanel.this, "Angajat Nu a fost sters " + ex.getMessage(), "Error", false);
                }
            }
        });
    }

    public void clearForm() {
        jTextFieldId.setText("" + 0);
        jTextFieldNume.setText("");
        jTextFieldPrenume.setText("");
        jTextFieldSalariu.setText("" + 0);
        jComboBoxDepartamente.setSelectedIndex(-1);
        departamentComboBoxModel = new DepartamentComboBoxModel();
        jComboBoxDepartamente.setModel(departamentComboBoxModel);
    }

    public void setButoaneAcesibile(boolean mod) {
        clearForm();
        jButtonSave.setEnabled(mod);
        jButtonUpdate.setEnabled(mod);
        jButtonDelete.setEnabled(mod);
        jButtonClear.setEnabled(mod);
        jButtonFind.setEnabled(mod);

        jComboBoxDepartamente.setEnabled(mod);

    }

    private Angajat readForm() {
        Angajat angajat = new Angajat();

        angajat.setId(Integer.parseInt(jTextFieldId.getText()));
        angajat.setNume(jTextFieldNume.getText());
        angajat.setPrenume(jTextFieldPrenume.getText());
        angajat.setSalariu(new Integer(jTextFieldSalariu.getText()));

        Departament dep=(Departament)jComboBoxDepartamente.getSelectedItem();
        if(dep.getId()==0){
            dep=new Departament();
        }
        angajat.setDepartament(dep);
        
        return angajat;
    }

    private void fillForm(Angajat angajat) {
        jTextFieldId.setText("" + angajat.getId());
        jTextFieldNume.setText(angajat.getNume());
        jTextFieldPrenume.setText(angajat.getPrenume());
        jTextFieldSalariu.setText("" + angajat.getSalariu());
        
        Departament dep=angajat.getDepartament();
        if(dep==null || dep.getId() ==0){
          jComboBoxDepartamente.setSelectedIndex(0);
        }else{
         jComboBoxDepartamente.setSelectedItem(dep);
        }

    }

    public void setAngajatiDao(AngajatDaoIntf angajatiDao) {
        this.angajatiDao = angajatiDao;
    }

    public void setAngajatiTableModel(AngajatiTableModel angajatiTableModel) {
        this.angajatiTableModel = angajatiTableModel;
    }

}
