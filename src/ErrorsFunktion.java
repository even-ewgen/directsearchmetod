import javafx.stage.Stage;

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
    // private static float b2 = 1;
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
            errorFunktionResult();

            //Последующие итерации
            iterationB0();
            iterationB1();
            iterationCounter = iterationCounter + 1;

            double postAccuracy = abs(oldErrorFunktion - errorFunktion);

            System.out.println("b0:                 " + b0);
            System.out.println("b1:                 " + b1);
            System.out.println("Всего итераций: " + iterationCounter);
            System.out.println("Точность: " + postAccuracy);

            if (preAccuracy == postAccuracy) {
                increment = increment/10;
            }
            if ((iterationCounter == 10000) || ((abs(oldErrorFunktion - errorFunktion) <= accuracy))) {
                work = false;
            }
        }
//
        System.out.println("Итог: ");
        System.out.println("b0:                 " + b0);
        System.out.println("b1:                 " + b1);
        System.out.println("errorFunktion:      " + errorFunktion);
        System.out.println("Точность:           " + (abs(oldErrorFunktion - errorFunktion)));
        System.out.println("iterationCounter:   " + iterationCounter);

        for (double x : allX) {
            double y =(b0 + (x)*b1); ///Это я
            newAllY.add(y);
        }

        dataContainer.setErrorFunktion(errorFunktion);
        dataContainer.setErrorFunktionB0(b0);
        dataContainer.setErrorFunktionB1(b1);
        dataContainer.setAllYFE(newAllY);
    }

    private static void errorFunktionResult() {
        //Получаем значение для функции ошибок
        //Цикл реализует операцию суммирования
        oldErrorFunktion = errorFunktion; //значение на предыдущей итерации
        errorFunktion = 0;
        for (int i = 0; i < allX.length; i++) {
            errorFunktion = (errorFunktion + pow((allY[i] - (b0 + (allX[i])*b1)),2)); //Это я
        }
    }

    private static void iterationB0() {
        System.out.println("Это первый коэффициент: ");
        double boxForPreIterationErrorFunktion = errorFunktion;
        double boxForB0 = b0;
        b0 = b0 + increment;
        System.out.println(b0);

        errorFunktionResult(); //новое значение
        if (errorFunktion < oldErrorFunktion) {
            System.out.println("Значение меньше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            //Все отлично, b0 остается измененным
            System.out.println("b0 = " + b0);

        } else if (errorFunktion > oldErrorFunktion) {
            System.out.println("Значение больше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            b0 = boxForB0 - increment;
            errorFunktionResult();
            System.out.println(errorFunktion);
            System.out.println("b0 = " + b0);

            if (errorFunktion < boxForPreIterationErrorFunktion) {
                System.out.println("Значение меньше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                //Оставляем это значение
                System.out.println("b0 = " + b0);

            } else if (errorFunktion > boxForPreIterationErrorFunktion) {
                System.out.println("Значение больше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                b0 = boxForB0;
                System.out.println("b0 = " + b0);
            }
        }
    }

    private static void iterationB1() {
        System.out.println("Это второй коэффициент: ");
        double boxForPreIterationErrorFunktion = errorFunktion;
        double boxForB1 = b1;
        b1 = b1 + increment;
        System.out.println(b1);

        errorFunktionResult(); //новое значение
        if (errorFunktion < oldErrorFunktion) {
            System.out.println("Значение меньше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            //Все отлично, b0 остается измененным
            System.out.println("b1 = " + b1);

        } else if (errorFunktion > oldErrorFunktion) {
            System.out.println("Значение больше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            b1 = boxForB1 - increment;
            errorFunktionResult();
            System.out.println(errorFunktion);
            System.out.println("b1 = " + b1);

            if (errorFunktion < boxForPreIterationErrorFunktion) {
                System.out.println("Значение меньше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                //Оставляем это значение
                System.out.println("b1 = " + b1);

            } else if (errorFunktion > boxForPreIterationErrorFunktion) {
                System.out.println("Значение больше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                b1 = boxForB1;
                System.out.println("b1 = " + b1);
            }
        }
    }
}
