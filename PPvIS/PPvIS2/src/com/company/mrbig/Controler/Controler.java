package com.company.mrbig.Controler;

import com.company.mrbig.Model.ReaderXML;
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
    private ArrayList<Student> students;
    private String file;

    public Controler(ArrayList<Student> students) {
        this.file = "DB/students.xml";
        this.students = students;
    }

    public Controler(String file){
        this.file = file;
    }

    public void getStudentsFromDB() {
        ReaderXML reader = new ReaderXML(file);
        students = reader.read();
    }

    public ArrayList<Student> getStudents(){
        if(students == null || students.size() < 1){
            if (file == null){
                students = new ArrayList<>();
            } else {
                getStudentsFromDB();
            }
        }
        return new ArrayList<>(students);
    }

    public void addStudent(Student addStudent){
        students.add(addStudent);
    }

    public void deleteStudents(ArrayList<Student> delStudent){
        for(Student iterStud : delStudent)
            students.removeIf(student -> student == iterStud);
    }

    public void setFile(String file){
        this.file = file;
        getStudentsFromDB();
    }
}
