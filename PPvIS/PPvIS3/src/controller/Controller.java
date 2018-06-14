package controller;

import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Coordinate> coordinates;

    public Controller(){
        coordinates  = new ArrayList<>();
        generateCoordinates();
    }

    private void generateCoordinates(){
        double h = 0.01;
        double x = 0;
        Coordinate coordinate;
        while(x <= 1.75) {
            coordinate = new Coordinate(x);
            coordinates.add(coordinate);
            x += h;
            x = Coordinate.round(x, 2);
        }
    }

    public List<Coordinate> getCoordinates(){
        return new ArrayList<>(coordinates);
    }
}
