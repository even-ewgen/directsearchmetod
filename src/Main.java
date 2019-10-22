public class Main {

    public static void main(String[] arg) {
        ErrorsFunktion errorsFunktion = new ErrorsFunktion(arg);
        errorsFunktion.start();

        AccurateErrorsFunktion accurateErrorsFunktion = new AccurateErrorsFunktion(arg);
        accurateErrorsFunktion.start();
    }
}