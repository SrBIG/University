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
        add(all);
        add(delete, BorderLayout.SOUTH);
        pack();
        //add(new JScrollPane(display), BorderLayout.CENTER);
        setVisible(true);
    }

    class DeleteStudent extends SearchListener {
        public void actionPerformed(ActionEvent actionEvent) {
            checkParametr();
            search();
            showResult();
            students = tableModel.getStudents();
            deleteListStudent();
            setVisible(false);
        }
        private void deleteListStudent(){
            if(students.size() == 0){
                JOptionPane.showMessageDialog(null, "Nothing was deleted");
                return;
            }
            controler.deleteStudents(students);
            JOptionPane.showMessageDialog(null, "Deleted " + students.size() + " student(s)");
        }
    }
}
