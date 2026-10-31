package PRACTICAL9;
class SumThread extends Thread {

    int[] arr;
    int start;
    int end;

    static int total = 0;

    SumThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    public void run() {
        for (int i = start; i < end; i++) {
            add(arr[i]);
        }
    }
    synchronized static void add(int value) {
        total = total + value;
    }
}
public class ArraySum {

    public static void main(String[] args) throws Exception {

        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        SumThread t1 = new SumThread(arr, 0, 250);
        SumThread t2 = new SumThread(arr, 250, 500);
        SumThread t3 = new SumThread(arr, 500, 750);
        SumThread t4 = new SumThread(arr, 750, 1000);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Total = " + SumThread.total);
        System.out.println("Expected = 1000");
    }
}
