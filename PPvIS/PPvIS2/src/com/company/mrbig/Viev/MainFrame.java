package com.company.mrbig.Viev;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Dialogs.DialogAddStudent;
import com.company.mrbig.Viev.Dialogs.DialogFindStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    Controler controler = new Controler();
    StudentTableModel tableModel;
    ArrayList<Student> students;

    JTable table;
    JButton buttonNumEntries = new JButton("Press");

    JButton toLeftPage = new JButton("<");
    JButton toRightPage = new JButton(">");
    JTextField inNumEntries = new JTextField(10);
    JLabel statusPage = new JLabel();

    JButton addStudent = new JButton("Add");
    JButton findStudent = new JButton("Find");

    JPanel all = new JPanel();
    JPanel menu  = new JPanel();
    JPanel status = new JPanel();


    public MainFrame() {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        status.setLayout(new BoxLayout(status, BoxLayout.X_AXIS));
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        students = controler.getStudents();
        tableModel = new StudentTableModel(students);
        table = new JTable(tableModel);

        addStudent.addActionListener(new AddStudentListener());
        menu.add(addStudent);
        findStudent.addActionListener(new FindStudentListener());
        menu.add(findStudent);

        buttonNumEntries.addActionListener(new NumEntriesListener());
        inNumEntries.setMaximumSize(new Dimension(40,25));
        menu.add(inNumEntries);
        menu.add(buttonNumEntries);

        toLeftPage.addActionListener(new ToLeftPage());
        toRightPage.addActionListener(new ToRightPage());
        statusPage.setText("1 - " + students.size());
        status.add(toLeftPage);
        status.add(statusPage);
        status.add(toRightPage);

        all.add(menu);
        all.add(table);
        all.add(status);

        getContentPane().add(new JScrollPane(all));

        setPreferredSize(new Dimension(900, 400));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public class AddStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogAddStudent(controler);
            tableModel.setStudents(controler.getStudents());
            statusPage.setText("  1 - " + controler.getStudents().size() + "  ");
            getContentPane().repaint();
        }
    }

    public class FindStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            DialogFindStudent find = new DialogFindStudent(controler);
            students = find.showResult();
            tableModel.setStudents(students);
            getContentPane().repaint();
        }
    }

    private class NumEntriesListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            int numEntry;
            try {
                numEntry = Integer.parseInt(inNumEntries.getText());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please, enter the correct value.");
                return;
            }
            if(numEntry > controler.getStudents().size()){
                numEntry = controler.getStudents().size();
            }
            students = new ArrayList<Student>(controler.getStudents().subList(0,numEntry));
            tableModel.setStudents(students);
            statusPage.setText("  1 - " + numEntry + " from " + controler.getStudents().size() + "  ");
            getContentPane().repaint();
        }
    }

    private class ToLeftPage implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = controler.getStudents().indexOf(firstOnPage);
            int numLastInDB = controler.getStudents().indexOf(lastOnPage);

            int numEntry;
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
                statusPage.setText(String.valueOf((numNewLast+1) + " from " + controler.getStudents().size()));
            } else {
                students = new ArrayList<Student>(controler.getStudents().subList(numNewFirst, numNewLast+1));
                statusPage.setText((numNewFirst+1) + " - " + (numNewLast+1) + " from " + controler.getStudents().size());
            }

            tableModel.setStudents(students);
            getContentPane().repaint();
        }
    }

    private class ToRightPage implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {
            Student firstOnPage = students.get(0);
            Student lastOnPage = students.get(students.size() - 1);
            int numFirstInDB = controler.getStudents().indexOf(firstOnPage);
            int numLastInDB = controler.getStudents().indexOf(lastOnPage);

            int numEntry;
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
                statusPage.setText(String.valueOf((numNewLast+1) + " from " + controler.getStudents().size()));
            } else {
                students = new ArrayList<Student>(controler.getStudents().subList(numNewFirst, numNewLast+1));
                statusPage.setText((numNewFirst+1) + " - " + (numNewLast+1) + " from " + controler.getStudents().size());
            }

            tableModel.setStudents(students);
            getContentPane().repaint();
        }
    }
}
