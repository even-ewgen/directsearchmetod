package containers;

import java.util.ArrayList;

public class DataContainer {
    private static double[] allX = {1.14, 1.37, 1.89, 2.09, 2.45, 2.4, 2.73, 3.04, 3.19, 3.09, 3.05, 3.10, 3.34, 3.75, 4.19, 4.59};
    private static double[] allY = {112, 115, 152, 199, 161, 209, 237, 231, 233, 259, 287, 240, 281, 311, 392, 357};
    private static ArrayList<Double> allYFE;

    private Double errorFunktion;

    public static double[] getAllX() {
        return allX;
    }

    public static double[] getAllY() {
        return allY;
    }

    public static ArrayList<Double> getAllYFE() {
        return allYFE;
    }

    public void setAllYFE(ArrayList<Double> allYFE) {
        this.allYFE = allYFE;
    }

    public void printYAndNewY(){
        System.out.printf("%15sСравнение значений Y\n", "");
        for (int i = 0; i < allY.length; i++) {
            System.out.printf("Y-заданное -> %f  %f <- Y-полученное\n", allY[i], allYFE.get(i));
        }
    }
}
