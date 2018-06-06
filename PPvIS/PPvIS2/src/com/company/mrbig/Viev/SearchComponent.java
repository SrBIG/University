package com.company.mrbig.Viev;

import com.company.mrbig.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class SearchComponent extends JPanel{
    ArrayList<Student> allStudents;
    ArrayList<Student> students;

    JCheckBox familySizeCB = new JCheckBox("Размер семьи");
    JCheckBox secondnameCB = new JCheckBox("Фамилия");
    JCheckBox livingSquareCB = new JCheckBox("Жилая площадь");
    JCheckBox onePersonSquareCB = new JCheckBox("Площадь на одного человека");

    JTextField sFamilySize = new JTextField();
    JTextField sSecondname = new JTextField();
    JTextField sLivingSquare = new JTextField();
    JTextField sOnePersonSquareMIN = new JTextField();
    JTextField sOnePersonSquareMAX = new JTextField();

    JPanel choiceSearch = new JPanel();
    JPanel searchFields = new JPanel();
    JPanel checkBox = new JPanel();
    JPanel areaForPerson = new JPanel();
    JPanel all = new JPanel();

    public SearchComponent(ArrayList<Student> allStudents){
        students = new ArrayList<>(allStudents);
        createChoiceBox();
        createSearchFields();

        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        all.add(choiceSearch);
        all.add(searchFields);
        add(all);
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

    public JCheckBox getFamilySizeCB(){
        return familySizeCB;
    }
    public JCheckBox getSecondnameCB(){
        return secondnameCB;
    }
    public JCheckBox getLivingSquareCB(){
        return livingSquareCB;
    }
    public JCheckBox getOnePersonSquareCB(){
        return onePersonSquareCB;
    }
    public JTextField getSFamilySize(){
        return sFamilySize;
    }
    public JTextField getSSecondname(){
        return sSecondname;
    }
    public JTextField getSLivingSquare(){
        return sLivingSquare;
    }
    public JTextField getSOnePersonSquareMIN(){
        return sOnePersonSquareMIN;
    }
    public JTextField getSOnePersonSquareMAX(){
        return sOnePersonSquareMAX;
    }
}
