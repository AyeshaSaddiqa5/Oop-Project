import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HousingSociety {
    private List<House> houses;
    
private static final String FILE_NAME = "society_data.ser";
    private Stage primaryStage;
    private static final String ADMIN_EMAIL = "h";
    private static final String ADMIN_PASSWORD = "y";
    public HousingSociety() {
        this.houses = new ArrayList<>();
        initializeHouses();
    }

    private void showAdminLoginScene() {
        BorderPane adminLoginLayout = new BorderPane();

        // Add logo to the center
        Image logo = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\log.png");
        ImageView logoView = new ImageView(logo);
        adminLoginLayout.setCenter(logoView);

        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));

        Label emailLabel = new Label("Enter your email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Enter your password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
                showWelcomeScene();
            } else {
                showMessage("Incorrect email or password", Alert.AlertType.ERROR);
            }
        });

        messageLabel = new Label();

        loginBox.getChildren().add(emailLabel);
        loginBox.getChildren().add(emailField);
        loginBox.getChildren().add(passwordLabel);
        loginBox.getChildren().add(passwordField);
        loginBox.getChildren().add(loginButton);
        loginBox.getChildren().add(messageLabel);
        adminLoginLayout.setBottom(loginBox);
        Scene adminLoginScene = new Scene(adminLoginLayout, 500, 500);
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(adminLoginScene);

        primaryStage.show();
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
    private void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(houses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<House> loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<House>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }


}
