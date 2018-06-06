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
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    static Controler controler;
    StudentTableModel tableModel;
    ArrayList<Student> students;

    JButton addStudent = new JButton("Add");
    JButton findStudent = new JButton("Find");
    JButton deleteStudent = new JButton("Delete");
    JButton save = new JButton("Save");

    JPanel all = new JPanel();
    JPanel menu  = new JPanel();

    static Display display;

    public MainFrame(String file) {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controler = new Controler(file);

        addMenuBar();

        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        students = controler.getStudents();
        tableModel = new StudentTableModel(students);
        display = new Display(tableModel, controler.getStudents());

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

    void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu actionMenu = new JMenu("Action");
        JMenu fileMenu = new JMenu("File");

        JMenuItem addStudent = new JMenuItem("Add");
        addStudent.addActionListener(new AddStudentListener());
        JMenuItem findStudent = new JMenuItem("Find");
        findStudent.addActionListener(new FindStudentListener());
        JMenuItem deleteStudent = new JMenuItem("Delete");
        deleteStudent.addActionListener(new DeleteStudentListener());
        actionMenu.add(addStudent);
        actionMenu.add(findStudent);
        actionMenu.add(deleteStudent);

        JMenuItem save = new JMenuItem("Save as");
        save.addActionListener(new SaveListener());
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new OpenFileListener());
        fileMenu.add(save);
        fileMenu.add(open);

        menuBar.add(fileMenu);
        menuBar.add(actionMenu);

        setJMenuBar(menuBar);
    }

    public static class AddStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogAddStudent(controler);
            display.refresh(controler.getStudents(),controler.getStudents());
        }
    }

    static class FindStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogFindStudent(controler.getStudents());
            display.refresh();
        }
    }

    static class DeleteStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogDeleteStudent(controler.getStudents(), controler);
            display.refresh(controler.getStudents(), controler.getStudents());
        }
    }

    static class SaveListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            new DialogSaveStudent(controler.getStudents());
        }
    }

    static class OpenFileListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser fileChooser = new JFileChooser();
            int res = fileChooser.showDialog(null, "Open file");
            String fileName;

            if(res == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                fileName = file.getPath();
            } else{ return; }

            controler.setFile(fileName);
            display.refresh(controler.getStudents(), controler.getStudents());
        }
    }
}
