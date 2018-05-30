package com.company.mrbig.Viev.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogStart extends JDialog {
    String file;
    JTextField inputFile = new JTextField();
    JButton button = new JButton("Подключить");

    JPanel all = new JPanel();

    public DialogStart(){
        setName("Database");
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Input name file with DB"), BorderLayout.NORTH);
        add(inputFile, BorderLayout.CENTER);
        button.addActionListener(new AddDBListener());
        add(button, BorderLayout.SOUTH);

        setSize(300, 60);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class AddDBListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(inputFile.getText().trim().isEmpty()){
                
            }
        }
    }

}
