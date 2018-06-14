package view;

import controller.Controller;
import model.Coordinate;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame {
    private JFrame frame = new JFrame("Graphic");

    Controller controller;

    JTable table;
    CoordinateTableModel tableModel;

    JPanel all = new JPanel();
    JPanel info = new JPanel();
    JPanel  graphic;

    public MainFrame(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        all.setLayout(new BoxLayout(all, BoxLayout.X_AXIS));

        controller = new Controller();
        tableModel = new CoordinateTableModel(controller.getCoordinates());
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        table.getColumnModel().getColumn(1).setMaxWidth(70);

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new JLabel("Coordinates:"));
        info.add(new JScrollPane(table));
        info.setMaximumSize(new Dimension(50, 600));
        all.add(info);
        graphic = new Graphic(controller.getCoordinates());
        all.add(graphic);
        frame.add(all);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
