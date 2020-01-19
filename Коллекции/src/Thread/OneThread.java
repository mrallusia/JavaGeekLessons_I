package Thread;

public class OneThread extends Thread  {

    private float[] arr;

    OneThread (){
        //Создадим массив
        arr = new float[MainClass.SIZE];

        for (int i = 0; i < MainClass.SIZE; i++) {
            arr[i] = 1;
        }
    }

    @Override
    public void run() {

        long a = System.currentTimeMillis();

        for (int i = 0; i < MainClass.SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long b = System.currentTimeMillis() - a;
        System.out.println("Затраченое время в миллисекундах при одном потоке: " + b);
    }


    public static void threadOne() {
        OneThread oneThread = new OneThread();
        oneThread.start();
    }

}

