import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HousingSociety {
    private List<House> houses;

    public HousingSociety() {
        this.houses = new ArrayList<>();
        initializeHouses();
    }

    private void initializeHouses() {
        int houseNumber = 1;
        for (int floor = 1; floor <= 20; floor++) {
            for (int flat = 1; flat <= 10; flat++) {
                houses.add(new Flat(houseNumber++, HouseStatus.FOR_RENT, 20000.0));
            }
        }
    }

    public void displayHouses() {
        System.out.println("Houses currently on rent:");
        for (House house : houses) {
            if (house.getStatus() == HouseStatus.OCCUPIED) {
                house.displayDetails();
            }
        }
    }


    public void enterNameAndAllocateHouse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your ID:");
        String id = scanner.nextLine();
        Person person = new Person(name, id);

        System.out.println("Enter the house number to allocate:");
        int houseNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (House house : houses) {
            if (house.getHouseNumber() == houseNumber) {
                house.occupyHouse(person);
                return;
            }
        }
        System.out.println("House " + houseNumber + " not found.");
    }

    public void collectRent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the flat number to collect rent:");
        int flatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (House house : houses) {
            if (house.getHouseNumber() == flatNumber && house.getStatus() == HouseStatus.OCCUPIED) {
                house.payRent();
                return;
            }
        }
        System.out.println("Flat " + flatNumber + " not found or not occupied.");
    }


    public void vacateHouse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the house number to vacate:");
        int houseNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (House house : houses) {
            if (house.getHouseNumber() == houseNumber) {
                house.vacateHouse();
                return;
            }
        }
        System.out.println("House " + houseNumber + " not found.");
    }



}
