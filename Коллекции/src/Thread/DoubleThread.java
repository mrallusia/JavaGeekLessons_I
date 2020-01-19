package Thread;

public class DoubleThread extends Thread {

    private float[] arr;

    DoubleThread (float[] arr){
        this.arr = arr;
    }


    @Override
    public void run() {

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long b = System.currentTimeMillis() - a;
        System.out.println("Затраченое время в миллисекундах при нескольких потоках: " + b);


    }

    public static void treadTwo() {

        //Создадим массив
        float[] arr = new float[MainClass.SIZE];
        float[] a1 = new float[MainClass.HALF];
        float[] a2 = new float[MainClass.HALF];

        for (int i = 0; i < MainClass.SIZE; i++) {
            arr[i] = 1;
        }

        //Разделим на несколько полумассивов для двух потоков
        System.arraycopy(arr, 0, a1, 0, MainClass.HALF);
        System.arraycopy(arr, MainClass.HALF, a2, 0, MainClass.HALF);

        DoubleThread doubleThread1 = new DoubleThread(a1);
        DoubleThread doubleThread2 = new DoubleThread(a2);
        doubleThread1.start();
        doubleThread2.start();

        System.arraycopy(a1, 0, arr, 0, MainClass.HALF);
        System.arraycopy(a2, 0, arr, MainClass.HALF, MainClass.HALF);

    }
}
