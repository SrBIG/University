package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Viev.MainFrame;
import com.sun.org.apache.xml.internal.resolver.helpers.BootstrapResolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DialogStart extends JDialog {
    String fileName;
    JLabel info = new JLabel("Please, select action:");
    JButton connect = new JButton("Connect");
    JButton create = new JButton("Create new file");

    JPanel actions = new JPanel();

    public DialogStart(){
        setName("Database");
        info.setFont(new Font("Sans-serif", Font.BOLD, 18));
        add(info, BorderLayout.NORTH);

        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));

        connect.addActionListener(new AddDBListener());
        add(connect, BorderLayout.SOUTH);
        create.addActionListener(new CreateListener());
        add(create, BorderLayout.CENTER);

        //add(actions, BorderLayout.CENTER);

        setSize(300, 80);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    class AddDBListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser fileChooser = new JFileChooser();
            int res = fileChooser.showDialog(null, "Open file");

            if(res == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                fileName = file.getPath();
            }
            setVisible(false);
            new MainFrame(fileName);
        }
    }

    private class CreateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisible(false);
            new MainFrame(null);
        }
    }

}
