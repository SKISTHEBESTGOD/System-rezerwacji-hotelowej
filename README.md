Opis poszczególnych klas
1. RoomType
Co to jest:
To zwykły enum, czyli zbiór stałych, który określa rodzaje pokoi dostępne w hotelu.
Co zawiera:

SINGLE
DOUBLE
APARTMENT
2. PaymentType
Co to jest:
Podobnie jak RoomType, to enum, który definiuje metody płatności.
Co zawiera:

CASH
BLIK
CARD
3. Room
Co robi:
Reprezentuje pojedynczy pokój hotelowy.
Co ma w sobie:

Numer pokoju (roomNumber).
Typ pokoju (roomType – korzysta z RoomType).
Informację, czy pokój jest dostępny (available).
Jak działa:
Kiedy tworzysz nowy pokój, ustawiasz jego numer i typ, a domyślnie jest on dostępny. Masz metody, żeby pobrać te dane, zmienić stan dostępności oraz metodę, która zwraca opis pokoju jako tekst (np. "Pokój 101 (SINGLE) – Dostępny").
4. Reservation
Co robi:
Ta klasa zapisuje rezerwację pokoju.
Co zawiera:

Obiekt klienta (Customer), który rezerwuje pokój.
Obiekt pokoju (Room), który jest rezerwowany.
Datę zameldowania (checkIn) oraz datę wymeldowania (checkOut).
Jak działa:
Tworzy rezerwację przy podaniu danych klienta, pokoju i daty, a metoda toString() zwraca opis rezerwacji jako tekst.
5. Payment.java
Co robi:
Reprezentuje płatność związaną z rezerwacją.
Co zawiera:

Kwotę płatności (price).
Metodę płatności (paymentType – używa PaymentType).
Jak działa:
Po utworzeniu obiektu Payment można pobrać kwotę i metodę, a metoda toString() wypisuje informacje o płatności.
6. Hotel.java
Co robi:
Jest głównym magazynem danych – przechowuje listę wszystkich pokoi i rezerwacji.
Co zawiera:

Listę pokoi (rooms).
Listę rezerwacji (reservations).
Co można zrobić:
Dodać pokój do systemu (addRoom).
Usunąć pokój (removeRoom).
Pobierać listę wszystkich pokoi lub szukać pokoju po numerze.
Dokonać rezerwacji (makeReservation) – przy rezerwacji zmienia się stan pokoju na niedostępny.
Anulować rezerwację (cancelReservation), co zmienia stan pokoju na dostępny.
Wyświetlać listę pokoi i rezerwacji.
7. PaymentProcessor.java
Co robi:
Symuluje przetwarzanie płatności.
Jak działa:
Metoda processPayment przyjmuje obiekt Payment, wypisuje komunikat o przetwarzaniu i (w tej wersji) zawsze zwraca true, czyli udaną płatność. Możesz to rozszerzyć, jeśli potrzebujesz bardziej złożonej logiki.

8. ReservationValidator.java
Co robi:
Sprawdza, czy rezerwacja ma poprawny zakres dat.
Jak działa:
Metoda isValidDateRange sprawdza, czy data wymeldowania (checkOut) jest późniejsza niż data zameldowania (checkIn).

9. BookingService.java
Co robi:
Łączy w sobie kilka kroków: sprawdza daty, szuka pokoju, przetwarza płatność i w końcu dokonuje rezerwacji.
Jak działa:

Najpierw waliduje daty, korzystając z ReservationValidator.
Sprawdza, czy pokój o podanym numerze istnieje i jest dostępny.
Przetwarza płatność przez PaymentProcessor.
Jeśli wszystko jest okej, tworzy nową rezerwację i wywołuje metodę makeReservation w klasie Hotel.
10. User.java
Co robi:
Jest to klasa abstrakcyjna, z której dziedziczą klasy Customer i Admin.
Co zawiera:

Imię (firstName) i nazwisko (lastName).
Metodę getFullName, która łączy te dwa pola.
Abstrakcyjną metodę displayMenu, którą każda klasa potomna musi zaimplementować.
11. Customer.java
Co robi:
Reprezentuje klienta, który rezerwuje pokoje.
Co zawiera:

Implementację metody displayMenu, która:
Wyświetla menu klienta.
Pozwala sprawdzić listę pokoi, dokonać rezerwacji, anulować rezerwację lub zobaczyć historię rezerwacji.
Przy rezerwacji pobiera od użytkownika numer pokoju, daty, kwotę płatności i metodę płatności, a potem korzysta z BookingService, żeby zarezerwować pokój.
12. Admin.java
Co robi:
Reprezentuje administratora, który zarządza pokojami i rezerwacjami.
Co zawiera:

Implementację metody displayMenu, która:
Pozwala dodawać nowe pokoje (podając numer i wybierając typ).
Usuwać pokoje.
Wyświetlać listę wszystkich pokoi i rezerwacji.
13. Main.java
Co robi:
To klasa wykonawcza z metodą main, która uruchamia cały system i zarządza interakcją z użytkownikiem.
Co zawiera:
Główne menu, gdzie można wybrać rolę (klient, administrator), sprawdzić statystyki hotelu lub przejść do zaawansowanych opcji.
Zaawansowane opcje systemowe:
Reset systemu – czyli anulowanie wszystkich rezerwacji i ustawienie wszystkich pokoi jako dostępnych.
Wyświetlanie szczegółowych statystyk (np. ile jest pokoi, ile zarezerwowanych, a ile dostępnych).
Metody pomocnicze (np. readInt, readDouble, readLocalDate, readNonEmptyString) wbudowane bezpośrednio w tę klasę, które zapewniają walidację danych wejściowych. Dzięki temu, gdy użytkownik wpisze niepoprawne dane, system wyświetli komunikat błędu i poprosi o ponowne wprowadzenie danych.
