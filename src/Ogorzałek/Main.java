package Ogorzałek;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        loadSampleData(hotel);

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int mainChoice = readInt("Wprowadź swój wybór (np. 1):", scanner);

            switch (mainChoice) {
                case 1:
                    String firstName = readNonEmptyString("Podaj swoje imię (np. Jan):", scanner);
                    String lastName = readNonEmptyString("Podaj swoje nazwisko (np. Kowalski):", scanner);
                    Customer customer = new Customer(firstName, lastName);
                    customer.displayMenu(hotel, scanner);
                    break;
                case 2:
                    String adminFirstName = readNonEmptyString("Podaj imię administratora (np. Anna):", scanner);
                    String adminLastName = readNonEmptyString("Podaj nazwisko administratora (np. Nowak):", scanner);
                    Admin admin = new Admin(adminFirstName, adminLastName);
                    admin.displayMenu(hotel, scanner);
                    break;
                case 3:
                    displayHotelStats(hotel);
                    break;
                case 4:
                    advancedSystemOptions(hotel, scanner);
                    break;
                case 5:
                    searchReservations(hotel, scanner);
                    break;
                case 6:
                    System.out.println("\n--- Lista Wszystkich Pokoi ---");
                    hotel.listRooms();
                    break;
                case 7:
                    System.out.println("\n--- Lista Wszystkich Rezerwacji ---");
                    hotel.listReservations();
                    break;
                case 0:
                    System.out.println("Wyjście z systemu. Do widzenia!");
                    exit = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n--- System Rezerwacji Hotelowej ---");
        System.out.println("Wybierz opcję(Podaj odpowiednia liczbę):");
        System.out.println("1. Klient");
        System.out.println("2. Administrator");
        System.out.println("3. Wyświetl podstawowe statystyki hotelu");
        System.out.println("4. Zaawansowane opcje systemowe");
        System.out.println("5. Wyszukaj rezerwacje");
        System.out.println("6. Wyświetl wszystkie pokoje");
        System.out.println("7. Wyświetl wszystkie rezerwacje");
        System.out.println("0. Wyjście");
    }

    private static void loadSampleData(Hotel hotel) {
        System.out.println("Ładowanie przykładowych danych...");
        hotel.addRoom(new Room(101, RoomType.SINGLE));
        hotel.addRoom(new Room(102, RoomType.DOUBLE));
        hotel.addRoom(new Room(103, RoomType.APARTMENT));
        hotel.addRoom(new Room(104, RoomType.SINGLE));
        hotel.addRoom(new Room(105, RoomType.DOUBLE));
        System.out.println("Przykładowe dane zostały załadowane.");
    }

    private static void displayHotelStats(Hotel hotel) {
        System.out.println("\n--- Podstawowe Statystyki Hotelu ---");
        System.out.println("Liczba pokoi: " + hotel.getAllRooms().size());
        System.out.println("Liczba rezerwacji: " + hotel.getReservations().size());
    }

    private static void advancedSystemOptions(Hotel hotel, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Zaawansowane Opcje Systemowe ---");
            System.out.println("1. Reset systemu (anuluj wszystkie rezerwacje, ustaw wszystkie pokoje jako dostępne)");
            System.out.println("2. Wyświetl szczegółowe statystyki hotelu");
            System.out.println("0. Powrót do głównego menu");
            int advChoice = readInt("Wprowadź swój wybór (np. 1):", scanner);

            switch (advChoice) {
                case 1:
                    resetSystem(hotel);
                    break;
                case 2:
                    displayDetailedHotelStats(hotel);
                    break;
                case 0:
                    System.out.println("Powrót do głównego menu...");
                    back = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static void resetSystem(Hotel hotel) {
        for (Room room : hotel.getAllRooms()) {
            room.setAvailable(true);
        }
        hotel.getReservations().clear();
        System.out.println("System został zresetowany: wszystkie rezerwacje anulowane, a wszystkie pokoje są dostępne.");
    }

    private static void displayDetailedHotelStats(Hotel hotel) {
        int totalRooms = hotel.getAllRooms().size();
        int reservedCount = 0;
        for (Room room : hotel.getAllRooms()) {
            if (!room.isAvailable()) {
                reservedCount++;
            }
        }
        int availableCount = totalRooms - reservedCount;
        System.out.println("\n--- Szczegółowe Statystyki Hotelu ---");
        System.out.println("Całkowita liczba pokoi: " + totalRooms);
        System.out.println("Dostępne pokoje: " + availableCount);
        System.out.println("Zarezerwowane pokoje: " + reservedCount);
    }

    private static void searchReservations(Hotel hotel, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Wyszukiwanie Rezerwacji ---");
            System.out.println("Wybierz kryterium wyszukiwania:");
            System.out.println("1. Wyszukaj według nazwiska klienta");
            System.out.println("2. Wyszukaj według numeru pokoju");
            System.out.println("0. Powrót do głównego menu");
            int searchChoice = readInt("Wprowadź swój wybór (np. 1):", scanner);

            switch (searchChoice) {
                case 1:
                    String name = readNonEmptyString("Wprowadź część nazwiska klienta (np. Kowalski):", scanner);
                    var resultsByName = hotel.searchReservationsByCustomer(name);
                    if (resultsByName.isEmpty()) {
                        System.out.println("Nie znaleziono rezerwacji dla klienta zawierającego: " + name);
                    } else {
                        System.out.println("Znalezione rezerwacje:");
                        for (Reservation res : resultsByName) {
                            System.out.println(res);
                        }
                    }
                    break;
                case 2:
                    int roomNumber = readInt("Wprowadź numer pokoju (np. 101):", scanner);
                    var resultsByRoom = hotel.searchReservationsByRoom(roomNumber);
                    if (resultsByRoom.isEmpty()) {
                        System.out.println("Nie znaleziono rezerwacji dla pokoju numer: " + roomNumber);
                    } else {
                        System.out.println("Znalezione rezerwacje:");
                        for (Reservation res : resultsByRoom) {
                            System.out.println(res);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Powrót do głównego menu...");
                    back = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static int readInt(String prompt, Scanner scanner) {
        int number;
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Błąd: wprowadź poprawną liczbę całkowitą (np. 1, 2, 3).");
            }
        }
    }

    private static String readNonEmptyString(String prompt, Scanner scanner) {
        String input;
        while (true) {
            System.out.print(prompt + " ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Błąd: wartość nie może być pusta.");
            }
        }
    }
}
