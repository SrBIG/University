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
    ArrayList<Student> allStudents;
    StudentTableModel tableModel;

    JTable table;
    JButton buttonNumEntries = new JButton("Press");

    JButton toLeftPage = new JButton("<");
    JButton toRightPage = new JButton(">");
    JButton toLastPage = new JButton("last");
    JButton toFirstPage = new JButton("first");
    JTextField inNumEntries = new JTextField(10);
    JLabel statusPage = new JLabel();
    JLabel numPageLabel = new JLabel();

    JPanel status = new JPanel();
    JPanel numEntries = new JPanel();
    JPanel all = new JPanel();

    int numEntry;

    public Display(StudentTableModel tableModel, ArrayList<Student> allStudents){
        this.tableModel = tableModel;
        this.allStudents = allStudents;
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
        numPageLabel.setText("  page: 1/1  ");

        status.add(toLeftPage);
        status.add(statusPage);
        status.add(toRightPage);
        status.add(toFirstPage);
        status.add(toLastPage);
        status.add(numPageLabel);
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
            if(numEntry > allStudents.size()) {
                numEntry = allStudents.size();
            }
            students = new ArrayList<Student>(allStudents.subList(0,numEntry));
            refresh();
        }
    }

    private class ToLeftPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(allStudents.size() == 0) return;
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = allStudents.indexOf(firstOnPage);
            int numLastInDB = allStudents.indexOf(lastOnPage);

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
                students.add(allStudents.get(numNewLast));
                statusPage.setText("  " + String.valueOf((numNewLast+1) + " from " + allStudents.size()) + "  ");
            } else {
                students = new ArrayList<Student>(allStudents.subList(numNewFirst, numNewLast+1));
                statusPage.setText("  " + (numNewFirst+1) + " - " + (numNewLast+1) + " from " + allStudents.size() + "  ");
            }
            //tableModel
            tableModel.setStudents(students);
            changeNumPage();
            repaint();
        }
    }

    private class ToRightPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(allStudents.size() == 0) return;
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = allStudents.indexOf(firstOnPage);
            int numLastInDB = allStudents.indexOf(lastOnPage);

            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }

            if (numLastInDB == allStudents.size() - 1) {
                return;
            }

            int numNewFirst = numLastInDB + 1;
            int numNewLast = numLastInDB + numEntry;
            if (numNewLast > allStudents.size()) {
                numNewLast = allStudents.size() - 1;
            }

            if (numNewFirst == numNewLast) {
                students = new ArrayList<Student>();
                students.add(allStudents.get(numNewLast));
                statusPage.setText("  " + String.valueOf((numNewLast + 1) + " from " + allStudents.size()) + "  ");
            } else {
                students = new ArrayList<Student>(allStudents.subList(numNewFirst, numNewLast + 1));
                statusPage.setText("  " + (numNewFirst + 1) + " - " + (numNewLast + 1) + " from " + allStudents.size() + "  ");
            }
            //tableModel
            tableModel.setStudents(students);
            changeNumPage();
            repaint();
        }
    }

    private class LastPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(allStudents.size() == 0){
                return;
            }
            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }
            ArrayList<Student> cStudent = allStudents;
            students = new ArrayList(cStudent.subList(cStudent.size() - numEntry, cStudent.size()));
            statusPage.setText("  " + (cStudent.size() - numEntry + 1) + " - " + (cStudent.size()) +
                    " from " + allStudents.size() + "  ");
            tableModel.setStudents(students);
            changeNumPage();
            repaint();
        }
    }

    private class FirstPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(allStudents.size() == 0){
                return;
            }
            if (inNumEntries.getText().isEmpty()) {
                numEntry = students.size();
            } else {
                numEntry = Integer.parseInt(inNumEntries.getText());
            }
            ArrayList<Student> cStudent = allStudents;
            students = new ArrayList(cStudent.subList(0, numEntry));
            statusPage.setText("  1" + " - " + (students.size()) +
                    " from " + allStudents.size() + "  ");
            tableModel.setStudents(students);
            changeNumPage();
            repaint();
        }
    }

    public void changeNumPage(){
        if(tableModel.getStudents().size() == 0) return;
        int numPage;
        int curPage;
        int allStudent;
        allStudent = allStudents.size();
        numPage = (int)Math.ceil((double)allStudent/numEntry);
        if(numPage == 1){
            numPageLabel.setText("  page: 1/1  ");
            return;
        }
        int numFirstOnPage = allStudents.indexOf(tableModel.getStudents().get(0));
        curPage = numFirstOnPage/numEntry;
        if(numEntry == 1){
            numPageLabel.setText("  page: " + numFirstOnPage + "/" + allStudent + "  ");
            return;
        }
        numPageLabel.setText("  page: " + (curPage + 1) + "/" + numPage + "  ");
    }

    public void refresh(){
        if(numEntry == 0) {
            numEntry = tableModel.getStudents().size();
        }
        if(numEntry > allStudents.size()){
            numEntry = allStudents.size();
        }
        if(tableModel.getStudents().size() > allStudents.size()){
            tableModel.setStudents(allStudents);
        }
        tableModel.setStudents(students);
        statusPage.setText("  1 - " + numEntry + " from " + allStudents.size() + "  ");
        changeNumPage();
        repaint();
    }

    public void refresh(ArrayList<Student> students, ArrayList<Student> allStudents){
        this.numEntry = students.size();
        this.students = students;
        this.allStudents = allStudents;
        tableModel.setStudents(students);
        statusPage.setText("  1 - " + numEntry + " from " + allStudents.size() + "  ");
        changeNumPage();
        repaint();
    }
}
