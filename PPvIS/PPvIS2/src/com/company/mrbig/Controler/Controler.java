package com.company.mrbig.Controler;

import com.company.mrbig.Viev.StudentTableModel;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Model.WriterXML;
import com.company.mrbig.Viev.MainFrame;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Controler {
    ArrayList<Student> students;
    StudentTableModel studentTableModel;
    MainFrame mainFrame;
    WriterXML writer;

    public Controler() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        studentTableModel = new StudentTableModel();
        students = studentTableModel.getStudents();
        mainFrame = new MainFrame(studentTableModel);
        writer = new WriterXML(students);
        writer.write();
    }
}
