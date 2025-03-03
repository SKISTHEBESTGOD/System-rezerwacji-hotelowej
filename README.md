# System Rezerwacji Hotelowej

## Biblioteki
**Użyte biblioteki:**
- java.util.Scanner
- java.util.LocalDate
- java.util.List
- java.util.ArrayList
- java.time.format.DateTimeParseException

**Co robią?**

- Scanner:
Umożliwia wprowadzenie danych z klawiatury (np. numeru pokoju, daty, kwoty płatności) oraz zamianę ich na odpowiednie typy danych (np. liczby całkowite, daty).

- LocalDate:
Umożliwia operacje na datach, takie jak porównywanie dat, dodawanie lub odejmowanie dni, miesiący, lat oraz sprawdzanie poprawności zakresu dat (np. dat zameldowania i wymeldowania).

- List:
Używany do przechowywania i zarządzania listami obiektów, takich jak pokoje (Room) i rezerwacje (Reservation).

- ArrayList:
Zapewnia metody do dodawania, usuwania i manipulowania elementami w liście. Używana do przechowywania listy pokoi oraz rezerwacji w systemie hotelowym.

- DateTimeParseException:
Używana do obsługi błędów związanych z nieprawidłowym formatem daty wprowadzanej przez użytkownika. Pomaga w walidacji i zapewnieniu, że daty zameldowania i wymeldowania są poprawnie sformatowane.

## RoomType
**Co to jest:**  
To enum, który określa rodzaje pokoi dostępne w hotelu.

**Co zawiera:**
- SINGLE
- DOUBLE
- APARTMENT

## PaymentType
**Co to jest:**  
Podobnie jak RoomType, to enum, który definiuje metody płatności.

**Co zawiera:**
- CASH
- BLIK
- CARD

## Room
**Co robi:**  
Reprezentuje pojedynczy pokój hotelowy.

**Co ma w sobie:**
- Numer pokoju (roomNumber).
- Typ pokoju (roomType – korzysta z RoomType).
- Informację, czy pokój jest dostępny (available).

**Jak działa:**  
Tworzy nowy pokój, ustawiając jego numer i typ, a domyślnie jest on dostępny. Dostępne są metody pobierające te dane, zmieniające stan dostępności oraz metoda, która zwraca opis pokoju jako tekst (np. "Pokój 101 (SINGLE) – Dostępny").

## Reservation
**Co robi:**  
Ta klasa zapisuje rezerwację pokoju.

**Co zawiera:**
- Obiekt klienta (Customer), który rezerwuje pokój.
- Obiekt pokoju (Room), który jest rezerwowany.
- Datę zameldowania (checkIn) oraz datę wymeldowania (checkOut).

**Jak działa:**  
Tworzy rezerwację, podając dane klienta, pokój i daty, a metoda `toString()` zwraca opis rezerwacji jako tekst.

## Payment
**Co robi:**  
Reprezentuje płatność związaną z rezerwacją.

**Co zawiera:**
- Kwotę płatności (price).
- Metodę płatności (paymentType – używa PaymentType).

**Jak działa:**  
Po utworzeniu obiektu Payment można pobrać kwotę i metodę, a metoda `toString()` wypisuje informacje o płatności.

## Hotel
**Co robi:**  
Jest głównym magazynem danych – przechowuje listę wszystkich pokoi i rezerwacji.

**Co zawiera:**
- Listę pokoi (rooms).
- Listę rezerwacji (reservations).

**Co można zrobić:**
- Dodać pokój do systemu (addRoom).
- Usunąć pokój (removeRoom).
- Pobierać listę wszystkich pokoi lub szukać pokoju po numerze (getAllRooms, getRoomByNumber).
- Dokonać rezerwacji (makeReservation) – przy rezerwacji zmienia się stan pokoju na niedostępny.
- Anulować rezerwację (cancelReservation), co zmienia stan pokoju na dostępny.
- Wyświetlać listę pokoi i rezerwacji (listRooms, listReservations).

## Wyszukiwanie rezerwacji:
- **searchReservationsByCustomer(String customerName):**  
  Ta metoda przeszukuje listę rezerwacji i sprawdza, czy pełne imię i nazwisko klienta zawiera podany ciąg znaków (bez względu na wielkość liter). Jeśli znajdzie pasujące rezerwacje, zwraca je w postaci listy.
- **searchReservationsByRoom(int roomNumber):**  
  Ta metoda przeszukuje rezerwacje i zwraca te, które dotyczą pokoju o podanym numerze.

## PaymentProcessor
**Co robi:**  
Symuluje przetwarzanie płatności.

**Jak działa:**  
Metoda `processPayment` przyjmuje obiekt Payment, wypisuje komunikat o przetwarzaniu i (w tej wersji) zawsze zwraca true, czyli udaną płatność. Możesz to rozszerzyć, jeśli potrzebujesz bardziej złożonej logiki.

## ReservationValidator
**Co robi:**  
Sprawdza, czy rezerwacja ma poprawny zakres dat.

**Jak działa:**  
Metoda `isValidDateRange` sprawdza, czy data wymeldowania (checkOut) jest późniejsza niż data zameldowania (checkIn).

## BookingService
**Co robi:**  
Łączy w sobie kilka kroków: sprawdza daty, szuka pokoju, przetwarza płatność i w końcu dokonuje rezerwacji.

**Jak działa:**
- Najpierw waliduje daty przy użyciu ReservationValidator.
- Sprawdza, czy pokój o podanym numerze istnieje i jest dostępny.
- Przetwarza płatność przez PaymentProcessor.
- Jeśli wszystko jest poprawne, tworzy nową rezerwację i wywołuje metodę `makeReservation` w klasie Hotel.

## User (klasa abstrakcyjna)
**Co robi:**  
Jest to klasa, z której dziedziczą klasy Customer i Admin.

**Co zawiera:**
- Imię (firstName) i nazwisko (lastName).
- Metodę `getFullName`, która łączy te dwa pola.
- Abstrakcyjną metodę `displayMenu`, którą każda klasa potomna musi zaimplementować.

## Customer
**Co robi:**  
Reprezentuje klienta, który rezerwuje pokoje.

**Co zawiera:**
- Implementację metody `displayMenu`, która:
    - Wyświetla menu klienta.
    - Pozwala sprawdzić listę pokoi, dokonać rezerwacji, anulować rezerwację lub zobaczyć historię rezerwacji.
    - Przy rezerwacji pobiera od użytkownika numer pokoju, daty, kwotę płatności i metodę płatności, a potem korzysta z BookingService, żeby zarezerwować pokój.

## Admin
**Co robi:**  
Reprezentuje administratora, który zarządza pokojami i rezerwacjami.

**Co zawiera:**
- Implementację metody `displayMenu`, która:
    - Pozwala dodawać nowe pokoje (podając numer i wybierając typ).
    - Usuwać pokoje.
    - Wyświetlać listę wszystkich pokoi i rezerwacji.

## Main
**Co robi:**  
To klasa wykonawcza z metodą maina, uruchamiająca cały system i zarządzająca interakcją z użytkownikiem.

**Co zawiera:**
- Główne menu, w którym użytkownik może:
    - Wybrać rolę (klient lub administrator).
    - Wyświetlić podstawowe statystyki hotelu.
    - Przejść do zaawansowanych opcji systemowych (reset systemu, szczegółowe statystyki).
    - Wyszukiwać rezerwacje (według nazwiska klienta lub numeru pokoju).
    - Wyświetlić listę wszystkich pokoi i rezerwacji.

## Metody pomocnicze
- **readInt**
- **readDouble**
- **readLocalDate**
- **readNonEmptyString**

## Metody związane z wyszukiwaniem rezerwacji
### W klasie Hotel zostały dodane dwie metody wyszukiwania rezerwacji:

- **searchReservationsByCustomer(String customerName):**
    - **Co robi:** Ta metoda przeszukuje listę rezerwacji i sprawdza, czy pełne imię i nazwisko klienta zawiera podany ciąg znaków (niezależnie od wielkości liter).
    - **Jak działa:** Jeśli znajdzie rezerwacje, które spełniają ten warunek, dodaje je do listy wyników i zwraca tę listę. Dzięki temu możesz szybko znaleźć wszystkie rezerwacje powiązane z danym klientem.

- **searchReservationsByRoom(int roomNumber):**
    - **Co robi:** Ta metoda przeszukuje listę rezerwacji i zwraca te rezerwacje, które dotyczą pokoju o określonym numerze.
    - **Jak działa:** Metoda przechodzi przez listę rezerwacji, sprawdza numer pokoju każdej rezerwacji i dodaje te, które pasują, do listy wyników. Na końcu zwraca listę rezerwacji dla danego pokoju.

Obie te metody są wykorzystywane w menu wyszukiwania rezerwacji w głównej klasie wykonawczej, gdzie użytkownik może wybrać, według jakiego kryterium chce wyszukać rezerwacje.
