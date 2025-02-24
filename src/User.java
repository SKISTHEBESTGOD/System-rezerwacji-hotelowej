import java.util.Scanner;

public abstract class User {
    protected String firstName;
    protected String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Metoda wyświetlająca menu – implementowana osobno w klasach Customer i Admin
    public abstract void displayMenu(Hotel hotel, Scanner scanner);
}
