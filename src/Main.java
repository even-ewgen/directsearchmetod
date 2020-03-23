import calculations.ErrorsFunktion;
import containers.DataContainer;
import graph.MakeGraph;

public class Main {

    public static void main(String[] arg) {
        DataContainer dataContainer = new DataContainer();

        ErrorsFunktion errorsFunktion = new ErrorsFunktion(dataContainer);
        errorsFunktion.start();

        dataContainer.printYAndNewY();

        MakeGraph makeGrahp = new MakeGraph();
        makeGrahp.getData(dataContainer);
        makeGrahp.work(arg);
    }
}