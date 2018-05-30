package com.company.mrbig.Viev.Dialogs;

import com.company.mrbig.Controler.Controler;
import com.company.mrbig.Model.Student;
import com.company.mrbig.Viev.Display;
import com.company.mrbig.Viev.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class DialogFindStudent extends JDialog{
    Controler controler;
    ArrayList<Student> students;
    StudentTableModel tableModel;
    Display display;

    JCheckBox familySizeCB = new JCheckBox("Размер семьи");
    JCheckBox secondnameCB = new JCheckBox("Фамилия");
    JCheckBox livingSquareCB = new JCheckBox("Жилая площадь");
    JCheckBox onePersonSquareCB = new JCheckBox("Площадь на одного человека");

    JTextField sFamilySize = new JTextField();
    JTextField sSecondname = new JTextField();
    JTextField sLivingSquare = new JTextField();
    JTextField sOnePersonSquareMIN = new JTextField();
    JTextField sOnePersonSquareMAX = new JTextField();

    JButton searchStudent = new JButton("Search");

    JPanel choiceSearch = new JPanel();
    JPanel searchFields = new JPanel();
    JPanel checkBox = new JPanel();
    JPanel areaForPerson = new JPanel();
    JPanel all = new JPanel();
    JPanel action = new JPanel();

    public DialogFindStudent(Controler controler){
        this.controler = controler;
        this.students = controler.getStudents();
        tableModel  = new StudentTableModel(students);
        display = new Display(tableModel, controler);

        setModal(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        createChoiceBox();
        createSearchFields();
        searchStudent.addActionListener(new SearchListener());
        action.add(searchStudent);

        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        paintComponent();
    }

    protected void paintComponent(){
        setName("Search for students");

        all.add(choiceSearch);
        all.add(searchFields);
        all.add(action);
        add(all);
        add(new JScrollPane(display), BorderLayout.CENTER);

        setSize(410, 600);
        setVisible(true);
    }

    protected void createChoiceBox(){
        choiceSearch.setLayout(new BoxLayout(choiceSearch, BoxLayout.Y_AXIS));
        choiceSearch.setAlignmentX(Component.LEFT_ALIGNMENT);
        choiceSearch.add(new JLabel("Choose search criteria:"));

        familySizeCB.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                sFamilySize.setEditable(true);
            } else {
                sFamilySize.setEditable(false);
            }
        });
        secondnameCB.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                sSecondname.setEditable(true);
            } else {
                sSecondname.setEditable(false);
            }
        });
        livingSquareCB.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                sLivingSquare.setEditable(true);
            } else {
                sLivingSquare.setEditable(false);
            }
        });
        onePersonSquareCB.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                sOnePersonSquareMIN.setEditable(true);
                sOnePersonSquareMAX.setEditable(true);
            } else {
                sOnePersonSquareMIN.setEditable(false);
                sOnePersonSquareMAX.setEditable(false);
            }
        });

        checkBox.setLayout(new BoxLayout(checkBox, BoxLayout.Y_AXIS));
        checkBox.add(familySizeCB);
        checkBox.add(secondnameCB);
        checkBox.add(livingSquareCB);
        checkBox.add(onePersonSquareCB);
        choiceSearch.add(checkBox);
    }

    protected void createSearchFields(){
        Dimension dimension = new Dimension(500,35);
        Dimension dim = new Dimension(100, 35);
        sFamilySize.setMaximumSize(dimension);
        sSecondname.setMaximumSize(dimension);
        sLivingSquare.setMaximumSize(dimension);
        sOnePersonSquareMIN.setMaximumSize(dim);
        sOnePersonSquareMAX.setMaximumSize(dim);

        sFamilySize.setEditable(false);
        sSecondname.setEditable(false);
        sLivingSquare.setEditable(false);
        sOnePersonSquareMIN.setEditable(false);
        sOnePersonSquareMAX.setEditable(false);

        searchFields.setLayout(new BoxLayout(searchFields, BoxLayout.Y_AXIS));
        searchFields.add(new JLabel("Family size"));
        searchFields.add(sFamilySize);
        searchFields.add(new JLabel("Second name"));
        searchFields.add(sSecondname);
        searchFields.add(new JLabel("Living square"));
        searchFields.add(sLivingSquare);
        searchFields.add(new JLabel("Area/person"));

        areaForPerson.setLayout(new BoxLayout(areaForPerson, BoxLayout.X_AXIS));
        areaForPerson.add(new JLabel("min: "));
        areaForPerson.add(sOnePersonSquareMIN);
        areaForPerson.add(new JLabel("max: "));
        areaForPerson.add(sOnePersonSquareMAX);
        searchFields.add(areaForPerson);
    }

    protected class SearchListener implements ActionListener{
        String secondname;
        int familySize = -1;
        double livingSquare = -1;
        double onePersonSquareMIN = -1;
        double onePersonSquareMAX = -1;
        public void actionPerformed(ActionEvent actionEvent) {
            checkParametr();
            search();
            showResult();
            students = controler.getStudents();
        }

        protected void checkParametr(){
            if(familySizeCB.isSelected() && sFamilySize.getText().trim().isEmpty() == false){
                familySize = Integer.parseInt(sFamilySize.getText());
            }
            if(secondnameCB.isSelected() && sSecondname.getText().trim().isEmpty() == false){
                secondname = sSecondname.getText();
            }
            if(livingSquareCB.isSelected() && sLivingSquare.getText().trim().isEmpty() == false){
                livingSquare = Double.parseDouble(sLivingSquare.getText());
            }
            if(onePersonSquareCB.isSelected() &&
                    sOnePersonSquareMAX.getText().trim().isEmpty() == false && sOnePersonSquareMIN.getText().trim().isEmpty() == false){
                onePersonSquareMIN = Double.parseDouble(sOnePersonSquareMIN.getText());
                onePersonSquareMAX = Double.parseDouble(sOnePersonSquareMAX.getText());
            }
        }

        protected void search() {
            if (familySize != -1) {
                students.removeIf(student -> student.getFamilySize() != familySize);
            }
            if (secondname != null) {
                students.removeIf(student -> student.getSecondname() != secondname);
            }
            if (livingSquare != -1.0) {
                students.removeIf(student -> student.getLivingSquare() != livingSquare);
            }
            if (onePersonSquareMIN != -1.0 && onePersonSquareMAX != -1.0) {
                students.removeIf(student -> student.getOnePersonSquare() < onePersonSquareMIN);
                students.removeIf(student -> student.getOnePersonSquare() > onePersonSquareMAX);
            }
            //System.out.println("Количество студентов всегоS: " + students.size());
        }
        protected void showResult(){
            //tableModel.setStudents(students);
            System.out.println("Количество студентов всего: " + students.size());
            display.refresh();
        }
    }
}

