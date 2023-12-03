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
    private void showWelcomeScene() {
        BorderPane welcomeLayout = new BorderPane();


        Image welcomeImage = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\log.png");
        ImageView welcomeImageView = new ImageView(welcomeImage);
        welcomeLayout.setCenter(welcomeImageView);


        Label welcomeLabel = new Label("Welcome to Housing Society!");
        welcomeLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
        welcomeLayout.setTop(welcomeLabel);


        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> showMainMenu());
        welcomeLayout.setBottom(nextButton);


        Scene welcomeScene = new Scene(welcomeLayout, 500, 500);
        primaryStage.setScene(welcomeScene);
    }
    private void showMainMenu() {
        BorderPane mainMenuLayout = new BorderPane();

        
        Image image = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\log.png");
        ImageView imageView = new ImageView(image);
        mainMenuLayout.setLeft(imageView);

        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setPadding(new Insets(20));

        
        VBox firstLine = new VBox(10);
        Button allocateHouseButton = new Button("Allocate House");
        allocateHouseButton.setOnAction(e -> showAllocateHouseScene());

        Button collectRentButton = new Button("Collect Rent");
        collectRentButton.setOnAction(e -> showCollectRentScene());
        firstLine.getChildren().addAll(allocateHouseButton, collectRentButton);

        VBox secondLine = new VBox(10);
        Button vacateHouseButton = new Button("Vacate House");
        vacateHouseButton.setOnAction(e -> showVacateHouseScene());

        Button displayHousesButton = new Button("Display Houses");
        displayHousesButton.setOnAction(e -> showDisplayHousesScene());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());
        secondLine.getChildren().addAll(vacateHouseButton, displayHousesButton, exitButton);

        buttonsLayout.getChildren().addAll(firstLine, secondLine);

        messageLabel = new Label(); 

        buttonsLayout.getChildren().addAll(messageLabel);

        mainMenuLayout.setRight(buttonsLayout);

        Scene mainMenuScene = new Scene(mainMenuLayout, 500, 300);
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Housing Society Management");
        primaryStage.show();
    }
    private void showCollectRentScene() {
        VBox collectRentLayout = new VBox(10);
        collectRentLayout.setPadding(new Insets(20));

        Label flatNumberLabel = new Label("Enter the flat number to collect rent:");
        TextField flatNumberField = new TextField();

        Button collectRentButton = new Button("Collect Rent");
        collectRentButton.setOnAction(e -> {
            int flatNumber = Integer.parseInt(flatNumberField.getText());

            for (House house : houses) {
                if (house.getHouseNumber() == flatNumber && house.getStatus() == HouseStatus.OCCUPIED) {
                    house.payRent();
                    showMessage("Rent collected for House " + flatNumber, Alert.AlertType.INFORMATION);
                    break;
                } else if (house.getHouseNumber() == flatNumber && house.getStatus() == HouseStatus.FOR_RENT) {
                    showMessage("House " + flatNumber + " is not occupied yet", Alert.AlertType.WARNING);
                    break;
                }
            }
        });

        collectRentLayout.getChildren().addAll(flatNumberLabel, flatNumberField, collectRentButton, createBackButton());

        messageLabel = new Label(); 

        collectRentLayout.getChildren().addAll(messageLabel);

        Scene collectRentScene = new Scene(collectRentLayout, 350, 300);
        primaryStage.setScene(collectRentScene);
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
