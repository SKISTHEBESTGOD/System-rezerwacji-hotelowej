import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {

    public Customer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void displayMenu(Hotel hotel, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Menu Klienta ---");
            System.out.println("Wpisz 1, żeby wyświetlić dostępne pokoje");
            System.out.println("Wpisz 2, żeby zarezerwować pokój");
            System.out.println("Wpisz 3, żeby anulować rezerwację");
            System.out.println("Wpisz 4, żeby wyświetlić historię rezerwacji");
            System.out.println("Wpisz 0, żeby powrócić do menu głównego");
            System.out.print("Wprowadź swój wybór: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    List<Room> allRooms = hotel.getAllRooms();
                    if (allRooms.isEmpty()) {
                        System.out.println("Brak pokoi w systemie.");
                    } else {
                        System.out.println("Lista pokoi:");
                        for (Room room : allRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Podaj numer pokoju do rezerwacji: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());
                    System.out.print("Podaj datę zameldowania (RRRR-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(scanner.nextLine());
                    System.out.print("Podaj datę wymeldowania (RRRR-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(scanner.nextLine());
                    System.out.print("Podaj kwotę płatności: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    System.out.println("Wybierz metodę płatności:");
                    System.out.println("1. CASH");
                    System.out.println("2. BLIK");
                    System.out.println("3. CARD");
                    System.out.print("Wprowadź swój wybór: ");
                    int paymentChoice = Integer.parseInt(scanner.nextLine());
                    PaymentType paymentType;
                    switch (paymentChoice) {
                        case 1:
                            paymentType = PaymentType.CASH;
                            break;
                        case 2:
                            paymentType = PaymentType.BLIK;
                            break;
                        case 3:
                            paymentType = PaymentType.CARD;
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór, domyślnie CASH.");
                            paymentType = PaymentType.CASH;
                    }
                    Payment payment = new Payment(amount, paymentType);
                    BookingService bookingService = new BookingService(hotel);
                    bookingService.bookRoom(this, roomNumber, checkIn, checkOut, payment);
                    break;
                case 3:
                    hotel.cancelReservation(this);
                    break;
                case 4:
                    hotel.listReservations();
                    break;
                case 0:
                    System.out.println("Powrót do menu głównego...");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        } while (choice != 0);
    }
}
