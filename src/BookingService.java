import java.time.LocalDate;

public class BookingService {
    private Hotel hotel;
    private ReservationValidator validator;
    private PaymentProcessor paymentProcessor;

    public BookingService(Hotel hotel) {
        this.hotel = hotel;
        this.validator = new ReservationValidator();
        this.paymentProcessor = new PaymentProcessor();
    }

    public void bookRoom(Customer customer, int roomNumber, LocalDate checkIn, LocalDate checkOut, Payment payment) {
        if (!validator.isValidDateRange(checkIn, checkOut)) {
            System.out.println("Data wymeldowania musi być późniejsza niż data zameldowania.");
            return;
        }

        Room room = hotel.getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Pokój o podanym numerze nie istnieje.");
            return;
        }

        if (!room.isAvailable()) {
            System.out.println("Pokój nie jest dostępny.");
            return;
        }

        if (!paymentProcessor.processPayment(payment)) {
            System.out.println("Płatność nie powiodła się.");
            return;
        }

        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        hotel.makeReservation(reservation);
        System.out.println("Rezerwacja została potwierdzona.");
    }
}
