package PRACTICAL9;
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

class MyThread extends Thread {
    Counter c;

    MyThread(Counter c) {
        this.c = c;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            c.increment();
        }
    }
}

public class CounterRaceSynchronized {
    public static void main(String[] args) throws Exception {

        Counter c = new Counter();

        MyThread t1 = new MyThread(c);
        MyThread t2 = new MyThread(c);
        MyThread t3 = new MyThread(c);
        MyThread t4 = new MyThread(c);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Final Count = " + c.count);
        System.out.println("Expected Count = 40000");
    }
}