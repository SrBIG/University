package com.company.mrbig.Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.Set;

public class StudentTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0)
            return String.class;
        if (columnIndex == 1)
            return String.class;
        if (columnIndex == 2)
            return int.class;
        if (columnIndex == 3)
            return double.class;
        if (columnIndex == 4)
            return double.class;
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ФИО";
            case 1:
                return "Адрес";
            case 2:
                return "Кол-во членов семьи";
            case 3:
                return "Жилая площадь(м^2)";
            case 4:
                return "Площадь на одного человека(м^2)";
        }
        return "";
    }

    public int getRowCount(){
        return students.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Student stud = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return students.getName();
            case 1:
                return students.getAdress();
            case 2:
                return students.getFamilySize();
            case 3:
                return students.getLivingSquare();
            case 4:
                return students.getOnePersonSquare();
            default:
                return "Error";
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
