package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Display;
import com.company.mrbig.Viev.SearchComponent;
import com.company.mrbig.Viev.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogDeleteStudent extends JDialog {
    Controler controler;
    ArrayList<Student> allStudents;
    ArrayList<Student> students;
    SearchComponent searchComponent;

    JButton searchStudent = new JButton("Search");
    JButton delete;

    public DialogDeleteStudent(ArrayList<Student> allStudents, Controler controler) {
        setName("Delete student");
        this.controler = controler;
        this.allStudents = allStudents;
        this.students = new ArrayList<>(allStudents);
        searchComponent = new SearchComponent(allStudents);

        delete = new JButton("Delete");
        delete.addActionListener(new DeleteStudent());

        setModal(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(searchComponent);

        add(delete, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }


    class DeleteStudent implements ActionListener {
        String secondname;
        int familySize = -1;
        double livingSquare = -1;
        double onePersonSquareMIN = -1;
        double onePersonSquareMAX = -1;
        public void actionPerformed(ActionEvent actionEvent) {
            checkParametr();
            search();
            showResult();
            deleteListStudent();
            setVisible(false);
        }
        private void deleteListStudent(){
            if(students.size() == 0){
                JOptionPane.showMessageDialog(null, "Nothing was deleted");
                return;
            }
            controler.deleteStudents(students);
            JOptionPane.showMessageDialog(null, "Deleted " + students.size() + " student(s)");
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
            //display.refresh(students);
        }
    }
}
