package com.company.mrbig.Controler;

import com.company.mrbig.Model.Model;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Viev;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Controler {
    ArrayList<Student> students;
    Model model;
    Viev viev;

    public Controler() throws ParserConfigurationException, SAXException, IOException {
        model = new Model();
        viev = new Viev(model);
    }
}
