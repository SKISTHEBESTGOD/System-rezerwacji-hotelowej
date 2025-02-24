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
            System.out.println("1. Wyświetl dostępne pokoje");
            System.out.println("2. Zarezerwuj pokój");
            System.out.println("3. Anuluj rezerwację");
            System.out.println("4. Historia rezerwacji");
            System.out.println("0. Powrót do menu głównego");
            System.out.print("Wprowadź swój wybór: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    List<Room> availableRooms = hotel.getAvailableRooms();
                    if (availableRooms.isEmpty()) {
                        System.out.println("Brak dostępnych pokoi w tym momencie.");
                    } else {
                        System.out.println("Dostępne pokoje:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Podaj numer pokoju do rezerwacji: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());
                    Room selectedRoom = null;
                    for (Room room : hotel.getAvailableRooms()) {
                        if (room.getRoomNumber() == roomNumber) {
                            selectedRoom = room;
                            break;
                        }
                    }
                    if (selectedRoom == null) {
                        System.out.println("Pokój niedostępny lub nie istnieje.");
                        break;
                    }
                    System.out.print("Podaj datę zameldowania (RRRR-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(scanner.nextLine());
                    System.out.print("Podaj datę wymeldowania (RRRR-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(scanner.nextLine());

                    Reservation reservation = new Reservation(this, selectedRoom, checkIn, checkOut);
                    hotel.makeReservation(reservation);
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
