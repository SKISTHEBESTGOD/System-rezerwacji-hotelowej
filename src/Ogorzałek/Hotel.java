package Ogorzałek;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Pokój dodany: " + room);
    }

    public void removeRoom(int roomNumber) {
        Room roomToRemove = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                roomToRemove = room;
                break;
            }
        }
        if (roomToRemove != null) {
            rooms.remove(roomToRemove);
            System.out.println("Pokój usunięty: " + roomToRemove);
        } else {
            System.out.println("Nie znaleziono pokoju o numerze " + roomNumber + ".");
        }
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(Reservation reservation) {
        reservation.getRoom().setAvailable(false);
        reservations.add(reservation);
        System.out.println("Rezerwacja dokonana: " + reservation);
    }

    public void cancelReservation(Customer customer) {
        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                reservationToCancel = reservation;
                break;
            }
        }
        if (reservationToCancel != null) {
            reservationToCancel.getRoom().setAvailable(true);
            reservations.remove(reservationToCancel);
            System.out.println("Rezerwacja anulowana dla " + customer.getFullName());
        } else {
            System.out.println("Nie znaleziono rezerwacji dla " + customer.getFullName());
        }
    }

    public void listReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Brak rezerwacji.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }

    public void listRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
