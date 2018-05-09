package com.company.mrbig;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.GeneratorDB;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Model.WriterXML;
import com.company.mrbig.Viev.MainFrame;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        GeneratorDB generator = new GeneratorDB();
        WriterXML writer = new WriterXML(generator.genStudList(50));
        new MainFrame();
    }
}
