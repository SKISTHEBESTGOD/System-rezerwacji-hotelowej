package Ogorzałek;

import java.util.Scanner;

public class Admin extends User {

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void displayMenu(Hotel hotel, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Menu Administratora ---");
            System.out.println("Wpisz 1, żeby dodać pokój");
            System.out.println("Wpisz 2, żeby usunąć pokój");
            System.out.println("Wpisz 3, żeby zobaczyć listę wszystkich pokoi");
            System.out.println("Wpisz 4, żeby zobaczyć listę wszystkich rezerwacji");
            System.out.println("Wpisz 0, żeby powrócić do menu głównego");
            System.out.print("Wprowadź swój wybór: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Podaj numer pokoju: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Wybierz typ pokoju(Wpisz odpowiednią cyfrę):");
                    System.out.println("1. SINGLE");
                    System.out.println("2. DOUBLE");
                    System.out.println("3. APARTMENT");
                    System.out.print("Wprowadź swój wybór: ");
                    int typeChoice = Integer.parseInt(scanner.nextLine());
                    RoomType roomType;
                    switch (typeChoice) {
                        case 1:
                            roomType = RoomType.SINGLE;
                            break;
                        case 2:
                            roomType = RoomType.DOUBLE;
                            break;
                        case 3:
                            roomType = RoomType.APARTMENT;
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór typu pokoju. Domyślnie ustawiono SINGLE.");
                            roomType = RoomType.SINGLE;
                    }
                    Room room = new Room(roomNumber, roomType);
                    hotel.addRoom(room);
                    break;
                case 2:
                    System.out.print("Podaj numer pokoju do usunięcia: ");
                    int removeRoomNumber = Integer.parseInt(scanner.nextLine());
                    hotel.removeRoom(removeRoomNumber);
                    break;
                case 3:
                    System.out.println("Lista wszystkich pokoi:");
                    hotel.listRooms();
                    break;
                case 4:
                    System.out.println("Lista wszystkich rezerwacji:");
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
