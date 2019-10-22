import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Math.exp;

public class AccurateErrorsFunktion {
    private String[] stage;
    //Задаем значения результатов экспериментов
    public static double[] allX = {1.14, 1.37, 1.89, 2.09, 2.45, 2.4, 2.73, 3.04, 3.19, 3.09, 3.05, 3.10, 3.34, 3.75, 4.19, 4.59};
    public static double[] allY = {112,115,152,199,161,209,237,231,233,259,287,240,281,311,392,357}; //Это я

/*    public static double[] allX = {400, 470, 590, 610, 620, 840, 950, 1200,  1400, 1550};
    public static double[] allY = {0.0125, 0.0165, 0.0215, 0.0225, 0.0235, 0.0420,  0.0530,  0.0750,  0.0970,  0.1200}; //Это Леша*/

    /*    public static float[] allX = {(float) 0.5, (float) 1.5, (float) 2.0, (float) 2.3, (float) 3.7, (float) 5.4, (float) 8.2};
        public static float[] allY = {(float) 0.618, (float) 9.645, (float) 19.799, (float) 28.078, (float) 92.166, (float) 327.166, (float) 673.91};//Это Катя*/
    public static ArrayList<Double> newAllY = new ArrayList<>();
    private static double summX = 0;
    private static double sredX = 0; //Это для более точных значений, сейчас не реализовано. Простовычетать эту переменную их х в функции ошибок
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

    public AccurateErrorsFunktion(String[] stage) {
        this.stage = stage;
    }

    public void start() {
        //Подготовка
        for (double x : allX)  summX = summX + x;
        sredX = summX/allX.length;
        errorFunktionResult();

        //Нулевая итерация
        iterationB0();
        iterationB1();
        //   iterationB2();

        //Пока не достигли удовлетворяющей точности повторяем действия
        while (!(abs(oldErrorFunktion - errorFunktion) <= accuracy)) {
            double preAccuracy = abs(oldErrorFunktion - errorFunktion);

            //Последующие итерации
            iterationB0();
            iterationB1();
            //    iterationB2();
            iterationCounter = iterationCounter + 1;
            System.out.println("Всего итераций: " + iterationCounter);
            System.out.println("Точность: " + abs(oldErrorFunktion - errorFunktion));

            double postAccuracy = abs(oldErrorFunktion - errorFunktion);
            System.out.println(postAccuracy + "   " + preAccuracy);
            if (preAccuracy == postAccuracy) {
                increment = increment/2;
            }
        }

        System.out.println("Итог: ");
        System.out.println("b0:                 " + b0);
        System.out.println("b1:                 " + b1);
        //System.out.println("b2:                 " + b2);
        System.out.println("errorFunktion:      " + errorFunktion);
        System.out.println("Точность:           " + (abs(oldErrorFunktion - errorFunktion)));
        System.out.println("iterationCounter:   " + iterationCounter);

        for (double x : allX) {
            //float y = (float) (b0 + pow(x,2)*b1); //Это я экспериментирую
            double y =(b0 + exp(x - sredX)*b1); ///Это я
            //double y = (b0 + pow((x-sredX),2)*b1); //Это Катя
            //double y = (b0 + exp(b1 * (x))); //Это Леша и Оля
            newAllY.add(y);
        }

        for (int i = 0; i < allY.length; i ++) {
            System.out.println(i + ". " + allY[i] + " " + newAllY.get(i) + " Точность: " + abs(allY[i] - newAllY.get(i)));
        }

        MakeGraph makeGrahp = new MakeGraph();
        makeGrahp.work(stage);
    }

    private static void errorFunktionResult() {
        //Получаем значение для функции ошибок
        //Цикл реализует операцию суммирования
        // pow((allY[i] - (b0 + b1*allX[i])),2) - это формула моей функции ошибок. В цикле ее нужно изменить на свою.
        oldErrorFunktion = errorFunktion; //значение на предыдущей итерации
        errorFunktion = 0;
        for (int i = 0; i < allX.length; i++) {
            //errorFunktion = (float) (errorFunktion + pow((allY[i] - (b0 + pow(allX[i],2)*b1)),2)); //Это я экспериментирую
            errorFunktion = (errorFunktion + pow((allY[i] - (b0 + exp(allX[i] - sredX)*b1)),2)); //Это я
            //errorFunktion = (errorFunktion + pow((allY[i] - (b0 + pow((allX[i] - sredX),2)*b1)),2)); //Это Катя
            //errorFunktion = (errorFunktion + pow((allY[i] - (b0 + exp(b1 *(allX[i])))),2)); //Это Леша
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
            System.out.println(b0);

        } else if (errorFunktion > oldErrorFunktion) {
            System.out.println("Значение больше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            b0 = b0 - increment;
            errorFunktionResult();
            System.out.println(errorFunktion);
            System.out.println(b0);

            if (errorFunktion < boxForPreIterationErrorFunktion) {
                System.out.println("Значение меньше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                //Оставляем это значение
                System.out.println(b0);

            } else if (errorFunktion > boxForPreIterationErrorFunktion) {
                System.out.println("Значение больше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                b0 = boxForB0;
                System.out.println(b0);
            }
        }
    }

    private static void iterationB1() {
        System.out.println("Это второй коэффициент: ");
        double boxForPreIterationErrorFunktion = errorFunktion;
        double boxForB0 = b1;
        b1 = b1 + increment;
        System.out.println(b1);

        errorFunktionResult(); //новое значение
        if (errorFunktion < oldErrorFunktion) {
            System.out.println("Значение меньше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            //Все отлично, b0 остается измененным
            System.out.println(b1);

        } else if (errorFunktion > oldErrorFunktion) {
            System.out.println("Значение больше чем старое: ");
            System.out.println("errorFunktion:      " + errorFunktion);
            System.out.println("oldErrorFunktion:   " + oldErrorFunktion);
            b1 = b1 - increment;
            errorFunktionResult();
            System.out.println(errorFunktion);
            System.out.println(b1);

            if (errorFunktion < boxForPreIterationErrorFunktion) {
                System.out.println("Значение меньше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                //Оставляем это значение
                System.out.println(b1);

            } else if (errorFunktion > boxForPreIterationErrorFunktion) {
                System.out.println("Значение больше чем старое: ");
                System.out.println("errorFunktion:      " + errorFunktion);
                System.out.println("oldErrorFunktion:   " + boxForPreIterationErrorFunktion);
                b1 = boxForB0;
                System.out.println(b1);
            }
        }
    }
}
