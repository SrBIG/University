package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Viev.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DialogStart extends JDialog {
    String fileName;
    File file;
    JTextField inputFile = new JTextField();
    JButton button = new JButton("Connect");

    public DialogStart(){
        setName("Database");
        add(new JLabel("Enter file with DB name"), BorderLayout.NORTH);
        add(inputFile, BorderLayout.CENTER);
        button.addActionListener(new AddDBListener());
        add(button, BorderLayout.SOUTH);

        setSize(300, 80);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class AddDBListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            fileName = inputFile.getText().trim();
            if(fileName.isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter file name");
                return;
            }

            fileName = "DB/" + fileName;
            file = new File(fileName);
            if(file.exists() == false){
                JOptionPane.showMessageDialog(null, "File not found");
                return;
            }
            setVisible(false);
            new MainFrame(fileName);
        }
    }

}
