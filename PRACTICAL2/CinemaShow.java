public class CinemaShow {

    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

   
    public CinemaShow(String title) {
        this(title, 100);
    }

    
    public boolean book(int n) {
        if (n > 0 && n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    // Cancel seats
    public void cancel(int n) {
        if (n > 0 && n <= capacity - seatsAvailable) {
            seatsAvailable += n;
            totalBooked -= n;
        }
    }

    // Getter
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    // Static getter
    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {

        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println("Book 20 seats: " + show.book(20));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 25 seats: " + show.book(25));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 10 seats: " + show.book(10));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        show.cancel(15);

        System.out.println("After cancelling 15 seats:");
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 10 seats: " + show.book(10));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Total Booked Seats: " + CinemaShow.getTotalBooked());
    }
}