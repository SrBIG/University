package com.company.mrbig.Viev;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Display extends JPanel{
    ArrayList<Student> students;
    StudentTableModel tableModel;
    Controler controler;

    JTable table;
    JButton buttonNumEntries = new JButton("Press");

    JButton toLeftPage = new JButton("<");
    JButton toRightPage = new JButton(">");
    JButton toLastPage = new JButton("last");
    JButton toFirstPage = new JButton("first");
    JTextField inNumEntries = new JTextField(10);
    JLabel statusPage = new JLabel();
    JLabel numPage = new JLabel();

    JPanel status = new JPanel();
    JPanel numEntries = new JPanel();
    JPanel all = new JPanel();

    int numEntry;

    public Display(StudentTableModel tableModel, Controler controler){
        this.tableModel = tableModel;
        this.controler = controler;
        students = tableModel.getStudents();
        numEntry = students.size();

        status.setLayout(new BoxLayout(status, BoxLayout.X_AXIS));
        table = new JTable(tableModel);
        setSizeColumn();

        buttonNumEntries.addActionListener(new NumEntriesListener());
        inNumEntries.setMaximumSize(new Dimension(40,25));

        numEntries.add(inNumEntries);
        numEntries.add(buttonNumEntries);

        toLeftPage.addActionListener(new ToLeftPage());
        toRightPage.addActionListener(new ToRightPage());
        toLastPage.addActionListener(new LastPage());
        toFirstPage.addActionListener(new FirstPage());

        statusPage.setText("   " + students.size() + "   ");
        numPage.setText("  page: 1/1  ");

        status.add(toLeftPage);
        status.add(statusPage);
        status.add(toRightPage);
        status.add(toFirstPage);
        status.add(toLastPage);
        status.add(numPage);
        status.add(numEntries, BorderLayout.EAST);

        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.add(table.getTableHeader());
        all.add(table);
        all.add(status);
        add(all);
    }

    private void setSizeColumn(){
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(240);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    private class NumEntriesListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                numEntry = Integer.parseInt(inNumEntries.getText());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please, enter the correct value.");
                return;
            }
            if(numEntry > controler.getStudents().size()) {
                numEntry = controler.getStudents().size();
            }
            students = new ArrayList<Student>(controler.getStudents().subList(0,numEntry));
            refresh();
        }
    }

    private class ToLeftPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = controler.getStudents().indexOf(firstOnPage);
            int numLastInDB = controler.getStudents().indexOf(lastOnPage);

            if(inNumEntries.getText().isEmpty()){
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }

            if(numFirstInDB == 0){
                return;
            }

            int numNewFirst = numFirstInDB - numEntry;
            int numNewLast = numFirstInDB - 1;

            if(numNewFirst < 0){
                numNewFirst = 0;
                numNewLast = numEntry - 1;
            }

            if(numNewFirst == numNewLast){
                students = new ArrayList<Student>();
                students.add(controler.getStudents().get(numNewLast));
                statusPage.setText("  " + String.valueOf((numNewLast+1) + " from " + controler.getStudents().size()) + "  ");
            } else {
                students = new ArrayList<Student>(controler.getStudents().subList(numNewFirst, numNewLast+1));
                statusPage.setText("  " + (numNewFirst+1) + " - " + (numNewLast+1) + " from " + controler.getStudents().size() + "  ");
            }
            //tableModel
            tableModel.setStudents(students);
            repaint();
        }
    }

    private class ToRightPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = controler.getStudents().indexOf(firstOnPage);
            int numLastInDB = controler.getStudents().indexOf(lastOnPage);

            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }

            if (numLastInDB == controler.getStudents().size() - 1) {
                return;
            }

            int numNewFirst = numLastInDB + 1;
            int numNewLast = numLastInDB + numEntry;
            if (numNewLast > controler.getStudents().size()) {
                numNewLast = controler.getStudents().size() - 1;
            }

            if (numNewFirst == numNewLast) {
                students = new ArrayList<Student>();
                students.add(controler.getStudents().get(numNewLast));
                statusPage.setText("  " + String.valueOf((numNewLast + 1) + " from " + controler.getStudents().size()) + "  ");
            } else {
                students = new ArrayList<Student>(controler.getStudents().subList(numNewFirst, numNewLast + 1));
                statusPage.setText("  " + (numNewFirst + 1) + " - " + (numNewLast + 1) + " from " + controler.getStudents().size() + "  ");
            }
            //tableModel
            tableModel.setStudents(students);
            repaint();
        }
    }

    private class LastPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(controler.getStudents().size() == 0){
                return;
            }
            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }
            ArrayList<Student> cStudent = controler.getStudents();
            students = new ArrayList(cStudent.subList(cStudent.size() - numEntry, cStudent.size()));
            statusPage.setText("  " + (cStudent.size() - numEntry + 1) + " - " + (cStudent.size()) +
                    " from " + controler.getStudents().size() + "  ");
            tableModel.setStudents(students);
            repaint();
        }
    }

    private class FirstPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(controler.getStudents().size() == 0){
                return;
            }
            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }
            ArrayList<Student> cStudent = controler.getStudents();
            students = new ArrayList(cStudent.subList(0, numEntry));
            statusPage.setText("  1" + " - " + (students.size()) +
                    " from " + controler.getStudents().size() + "  ");
            tableModel.setStudents(students);
            repaint();
        }
    }

    public void changeNumPage(){
        numEntry = tableModel.getStudents().size();
    }

    public void refresh(){
        if(numEntry == 0) {
            numEntry = tableModel.getStudents().size();
        }
        if(numEntry > controler.getStudents().size()){
            numEntry = controler.getStudents().size();
        }
        if(tableModel.getStudents().size() > controler.getStudents().size()){
            tableModel.setStudents(controler.getStudents());
        }
        tableModel.setStudents(students);
        statusPage.setText("  1 - " + numEntry + " from " + controler.getStudents().size() + "  ");
        repaint();
    }

    public void refresh(ArrayList<Student> students){
        this.numEntry = students.size();
        this.students = students;
        tableModel.setStudents(students);
        statusPage.setText("  1 - " + numEntry + " from " + controler.getStudents().size() + "  ");
        repaint();
    }
}
