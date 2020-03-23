import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MakeGraph extends Application {
    private static DataContainer dataContainer;

    public void getData(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void start(Stage stage) {
        double[] x = dataContainer.getAllX();
        double[] y = dataContainer.getAllY();
        ArrayList<Double> newY1 = dataContainer.getAllYFE();
        ArrayList<Double> newY2 = dataContainer.getAllYAFE();

        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Метод прямого поиска");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Исходный график");
        //populating the series with data
        for (int i = 0; i < x.length; i ++) {
            series.getData().add(new XYChart.Data(x[i], y[i]));
        }

        XYChart.Series newSeries1 = new XYChart.Series();
        newSeries1.setName("Новый график");
        //populating the series with data
        for (int i = 0; i < x.length; i ++) {
            newSeries1.getData().add(new XYChart.Data(x[i], newY1.get(i)));
        }

        lineChart.getData().addAll(series, newSeries1);

        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);

        stage.show();
    }//

    public void work(String[] args) {
        launch(args);
    }
}