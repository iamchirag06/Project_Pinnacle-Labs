import java.util.*;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureTime;
    private int totalSeats;
    private boolean[] availableSeats;

    public Flight(String flightNumber, String origin, String destination, Date departureTime, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = new boolean[totalSeats];
        Arrays.fill(this.availableSeats, true);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber > 0 && seatNumber <= totalSeats && availableSeats[seatNumber - 1];
    }

    public boolean bookSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            availableSeats[seatNumber - 1] = false;
            return true;
        }
        return false;
    }

    public void displayAvailableSeats() {
        System.out.println("Available seats for flight " + flightNumber + ":");
        for (int i = 0; i < totalSeats; i++) {
            if (availableSeats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }
}

class PaymentProcessor {
    public static boolean processPayment(String cardNumber, String cardHolderName, double amount) {
        System.out.println("Processing payment of $" + amount + " for cardholder: " + cardHolderName);
        return true;
    }
}

public class FlightBookingSystem {
    private static List<Flight> flights = new ArrayList<>();

    public static void main(String[] args) {
        initializeFlights();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Flight Booking System!");

        System.out.println("Available flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println((i + 1) + ". Flight: " + flight.getFlightNumber() + ", From: " + flight.getOrigin() + ", To: " + flight.getDestination() + ", Departure: " + flight.getDepartureTime());
        }

        System.out.print("Select a flight by entering the number: ");
        int flightChoice = scanner.nextInt();

        if (flightChoice < 1 || flightChoice > flights.size()) {
            System.out.println("Invalid flight selection.");
            scanner.close();
            return;
        }

        Flight selectedFlight = flights.get(flightChoice - 1);
        selectedFlight.displayAvailableSeats();

        System.out.print("Select a seat number: ");
        int seatChoice = scanner.nextInt();

        if (selectedFlight.bookSeat(seatChoice)) {
            System.out.println("Seat " + seatChoice + " booked successfully.");

            System.out.print("Enter your card number: ");
            String cardNumber = scanner.next();

            System.out.print("Enter cardholder name: ");
            scanner.nextLine();
            String cardHolderName = scanner.nextLine();

            double paymentAmount = 150.00;
            if (PaymentProcessor.processPayment(cardNumber, cardHolderName, paymentAmount)) {
                System.out.println("Payment successful! Booking confirmed.");
            } else {
                System.out.println("Payment failed. Please try again.");
            }
        } else {
            System.out.println("Seat not available. Please choose a different seat.");
        }

        scanner.close();
    }

    private static void initializeFlights() {
        flights.add(new Flight("AI101", "New York", "Los Angeles", new Date(), 10));
        flights.add(new Flight("AI202", "Chicago", "Miami", new Date(), 15));
        flights.add(new Flight("AI303", "San Francisco", "Seattle", new Date(), 12));
    }
}
