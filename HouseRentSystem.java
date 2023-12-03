import java.util.Scanner;
public class HouseRentSystem {
    public static void main(String[] args) {
        HousingSociety society = new HousingSociety();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. Enter details and allocate a house");
            System.out.println("2. Collect rent");
            System.out.println("3. Vacate a house");
            System.out.println("4. Display houses");
            System.out.println("0. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    society.enterNameAndAllocateHouse();
                    break;

                case 2:
                    society.collectRent();
                    break;

                case 3:
                    society.vacateHouse();
                    break;

                case 4:
                    society.displayHouses();
                    break;

                case 0:
                    System.out.println("Closing the System. Goodbye!!!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

    }
}

