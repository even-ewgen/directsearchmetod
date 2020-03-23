package calculations;

import containers.DataContainer;

import java.util.ArrayList;

import static java.lang.Math.*;

public class ErrorsFunktion {
    private DataContainer dataContainer;
    //Задаем значения результатов экспериментов
    public static double[] allX;
    public static double[] allY;

    public static ArrayList<Double> newAllY = new ArrayList<>();
    //Задаем значения для коэффициентов на первой итерации и приращение для них
    private static double b0 = 1;
    private static double b1 = 1;
    private static double increment = 0.1;
    //Точность, с которой будем сравнивать
    private static double accuracy = 0.001;
    //Задаем контейнеры для функции ошибок
    private static double errorFunktion;
    private static double oldErrorFunktion;
    //Задаем счетчик итераций
    private static int iterationCounter = 1;

    public ErrorsFunktion(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
        allX = dataContainer.getAllX();
        allY = dataContainer.getAllY();
    }

    public void start() {
        //Пока не достигли удовлетворяющей точности повторяем действия
        boolean work = true;
        while (work) {
            double preAccuracy = abs(oldErrorFunktion - errorFunktion);
            errorFunktionResult(b0, b1);

            //Последующие итерации
            b0 = iterationB0(b0, 1);
            b1 = iterationB0(b1, 2);
            iterationCounter = iterationCounter + 1;

            double postAccuracy = abs(oldErrorFunktion - errorFunktion);
            if (preAccuracy == postAccuracy) {
                increment = increment/10;
            }
            if ((iterationCounter == 10000) || ((abs(oldErrorFunktion - errorFunktion) <= accuracy))) {
                work = false;
            }
        }

        System.out.println("Итог:");
        System.out.println("Первый коэффициент: " + b0);
        System.out.println("Второй коэффициент: " + b1);
        System.out.println("Значение функции ошибок: " + errorFunktion);
        System.out.println("Точность: " + (abs(oldErrorFunktion - errorFunktion)));
        System.out.println("Количество итераций: " + iterationCounter);

        for (double x : allX) {
            double y =(b0 + (x)*b1);
            newAllY.add(y);
        }

        dataContainer.setErrorFunktion(errorFunktion);
        dataContainer.setAllYFE(newAllY);
    }

    private static void errorFunktionResult(double b0, double b1) {
        //Получаем значение для функции ошибок
        //Цикл реализует операцию суммирования
        oldErrorFunktion = errorFunktion; //значение на предыдущей итерации
        errorFunktion = 0;
        for (int i = 0; i < allX.length; i++) {
            errorFunktion = (errorFunktion + pow((allY[i] - (b0 + (allX[i])*b1)),2));
        }
    }

    private Double iterationB0(double b, int number) {
        double boxForPreIterationErrorFunktion = errorFunktion;
        double boxForB0 = b;
        b = b + increment;

        if (number == 1) errorFunktionResult(b, b1); //новое значение
        else errorFunktionResult(b0, b);

        if (errorFunktion > oldErrorFunktion) {
            b = boxForB0 - increment;

            if (number == 1) errorFunktionResult(b, b1); //новое значение
            else errorFunktionResult(b0, b);

            if (errorFunktion > boxForPreIterationErrorFunktion) b = boxForB0; //иначе b не меняется
        } //иначе b не меняется
        return b;
    }
}
