package com.company.mrbig.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class WriterXML {
    private Document document;

    {
        try {
            document = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(null, "ERROR!!! Can't write in file.");
        }
    }

    private String file;
    private ArrayList<Student> students;

    public WriterXML(ArrayList<Student> students){
        this.students = students;
        this.file = "DB/students.xml";
    }
    public WriterXML(ArrayList<Student> students, String file) {
        this.students = students;
        this.file = file;
    }

    public void write() {
        if (file != null && students != null) {
            Element students = document.createElement(ParserConstants.STUDENTS);
            for (Student studIter : this.students) {
                Element person = document.createElement(ParserConstants.PERSON);
                Element name = document.createElement(ParserConstants.NAME);

                Element secondname = document.createElement(ParserConstants.SECONDNAME);
                secondname.setTextContent(studIter.getSecondname());
                name.appendChild(secondname);

                Element firstname = document.createElement(ParserConstants.FIRSTNAME);
                firstname.setTextContent(studIter.getFirstname());
                name.appendChild(firstname);

                Element patronymic = document.createElement(ParserConstants.PATRONYMIC);
                patronymic.setTextContent(studIter.getPatronymic());
                name.appendChild(patronymic);

                person.appendChild(name);

                Element address = document.createElement(ParserConstants.ADDRESS);

                Element street = document.createElement(ParserConstants.STREET);
                street.setTextContent(studIter.getStreet());
                address.appendChild(street);

                Element home = document.createElement(ParserConstants.HOME);
                home.setTextContent(studIter.getHome());
                address.appendChild(home);

                Element flat = document.createElement(ParserConstants.FLAT);
                flat.setTextContent(studIter.getFlat());
                address.appendChild(flat);

                person.appendChild(address);

                Element familysize = document.createElement(ParserConstants.FAMILYSIZE);
                familysize.setTextContent(String.valueOf(studIter.getFamilySize()));
                person.appendChild(familysize);

                Element livingsquare = document.createElement(ParserConstants.LIVINGSQUARE);
                livingsquare.setTextContent(String.valueOf(studIter.getLivingSquare()));
                person.appendChild(livingsquare);

                students.appendChild(person);
            }
            document.appendChild(students);
            Transformer transformer = null;
            try {
                transformer = TransformerFactory.newInstance().newTransformer();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            try {
                transformer.transform(domSource, streamResult);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }
}
