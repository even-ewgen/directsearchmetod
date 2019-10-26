import java.util.ArrayList;

public class DataContainer {
    private double[] allX = {1.14, 1.37, 1.89, 2.09, 2.45, 2.4, 2.73, 3.04, 3.19, 3.09, 3.05, 3.10, 3.34, 3.75, 4.19, 4.59};
    private double[] allY = {112, 115, 152, 199, 161, 209, 237, 231, 233, 259, 287, 240, 281, 311, 392, 357};
    private ArrayList<Double> allYFE;
    private ArrayList<Double> allYAFE;

    private Double errorFunktion;
    private Double errorFunktionB0;
    private Double errorFunktionB1;
    private Double AccurateErrorFunktion;
    private Double AccurateErrorFunktionB0;
    private Double AccurateErrorFunktionB1;

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

    public Double getSredY() {
        double summY = 0;
        for (double y : allY) summY = summY + y;
        double sredY = summY / allY.length;
        return sredY;
    }

    public Double getSred(double[] allS) {
        double summS = 0;
        for (double s : allY) summS = summS + s;
        double sredS = summS / allS.length;
        return sredS;
    }

    public ArrayList<Double> getAllYFE() {
        return allYFE;
    }

    public void setAllYFE(ArrayList<Double> allYFE) {
        this.allYFE = allYFE;
    }

    public ArrayList<Double> getAllYAFE() {
        return allYAFE;
    }

    public void setAllYAFE(ArrayList<Double> allYAFE) {
        this.allYAFE = allYAFE;
    }

    public Double getErrorFunktion() {
        return errorFunktion;
    }

    public void setErrorFunktion(Double errorFunktion) {
        this.errorFunktion = errorFunktion;
    }

    public Double getAccurateErrorFunktion() {
        return AccurateErrorFunktion;
    }

    public void setAccurateErrorFunktion(Double accurateErrorFunktion) {
        AccurateErrorFunktion = accurateErrorFunktion;
    }

    public Double getErrorFunktionB0() {
        return errorFunktionB0;
    }

    public void setErrorFunktionB0(Double errorFunktionB0) {
        this.errorFunktionB0 = errorFunktionB0;
    }

    public Double getErrorFunktionB1() {
        return errorFunktionB1;
    }

    public void setErrorFunktionB1(Double errorFunktionB1) {
        this.errorFunktionB1 = errorFunktionB1;
    }

    public Double getAccurateErrorFunktionB0() {
        return AccurateErrorFunktionB0;
    }

    public void setAccurateErrorFunktionB0(Double accurateErrorFunktionB0) {
        AccurateErrorFunktionB0 = accurateErrorFunktionB0;
    }

    public Double getAccurateErrorFunktionB1() {
        return AccurateErrorFunktionB1;
    }

    public void setAccurateErrorFunktionB1(Double accurateErrorFunktionB1) {
        AccurateErrorFunktionB1 = accurateErrorFunktionB1;
    }
}
