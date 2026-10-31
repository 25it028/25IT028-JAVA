package PRACTICAL9;
class Theater {
    int seatsLeft = 5;

    synchronized void book(String name) {

        if (seatsLeft > 0) {
            seatsLeft--;

            System.out.println(name + " booked a seat");
        } else {
            System.out.println(name + " could not book a seat");
        }
    }
}
class BookingThread extends Thread {
    Theater theater;
    BookingThread(Theater theater, String name) {
        super(name);
        this.theater = theater;
    }
    public void run() {
        theater.book(getName());
    }
}

public class SeatBooking {
    public static void main(String[] args) throws Exception {

        Theater theater = new Theater();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new BookingThread(
                theater,
                "Person-" + (i + 1)
            );

            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Seats Left = " + theater.seatsLeft);
    }
}
