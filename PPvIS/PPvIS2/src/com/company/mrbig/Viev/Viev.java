package com.company.mrbig.Viev;

import com.company.mrbig.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Viev extends JFrame {
    static int i = 0;
    JButton button = new JButton("Press");
    // Панель с горизонтальным расположением компонентов
    JPanel menu  = new JPanel();
    JPanel all = new JPanel();

    public Viev(Model model) {
        super("Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(model);
        button.addActionListener(new PressListener());

        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        menu.add(button);

        all.add(menu);
        all.add(new JScrollPane(table));
        getContentPane().add(all, "North");


        //getContentPane().add(button);

        setPreferredSize(new Dimension(900, 400));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class PressListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
