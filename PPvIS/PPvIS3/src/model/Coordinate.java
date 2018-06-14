package model;

public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(double x){
        setX(x);
    }

    public double calculetedY(double x){
        double y = 0;
        int k = 0;
        while(true){
            double step = 0;
            step = (Math.pow(-1, k) * Math.pow(x, 2*k)) / (double)factorial(2*k);
            if(round(y, 6) == round(step + y, 6)){
                return round(y, 4);
            }
            y += step;
            k++;
        }
    }

    public int factorial(int k){
        int res = 1;
        for(int i = 1; i <= k; i++){
            res = res * i;
        }
        return res;
    }

    public static double round(double dig, int power){
        dig *= Math.pow(10, power);
        int magic = (int) Math.round(dig);
        dig = (double)magic/Math.pow(10, power);
        return dig;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.y = calculetedY(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
