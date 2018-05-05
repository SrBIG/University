package com.company.mrbig.Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Model implements TableModel {
    private String fileName;
    private AbstractList<Student> students = new ArrayList<Student>();
    private Student readStudent = new Student();

    public Model(String fileName) throws ParserConfigurationException, SAXException {
        this.fileName = fileName;
    }

    public void readFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler(){
            boolean firstname = false;
            boolean secondname = false;
            boolean patronymic = false;
            boolean street = false;
            boolean home  = false;
			boolean flat = false;
            boolean familysize = false;
            boolean livingsquare = false;

            public void startElement(String uri, String localName,String qName,
                                     Attributes attributes) throws SAXException{
                if (qName.equalsIgnoreCase("FIRSTNAME")) {
                    firstname = true;
                }
                if (qName.equalsIgnoreCase("SECONDNAME")) {
                    secondname = true;
                }
                if (qName.equalsIgnoreCase("PATRONYMIC")) {
                    patronymic = true;
                }
                if (qName.equalsIgnoreCase("STREET")) {
                    street = true;
                }
                if (qName.equalsIgnoreCase("HOME")) {
                    home = true;
                }
                if (qName.equalsIgnoreCase("FLAT")) {
                    flat = true;
                }
                if (qName.equalsIgnoreCase("FAMILYSIZE")) {
                    familysize = true;
                }
                if (qName.equalsIgnoreCase("LIVINGSQUARE")) {
                    livingsquare = true;
                }
            }

            public void endElement(String uri, String localName,
                                   String qName) throws SAXException {
                double onePersonSquare = readStudent.getLivingSquare() / readStudent.getFamilySize();
                readStudent.setOnePersonSquare(onePersonSquare);
                students.add(readStudent);
            }

            public void characters(char ch[], int start, int length) throws SAXException {
                if (firstname) {
                    readStudent.setFirstname(new String(ch, start, length));
                    firstname = false;
                }
                if (secondname) {
                    readStudent.setSecondname(new String(ch, start, length));
                    secondname = false;
                }
                if (patronymic) {
                    readStudent.setPatronymic(new String(ch, start, length));
                    patronymic = false;
                }
                if (street) {
                    readStudent.setStreet(new String(ch, start, length));
                    street = false;
                }
                if (home) {
                    readStudent.setHome(new String(ch, start, length));
                    home = false;
                }
                if (flat) {
                    readStudent.setFlat(new String(ch, start, length));
                    flat = false;
                }
                if (familysize) {
                    readStudent.setFamilySize(Integer.parseInt(new String(ch, start, length)));
                    familysize = false;
                }
                if (livingsquare) {
                    readStudent.setLivingSquare(Integer.parseInt(new String(ch, start, length)));
                    livingsquare = false;
                }
            }
        };
            if (fileName.trim().isEmpty()) {
                saxParser.parse(fileName, handler);
            } else {
                saxParser.parse("/home/mrbig/Универ/students.xml", handler);
            }
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
        return Object.class;
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
}
