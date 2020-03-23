package containers;

import java.util.ArrayList;

public class DataContainer {
    private double[] allX = {1.14, 1.37, 1.89, 2.09, 2.45, 2.4, 2.73, 3.04, 3.19, 3.09, 3.05, 3.10, 3.34, 3.75, 4.19, 4.59};
    private double[] allY = {112, 115, 152, 199, 161, 209, 237, 231, 233, 259, 287, 240, 281, 311, 392, 357};
    private ArrayList<Double> allYFE;

    private Double errorFunktion;

    public double[] getAllX() {
        return allX;
    }

    public double[] getAllY() {
        return allY;
    }

    public Double getSredX() {
        double summX = 0;
        for (double x : allX) summX = summX + x;
        double sredX = summX / allX.length;
        return sredX;
    }

    public ArrayList<Double> getAllYFE() {
        return allYFE;
    }

    public void setAllYFE(ArrayList<Double> allYFE) {
        this.allYFE = allYFE;
    }

    public Double getErrorFunktion() {
        return errorFunktion;
    }

    public void setErrorFunktion(Double errorFunktion) {
        this.errorFunktion = errorFunktion;
    }
}