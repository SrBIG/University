package com.company.mrbig.Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderXML {
    private String file;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Student readStudent = new Student();

    public ReaderXML(String file){
        this.file = file;
    }

    public ReaderXML(){
        this.file = "DB/students.xml";
    }

    public ArrayList<Student> read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(null, "Что-то парсер барахлит.");
        } catch (SAXException e) {
            e.printStackTrace();
        }
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
                                     Attributes attributes) {
                if (qName.equalsIgnoreCase(ParserConstants.FIRSTNAME)) {
                    firstname = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.SECONDNAME)) {
                    secondname = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.PATRONYMIC)) {
                    patronymic = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.STREET)) {
                    street = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.HOME)) {
                    home = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.FLAT)) {
                    flat = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.FAMILYSIZE)) {
                    familysize = true;
                }
                if (qName.equalsIgnoreCase(ParserConstants.LIVINGSQUARE)) {
                    livingsquare = true;
                }
            }

            public void endElement(String uri, String localName,
                                   String qName) {
                if (qName.equalsIgnoreCase(ParserConstants.PERSON)) {
                    double onePersonSquare = readStudent.getLivingSquare() / readStudent.getFamilySize();
                    readStudent.setOnePersonSquare(onePersonSquare);
                    students.add(readStudent);
                    readStudent = new Student();
                }
            }

            public void characters(char ch[], int start, int length) {
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
                    readStudent.setLivingSquare(Double.parseDouble(new String(ch, start, length)));
                    livingsquare = false;
                }
            }
        };
        if (file.trim().isEmpty()) {
            try {
                saxParser.parse("DB/students.xml", handler);
            } catch (SAXException e) {
                JOptionPane.showMessageDialog(null, "Can't open TEST file! Please, kill me.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Can't open TEST file! Please, kill me.");
            }
        } else {
            try {
                saxParser.parse(file, handler);
            } catch (SAXException e) {
                JOptionPane.showMessageDialog(null, "Can't open file! Open test file.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Can't open file! Open test file.");
            }
        }
        return students;
    }
}
