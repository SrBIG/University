package com.company.mrbig.Viev;

import com.company.mrbig.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Viev extends JFrame {
    static int i = 0;
    JButton button = new JButton("Press");

    public Viev(Model model) {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(model);
        button.addActionListener(new PressListener());
        getContentPane().add(new JScrollPane(table));
        //getContentPane().add(button);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class PressListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
