package com.isoft.framecujdbc.domain.gui.models;

import com.isoft.framecujdbc.domain.Angajat;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iurasun
 */
public class AngajatiTableModel extends DefaultTableModel {

    String[] columnNames = {"#", "ID", "Numele", "Prenumele", "Salariu (MDL)", "Departamentul"};

    public AngajatiTableModel() {

        for (String coloana : columnNames) {
            super.addColumn(coloana);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void refreshData(List<Angajat> listaAngajati) {

        super.dataVector.clear();

        Angajat angajat = null;
        Vector row = null;
        for (int i = 0; i < listaAngajati.size(); i++) {

            angajat = listaAngajati.get(i);

            row = new Vector();

            row.add(i + 1);
            row.add(angajat.getId());
            row.add(angajat.getNume());
            row.add(angajat.getPrenume());
            row.add(angajat.getSalariu());
            row.add(angajat.getDepartament().getDenumire());

            super.addRow(row);

        }

    }

    public void refreshData() {
        super.dataVector.clear();
        super.fireTableDataChanged();
        Angajat angajat = null;
        Vector row = new Vector();

        row.add("");
        row.add("");
        row.add("");
        row.add("");
        row.add("");
        row.add("");

        super.addRow(row);

    }

}
