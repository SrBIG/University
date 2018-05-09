package com.company.mrbig.Controler;

import com.company.mrbig.Model.Model;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Model.WriterXML;
import com.company.mrbig.Viev.Viev;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Controler {
    ArrayList<Student> students;
    Model model;
    Viev viev;
    WriterXML writer;

    public Controler() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        model = new Model();
        students = model.getStudents();
        viev = new Viev(model);
        writer = new WriterXML(students);
        writer.write();
    }
}
