package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

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
        table.getColumnModel().getColumn(0).setMaxWidth(110);
        table.getColumnModel().getColumn(1).setMaxWidth(110);

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new JLabel("Coordinates:"));
        info.add(new JScrollPane(table));
        info.setMaximumSize(new Dimension(100, 600));
        all.add(info);
        graphic = new Graphic(controller.getCoordinates());
        all.add(graphic);
        frame.add(all);
        frame.setSize(650, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}
