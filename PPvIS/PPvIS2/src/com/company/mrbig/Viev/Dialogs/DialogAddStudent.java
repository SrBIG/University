package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAddStudent extends JDialog {
    Controler controler;

    Dimension dimension = new Dimension(500,35);
    Dimension minDimension = new Dimension(10, 30);

    JTextField firstname = new JTextField();
    JTextField secondname = new JTextField();
    JTextField patronymic = new JTextField();
    JTextField street = new JTextField();
    JTextField home = new JTextField();
    JTextField flat = new JTextField();
    JTextField familySize = new JTextField();
    JTextField livingSqr = new JTextField();

    JButton butAddStudent = new JButton("Add");
    JButton butExit = new JButton("Exit");

    JPanel info = new JPanel();
    JPanel buttons = new JPanel();

    public DialogAddStudent(Controler controler){
        this.controler = controler;
        this.setName("Add student");
        this.setModal(true);
        setDimension();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new JLabel("Firstname: "));
        info.add(firstname);
        info.add(new JLabel("Secondname: "));
        info.add(secondname);
        info.add(new JLabel("Patronymic: "));
        info.add(patronymic);
        info.add(new JLabel("Street: "));
        info.add(street);
        info.add(new JLabel("Number of home: "));
        info.add(home);
        info.add(new JLabel("Number of flat: "));
        info.add(flat);
        info.add(new JLabel("Family size: "));
        info.add(familySize);
        info.add(new JLabel("Livings square: "));
        info.add(livingSqr);

        butAddStudent.addActionListener(new AddListener());
        butExit.addActionListener(new ExitListener());
        buttons.add(butAddStudent);
        buttons.add(butExit);

        add(info, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        setSize(250, 500);
        setVisible(true);
    }

    private class AddListener implements ActionListener{
        Student addStud;

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
            try {
                addStud.setFirstname(firstname.getText());
                addStud.setSecondname(secondname.getText());
                addStud.setPatronymic(patronymic.getText());
                addStud.setStreet(street.getText());
                addStud.setHome(home.getText());
                addStud.setFlat(flat.getText());
                addStud.setFamilySize(Integer.parseInt(familySize.getText()));
                addStud.setLivingSquare(Double.parseDouble(livingSqr.getText()));
                addStud.setOnePersonSquare(
                        addStud.getLivingSquare() / addStud.getFamilySize());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Please, enter correct data");
                return;
            }

            controler.addStudent(addStud);
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

    private void setDimension(){
        firstname.setMaximumSize(dimension);
        secondname.setMaximumSize(dimension);
        patronymic.setMaximumSize(dimension);
        street.setMaximumSize(dimension);
        home.setMaximumSize(dimension);
        flat.setMaximumSize(dimension);
        familySize.setMaximumSize(dimension);
        livingSqr.setMaximumSize(dimension);

        firstname.setPreferredSize(minDimension);
        secondname.setPreferredSize(minDimension);
        patronymic.setPreferredSize(minDimension);
        street.setPreferredSize(minDimension);
        home.setPreferredSize(minDimension);
        flat.setPreferredSize(minDimension);
        familySize.setPreferredSize(minDimension);
        livingSqr.setPreferredSize(minDimension);
    }
}
