import java.util.Arrays;

public class Main {

    public static void main(String[] arg) {
        DataContainer dataContainer = new DataContainer();

        ErrorsFunktion errorsFunktion = new ErrorsFunktion(dataContainer);
        errorsFunktion.start();

        AccurateErrorsFunktion accurateErrorsFunktion = new AccurateErrorsFunktion(dataContainer);
        accurateErrorsFunktion.start();

        System.out.println(dataContainer.getAllYAFE());
        System.out.println(dataContainer.getAllYFE());
        System.out.println(Arrays.toString(dataContainer.getAllY()));

        Accuracy accuracy = new Accuracy(dataContainer);
        accuracy.start();


        MakeGraph makeGrahp = new MakeGraph();
        makeGrahp.getData(dataContainer);
        makeGrahp.work(arg);
    }
}