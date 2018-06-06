package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Display;
import com.company.mrbig.Viev.SearchComponent;
import com.company.mrbig.Viev.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogFindStudent extends JDialog{
    ArrayList<Student> students;
    ArrayList<Student> allStudents;
    StudentTableModel tableModel;
    Display display;
    SearchComponent searchComponent;

    JButton searchStudent = new JButton("Search");

    public DialogFindStudent(ArrayList<Student> allStudents){
        setName("Search for students");
        this.allStudents = allStudents;
        this.students = new ArrayList<>(allStudents);
        tableModel  = new StudentTableModel(students);
        display = new Display(tableModel, allStudents);
        searchComponent = new SearchComponent(allStudents);

        setModal(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setSize(700, 600);
        add(searchComponent);
        searchStudent.addActionListener(new SearchListener());
        add(searchStudent);
        add(new JScrollPane(display), BorderLayout.CENTER);

        setVisible(true);
    }

    protected class SearchListener implements ActionListener {
        String secondname;
        int familySize = -1;
        double livingSquare = -1;
        double onePersonSquareMIN = -1;
        double onePersonSquareMAX = -1;
        public void actionPerformed(ActionEvent actionEvent) {
            students = new ArrayList<>(allStudents);
            checkParametr();
            search();
            showResult();
        }

        protected void checkParametr(){
            secondname = null;
            familySize = -1;
            livingSquare = -1;
            onePersonSquareMIN = -1;
            onePersonSquareMAX = -1;

            if(searchComponent.getFamilySizeCB().isSelected() && searchComponent.getSFamilySize().getText().trim().isEmpty() == false){
                familySize = Integer.parseInt(searchComponent.getSFamilySize().getText());
            }
            if(searchComponent.getSecondnameCB().isSelected() && searchComponent.getSSecondname().getText().trim().isEmpty() == false){
                secondname = searchComponent.getSSecondname().getText();
            }
            if(searchComponent.getLivingSquareCB().isSelected() && searchComponent.getSLivingSquare().getText().trim().isEmpty() == false){
                livingSquare = Double.parseDouble(searchComponent.getSLivingSquare().getText());
            }
            if(searchComponent.getOnePersonSquareCB().isSelected() &&
                    searchComponent.getSOnePersonSquareMAX().getText().trim().isEmpty() == false &&
                    searchComponent.getSOnePersonSquareMIN().getText().trim().isEmpty() == false){
                onePersonSquareMIN = Double.parseDouble(searchComponent.getSOnePersonSquareMIN().getText());
                onePersonSquareMAX = Double.parseDouble(searchComponent.getSOnePersonSquareMAX().getText());
            }
        }

        protected void search() {
            if (familySize != -1) {
                students.removeIf(student -> student.getFamilySize() != familySize);
            }
            if (secondname != null) {
                students.removeIf(student -> student.getSecondname().equals(secondname) == false);
            }
            if (livingSquare != -1.0) {
                students.removeIf(student -> student.getLivingSquare() != livingSquare);
            }
            if (onePersonSquareMIN != -1.0 && onePersonSquareMAX != -1.0) {
                students.removeIf(student -> student.getOnePersonSquare() < onePersonSquareMIN);
                students.removeIf(student -> student.getOnePersonSquare() > onePersonSquareMAX);
            }
        }
        protected void showResult(){
            if(students.size() == 0){
                JOptionPane.showMessageDialog(null, "Student(s) not found");
            }
            display.refresh(students, allStudents);
        }
    }
}

