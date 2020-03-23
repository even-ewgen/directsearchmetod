package graph;

import containers.DataContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MakeGraph extends Application {

    @Override
    public void start(Stage stage) {
        double[] x = DataContainer.getAllX();
        double[] y = DataContainer.getAllY();
        ArrayList<Double> newY1 = DataContainer.getAllYFE();

        stage.setTitle("Метод прямого поиска");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Метод прямого поиска");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Исходные данные");
        //populating the series with data
        for (int i = 0; i < x.length; i ++) {
            series.getData().add(new XYChart.Data(x[i], y[i]));
        }

        XYChart.Series newSeries = new XYChart.Series();
        newSeries.setName("Расчетные данные");
        //populating the series with data
        for (int i = 0; i < x.length; i ++) {
            newSeries.getData().add(new XYChart.Data(x[i], newY1.get(i)));
        }

        lineChart.getData().addAll(series, newSeries);

        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);

        stage.show();
    }

    public void work(String[] args) {
        launch(args);
    }
}