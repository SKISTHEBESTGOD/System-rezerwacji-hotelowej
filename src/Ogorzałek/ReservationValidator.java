package Ogorzałek;

import java.time.LocalDate;

public class ReservationValidator {
    public boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        return checkOut.isAfter(checkIn);
    }
}
