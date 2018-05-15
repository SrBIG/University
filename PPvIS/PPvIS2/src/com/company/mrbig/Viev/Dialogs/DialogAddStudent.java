package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAddStudent extends JDialog {
    Student addStud;
    ArrayList<Student> students = new ArrayList<Student>();

    JTextField firstname = new JTextField();
    JTextField secondname = new JTextField();
    JTextField patronymic = new JTextField();
    JTextField street = new JTextField();
    JTextField home = new JTextField();
    JTextField flat = new JTextField();
    JTextField familySize = new JTextField();
    JTextField livingSqr = new JTextField();

    JButton butAddStudent = new JButton();
    JButton butExit = new JButton();

    JPanel buttons = new JPanel();

    public DialogAddStudent(){
        this.setName("Add student");
        this.setModal(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Firstname: "));
        add(firstname);
        add(new JLabel("Secondname: "));
        add(secondname);
        add(new JLabel("Patronymic: "));
        add(patronymic);
        add(new JLabel("Street: "));
        add(street);
        add(new JLabel("Number of home: "));
        add(home);
        add(new JLabel("Number of flat: "));
        add(flat);
        add(new JLabel("Family size: "));
        add(familySize);
        add(new JLabel("Livings square: "));
        add(livingSqr);

        butAddStudent.addActionListener(new AddListener());
        butExit.addActionListener(new ExitListener());
        add(butAddStudent, BorderLayout.SOUTH);
    }

    private class AddListener implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {
            addStud = new Student();
            if(firstname.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(secondname.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(patronymic.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(street.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(home.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(flat.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(familySize.getText().trim().isEmpty()){
                addStud = null;
                return;
            }
            if(livingSqr.getText().trim().isEmpty()){
                addStud = null;
                return;
            }

            addStud.setFirstname(firstname.getText());
            addStud.setSecondname(secondname.getText());
            addStud.setPatronymic(patronymic.getText());
            addStud.setStreet(street.getText());
            addStud.setHome(home.getText());
            addStud.setFlat(flat.getText());
            addStud.setFamilySize(Integer.parseInt(familySize.getText()));
            addStud.setLivingSquare(Double.parseDouble(livingSqr.getText()));
            addStud.setOnePersonSquare(
                    addStud.getLivingSquare()/addStud.getFamilySize()
            );

            students.add(addStud);
            addStud = null;

            firstname.setText("");
            secondname.setText("");
            patronymic.setText("");
            street.setText("");
            home.setText("");
            flat.setText("");
            familySize.setText("");
            livingSqr.setText("");
        }
    }

    private class ExitListener implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {
            setVisible(false);
        }
    }

    public ArrayList<Student> getResult(){
        return students;
    }
}
