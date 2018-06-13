package com.company.mrbig;

import com.company.mrbig.Model.GeneratorDB;
import com.company.mrbig.Model.WriterXML;
import com.company.mrbig.Viev.Dialogs.DialogStart;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        GeneratorDB generator = new GeneratorDB();
        WriterXML writer = new WriterXML(generator.genStudList(50));
        writer.write();
        new DialogStart();
    }
}
