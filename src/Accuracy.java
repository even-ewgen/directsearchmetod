import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Accuracy {
    private DataContainer dataContainer;
    private double[] allX;

    public Accuracy(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
        allX = dataContainer.getAllX();
    }

    public void start() {
        double S2yi = dataContainer.getErrorFunktion() / (allX.length - 2);
        double rS2yi = sqrt(S2yi);

        double S2B0 = S2yi / allX.length;

        double summX2 = 0;
        for (double x : allX) summX2 = summX2 + pow((x - dataContainer.getSredX()), 2);
        double S2B1 = S2yi / summX2;

        double SB0 = S2yi * sqrt((1 / (double) allX.length) + pow(dataContainer.getSredX(), 2) / (summX2));

        System.out.println("S2yi = " + S2yi);
        System.out.println("rS2yi = " + rS2yi);
        System.out.println("S2B0 = " + S2B0);
        System.out.println("S2B1 = " + S2B1);
        System.out.println("SB0 = " + SB0);
    }
}
