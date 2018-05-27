package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDeleteStudent extends DialogFindStudent {
    JButton delete;
    public DialogDeleteStudent(Controler controler) {
        super(controler);
    }

    @Override
    protected void paintComponent(){
        setName("Delete student");
        delete = new JButton("Delete");
        delete.addActionListener(new DeleteStudent());

        action.setLayout(new BoxLayout(action, BoxLayout.X_AXIS));
        action.add(delete);

        all.add(choiceSearch);
        all.add(searchFields);
        all.add(action);
        add(all);
        add(new JScrollPane(display), BorderLayout.CENTER);

        setSize(410, 600);
        setVisible(true);
    }

    class DeleteStudent implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            deleteListStudent();
            setVisible(false);
        }
        private void deleteListStudent(){
            controler.deleteStudents(students);
        }
    }
}
