package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Viev.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDeleteStudent extends DialogFindStudent {
    JButton delete = new JButton("delete");
    public DialogDeleteStudent(Controler controler) {
        super(controler);
    }

    protected class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            checkParametr();
            search();
            showResult();
        }
    }
}
