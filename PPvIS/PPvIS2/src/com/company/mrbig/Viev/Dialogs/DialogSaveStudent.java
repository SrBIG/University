package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Model.Student;
import com.company.mrbig.Model.WriterXML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogSaveStudent extends JDialog {
    ArrayList<Student> students;
    JTextField inputFile = new JTextField();
    JButton save = new JButton("Save");

    public DialogSaveStudent(ArrayList<Student> students){
        this.students = students;
        setName("Save in file");
        add(new JLabel("Enter file for save info"), BorderLayout.NORTH);
        add(inputFile, BorderLayout.CENTER);
        save.addActionListener(new SaveListener());
        add(save, BorderLayout.SOUTH);

        setSize(300, 80);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    class SaveListener implements ActionListener{
        String file;
        public void actionPerformed(ActionEvent actionEvent) {
            file = inputFile.getText().trim();
            if(file.isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter file name");
                return;
            }
            write();
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Success");
        }

        private void write(){
            file = "DB/" + file;
            WriterXML writer = new WriterXML(students,file);
            writer.write();
        }
    }
}
