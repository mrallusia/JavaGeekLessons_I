package Thread;

public class MainClass {

    public static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {

        //Одиночный поток
        OneThread.threadOne();

        //Два потока
        DoubleThread.treadTwo();

    }
}
