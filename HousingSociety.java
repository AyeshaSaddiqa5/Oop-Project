package com.example.projectgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
public class HousingSociety extends Application {
    private List<House> houses;
    private static final String FILE_NAME = "society_data.ser";
    private Stage primaryStage;
    private Label messageLabel;

    private static final String ADMIN_EMAIL = "a";
    private static final String ADMIN_PASSWORD = "a";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.houses = loadFromFile(FILE_NAME);
        initializeHouses();
        addShutdownHook();

        showAdminLoginScene();
    }

    private void showAdminLoginScene() {
        BorderPane adminLoginLayout = new BorderPane();
        Scene adminLoginScene = new Scene(adminLoginLayout, 500, 500);
        //adminLoginScene.setFill(Color.LIGHTSKYBLUE);
       // adminLoginLayout.setStyle("-fx-background-color: #E0FFFF;");
        adminLoginLayout.setStyle("-fx-background-color: #70FF70;");
        Image logo = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\log.png");
        ImageView logoView = new ImageView(logo);
        adminLoginLayout.setCenter(logoView);

        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));

        Label emailLabel = new Label("Enter your email:");
        emailLabel.setStyle("-fx-text-fill: #006400;");

        TextField emailField = new TextField();

        Label passwordLabel = new Label("Enter your password:");
        passwordLabel.setStyle("-fx-text-fill: #006400;");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
                showWelcomeScene();
            } else {
                showMessage("Incorrect email or password", Alert.AlertType.ERROR);
            }
        });

      //  messageLabel = new Label();

        loginBox.getChildren().add(emailLabel);
        loginBox.getChildren().add(emailField);
        loginBox.getChildren().add(passwordLabel);
        loginBox.getChildren().add(passwordField);
        loginBox.getChildren().add(loginButton);
      //  loginBox.getChildren().add(messageLabel);
        adminLoginLayout.setBottom(loginBox);

        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(adminLoginScene);

        primaryStage.show();
    }

    private void showWelcomeScene() {
        BorderPane welcomeLayout = new BorderPane();
        welcomeLayout.setStyle("-fx-background-color: #70FF70;");


        Image welcomeImage = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\WelcomeImage.jpeg");

        ImageView welcomeImageView = new ImageView(welcomeImage);
        welcomeImageView.setFitWidth(500);
        welcomeImageView.setFitHeight(300);
        welcomeLayout.setCenter(welcomeImageView);



        Label welcomeLabel = new Label("Welcome to GreenLand Housing Society!\nStep in for Better Future\nLive Green\nTo be Green\nStep in for Your DREAMS");
        welcomeLabel.setStyle("fx-font-family: 'Didot';-fx-font-size: 15;-fx-text-fill: #006400;-fx-font-weight:bold ");
        welcomeLayout.setTop(welcomeLabel);

        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        nextButton.setOnAction(e -> showMainMenu());
        welcomeLayout.setBottom(nextButton);


        Scene welcomeScene = new Scene(welcomeLayout, 500, 500);
        primaryStage.setScene(welcomeScene);
    }

    private void showMainMenu() {
        BorderPane mainMenuLayout = new BorderPane();

        mainMenuLayout.setStyle("-fx-background-color: #70FF70;");

        Image image = new Image("D:\\Computer Science\\Semester2\\OOP\\projectgui\\src\\log.png");
        ImageView imageView = new ImageView(image);
        mainMenuLayout.setLeft(imageView);

        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setPadding(new Insets(20));


        VBox firstLine = new VBox(10);
        Button allocateHouseButton = new Button("Allocate House");
        allocateHouseButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        allocateHouseButton.setOnAction(e -> showAllocateHouseScene());

        Button collectRentButton = new Button("Collect Rent");
        collectRentButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        collectRentButton.setOnAction(e -> showCollectRentScene());
        firstLine.getChildren().addAll(allocateHouseButton, collectRentButton);

        VBox secondLine = new VBox(10);
        Button vacateHouseButton = new Button("Vacate House");
        vacateHouseButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        vacateHouseButton.setOnAction(e -> showVacateHouseScene());

        Button displayHousesButton = new Button("Display Houses");
        displayHousesButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        displayHousesButton.setOnAction(e -> showDisplayHousesScene());

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

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

    private void showAllocateHouseScene() {
        VBox allocateHouseLayout = new VBox(10);
        allocateHouseLayout.setStyle("-fx-background-color: #70FF70;");

        allocateHouseLayout.setPadding(new Insets(20));

        Label nameLabel = new Label("Enter name of Tenant:");
        nameLabel.setStyle("-fx-text-fill: #006400;");

        TextField nameField = new TextField();

        Label idLabel = new Label("Enter ID:");
        idLabel.setStyle("-fx-text-fill: #006400;");

        TextField idField = new TextField();

        Label houseNumberLabel = new Label("Enter the house number to allocate:");
        houseNumberLabel.setStyle("-fx-text-fill: #006400;");

        TextField houseNumberField = new TextField();

        Button allocateButton = new Button("Allocate House");
        allocateButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");


        allocateButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            int houseNumber = Integer.parseInt(houseNumberField.getText());

            Person person = new Person(name, id);

            for (House house : houses) {
                if (house.getHouseNumber() == houseNumber) {
                    if (house.getStatus() == HouseStatus.FOR_RENT) {
                        house.occupyHouse(person);
                        showMessage("House allocated to " + name, Alert.AlertType.INFORMATION);
                    } else {
                        showMessage("House " + houseNumber + " is already occupied", Alert.AlertType.WARNING);
                    }
                    break;
                }
            }
        });

        allocateHouseLayout.getChildren().add(nameLabel);
        allocateHouseLayout.getChildren().add(nameField);
        allocateHouseLayout.getChildren().add(idLabel);
        allocateHouseLayout.getChildren().add(idField);
        allocateHouseLayout.getChildren().add(houseNumberLabel);
        allocateHouseLayout.getChildren().add(houseNumberField);
        allocateHouseLayout.getChildren().add(allocateButton);
        allocateHouseLayout.getChildren().add(createBackButton());

        messageLabel = new Label();

        allocateHouseLayout.getChildren().addAll(messageLabel);

        Scene allocateHouseScene = new Scene(allocateHouseLayout, 350, 300);
        primaryStage.setScene(allocateHouseScene);
    }

    private void showCollectRentScene() {
        VBox collectRentLayout = new VBox(10);
        collectRentLayout.setStyle("-fx-background-color: #70FF70;");

        collectRentLayout.setPadding(new Insets(20));

        Label flatNumberLabel = new Label("Enter the flat number to collect rent:");
        flatNumberLabel.setStyle("-fx-text-fill: #006400;");

        TextField flatNumberField = new TextField();

        Button collectRentButton = new Button("Collect Rent");
        collectRentButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

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

    private void showVacateHouseScene() {
        VBox vacateHouseLayout = new VBox(10);
        vacateHouseLayout.setStyle("-fx-background-color: #70FF70;");

        vacateHouseLayout.setPadding(new Insets(20));

        Label houseNumberLabel = new Label("Enter the house number to vacate:");
        houseNumberLabel.setStyle("-fx-text-fill: #006400;");

        TextField houseNumberField = new TextField();

        Button vacateButton = new Button("Vacate House");
        vacateButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        vacateButton.setOnAction(e -> {
            int houseNumber = Integer.parseInt(houseNumberField.getText());

            for (House house : houses) {
                if (house.getHouseNumber() == houseNumber) {
                    if (house.getStatus() == HouseStatus.OCCUPIED) {
                        house.vacateHouse();
                        house.resetHouse();
                        showMessage("House vacated: " + houseNumber, Alert.AlertType.INFORMATION);
                    } else {
                        showMessage("House " + houseNumber + " is not occupied", Alert.AlertType.WARNING);
                    }
                    break;
                }
            }
        });

        vacateHouseLayout.getChildren().addAll(houseNumberLabel, houseNumberField, vacateButton, createBackButton());

        messageLabel = new Label(); // Initialize the message label

        vacateHouseLayout.getChildren().addAll(messageLabel);

        Scene vacateHouseScene = new Scene(vacateHouseLayout, 350, 300);
        primaryStage.setScene(vacateHouseScene);
    }

    private void showDisplayHousesScene() {
        VBox displayHousesLayout = new VBox(10);
        displayHousesLayout.setStyle("-fx-background-color: #70FF70;");

        displayHousesLayout.setPadding(new Insets(20));

        TextArea housesTextArea = new TextArea();
        housesTextArea.setEditable(false);

        for (House house : houses) {
            if (house.getStatus() == HouseStatus.OCCUPIED) {
                housesTextArea.appendText("House " + house.getHouseNumber() + " - " +
                        "Tenant: " + (house.getTenant() != null ? house.getTenant().getName() : "None") +
                        ", Rent Paid: " + house.isRentPaid() + "\n");
            }
        }

          displayHousesLayout.getChildren().add(housesTextArea);
        displayHousesLayout.getChildren().add(createBackButton());
        messageLabel = new Label();

        displayHousesLayout.getChildren().addAll(messageLabel);

        Scene displayHousesScene = new Scene(displayHousesLayout, 400, 300);
        primaryStage.setScene(displayHousesScene);
    }

    private Button createBackButton() {
        Button backButton = new Button("Back to Main Menu");
        backButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;");

        backButton.setOnAction(e -> showMainMenu());
        return backButton;
    }

    private void initializeHouses() {
        if (houses.isEmpty()) {
            int houseNumber = 1;
            for (int floor = 1; floor <= 20; floor++) {
                for (int flat = 1; flat <= 10; flat++) {
                    houses.add(new Flat(houseNumber++, HouseStatus.FOR_RENT, 20000.0));
                }
            }
        }
    }

    private void addShutdownHook() {
        primaryStage.setOnCloseRequest(event -> saveToFile(FILE_NAME));
    }

    private void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(houses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<House> loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<House>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Method to show messages on the GUI
    private void showMessage(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}