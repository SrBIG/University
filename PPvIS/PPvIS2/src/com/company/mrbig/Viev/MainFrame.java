package com.company.mrbig.Viev;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Dialogs.DialogAddStudent;
import com.company.mrbig.Viev.Dialogs.DialogDeleteStudent;
import com.company.mrbig.Viev.Dialogs.DialogFindStudent;
import com.company.mrbig.Viev.Dialogs.DialogSaveStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    Controler controler;
    StudentTableModel tableModel;
    ArrayList<Student> students;

    JButton addStudent = new JButton("Add");
    JButton findStudent = new JButton("Find");
    JButton deleteStudent = new JButton("Delete");
    JButton save = new JButton("Save");

    JPanel all = new JPanel();
    JPanel menu  = new JPanel();

    Display display;

    public MainFrame(String file) {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controler = new Controler(file);

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
        save.addActionListener(new SaveListener());
        menu.add(save);

        all.add(menu);
        all.add(new JScrollPane(display));

        getContentPane().add(all);

        setSize(new Dimension(800, 400));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    class AddStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogAddStudent(controler);
            display.refresh();
        }
    }

    class FindStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogFindStudent(controler);
            display.refresh();
        }
    }

    class DeleteStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogDeleteStudent(controler);
            display.refresh();
        }
    }

    class SaveListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogSaveStudent(controler.getStudents());
        }
    }
}
