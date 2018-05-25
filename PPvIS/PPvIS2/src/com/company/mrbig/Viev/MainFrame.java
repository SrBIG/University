package com.company.mrbig.Viev;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Dialogs.DialogAddStudent;
import com.company.mrbig.Viev.Dialogs.DialogDeleteStudent;
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

    JButton addStudent = new JButton("Add");
    JButton findStudent = new JButton("Find");
    JButton deleteStudent = new JButton("Delete");

    JPanel all = new JPanel();
    JPanel menu  = new JPanel();

    Display display;

    public MainFrame() {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        students = controler.getStudents();
        tableModel = new StudentTableModel(students);
        display = new Display(tableModel, controler);

        addStudent.addActionListener(new AddStudentListener());
        menu.add(addStudent);
        findStudent.addActionListener(new FindStudentListener());
        menu.add(findStudent);
        deleteStudent.addActionListener(new DeleteStudentListener());
        menu.add(deleteStudent);

        all.add(menu);
        all.add(new JScrollPane(display));

        getContentPane().add(all);

        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public class AddStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogAddStudent(controler);
            //statusPage.setText("  1 - " + controler.getStudents().size() + "  ");
            display.refresh();
        }
    }

    public class FindStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogFindStudent(controler);
        }
    }

    public class DeleteStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogDeleteStudent(controler);
        }
    }
}
