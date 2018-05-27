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
    JTextField inNumEntries = new JTextField(10);
    JLabel statusPage = new JLabel();

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

        buttonNumEntries.addActionListener(new NumEntriesListener());
        inNumEntries.setMaximumSize(new Dimension(40,25));

        numEntries.add(inNumEntries);
        numEntries.add(buttonNumEntries);

        toLeftPage.addActionListener(new ToLeftPage());
        toRightPage.addActionListener(new ToRightPage());
        statusPage.setText("  1 - " + students.size() + "  ");
        status.add(toLeftPage);
        status.add(statusPage);
        status.add(toRightPage);
        status.add(numEntries, BorderLayout.EAST);

        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.add(table.getTableHeader());
        all.add(table);
        all.add(status);
        add(all);
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

            if(inNumEntries.getText().isEmpty()){
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }

            if(numLastInDB == controler.getStudents().size() - 1){
                return;
            }

            int numNewFirst = numLastInDB + 1;
            int numNewLast = numLastInDB + numEntry;
            if(numNewLast > controler.getStudents().size()){
                numNewLast = controler.getStudents().size() - 1;
            }

            if(numNewFirst == numNewLast){
                students = new ArrayList<Student>();
                students.add(controler.getStudents().get(numNewLast));
                statusPage.setText("  " + String.valueOf((numNewLast+1) + " from " + controler.getStudents().size()) + "  ");
            } else {
                students = new ArrayList<Student>(controler.getStudents().subList(numNewFirst, numNewLast+1));
                statusPage.setText("  " + (numNewFirst+1) + " - " + (numNewLast+1) + " from " + controler.getStudents().size()+"  ");
            }
            //tableModel
            tableModel.setStudents(students);
            repaint();
        }
    }

    public void refresh(){
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
}
