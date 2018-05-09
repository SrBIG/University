package com.company.mrbig.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class WriterXML {
    private Document document = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder().newDocument();
    private String file;
    private ArrayList<Student> students;

    public WriterXML(ArrayList<Student> students) throws ParserConfigurationException {
        this.students = students;
        this.file = "studentsBUFF.xml";
    }
    public WriterXML(ArrayList<Student> students, String file) throws ParserConfigurationException {
        this.students = students;
        this.file = file;
    }

    public void write() throws TransformerException {
        if (file != null && students != null) {
            Element students = document.createElement("students");
            for (Student studIter : this.students) {
                Element person = document.createElement("person");
                Element name = document.createElement("name");

                Element secondname = document.createElement("secondname");
                secondname.setTextContent(studIter.getSecondname());
                name.appendChild(secondname);

                Element firstname = document.createElement("firstname");
                firstname.setTextContent(studIter.getFirstname());
                name.appendChild(firstname);

                Element patronymic = document.createElement("patronymic");
                patronymic.setTextContent(studIter.getPatronymic());
                name.appendChild(patronymic);

                person.appendChild(name);

                Element address = document.createElement("address");

                Element street = document.createElement("street");
                street.setTextContent(studIter.getStreet());
                address.appendChild(street);

                Element home = document.createElement("home");
                home.setTextContent(studIter.getHome());
                address.appendChild(home);

                Element flat = document.createElement("flat");
                flat.setTextContent(studIter.getFlat());
                address.appendChild(flat);

                person.appendChild(address);

                Element familysize = document.createElement("familysize");
                familysize.setTextContent(String.valueOf(studIter.getFamilySize()));
                person.appendChild(familysize);

                Element livingsquare = document.createElement("livingsquare");
                livingsquare.setTextContent(String.valueOf(studIter.getLivingSquare()));
                person.appendChild(livingsquare);

                students.appendChild(person);
            }
            document.appendChild(students);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        }
    }
}
