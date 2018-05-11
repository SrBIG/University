package com.company.mrbig.Viev;

import com.company.mrbig.Model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentTableModel extends AbstractTableModel {
    private ArrayList<Student> students;

    public StudentTableModel(ArrayList<Student> students) {
        this.students = students;
    }

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
        return null;
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
                return "Площадь на человека(м^2)";
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
                return stud.getName();
            case 1:
                return stud.getAdress();
            case 2:
                return stud.getFamilySize();
            case 3:
                return stud.getLivingSquare();
            case 4:
                return stud.getOnePersonSquare();
            default:
                return "Error";
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }
}
