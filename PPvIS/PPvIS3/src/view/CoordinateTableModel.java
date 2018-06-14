package view;

import model.Coordinate;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoordinateTableModel extends AbstractTableModel {
    private List<Coordinate> coordinate;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public CoordinateTableModel(List<Coordinate> coordinate){
        this.coordinate = coordinate;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "X";
            case 1:
                return "F(X)";
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return coordinate.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        double cX = coordinate.get(rowIndex).getX();
        double cY = coordinate.get(rowIndex).getY();
        switch (columnIndex) {
            case 0:
                return cX;
            case 1:
                return cY;
            default:
                return "Error";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public List<Coordinate> getCoordinate(){
        return coordinate;
    }

    public void setCoordinate(List<Coordinate> coordinate){
        this.coordinate = coordinate;
    }
}
