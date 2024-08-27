import javafx.application.Application; //These first imports are for the JavaFX utilities that are needed
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.FileWriter; //This group of imports is just the normal java utilities needed
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Menu Example");

        MenuBar menuBar = new MenuBar(); //Creates the menu bar and menus that a user can select
        Menu fileMenu = new Menu("File");
        Menu dateMenu = new Menu("Date and Time");
        Menu colorMenu = new Menu("Color");
        Menu exitMenu = new Menu("Exit");

        MenuItem printDateTimeItem = new MenuItem("Print Date and Time"); //This creates the drop down menus for each main category
        MenuItem writeToLogFileItem = new MenuItem("Write to Log File");
        MenuItem changeBackgroundColorItem = new MenuItem("Change Background Color");
        MenuItem exitItem = new MenuItem("Exit");

        dateMenu.getItems().add(printDateTimeItem); //Adds what each menu item will do
        fileMenu.getItems().add(writeToLogFileItem);
        colorMenu.getItems().add(changeBackgroundColorItem);
        exitMenu.getItems().add(exitItem);

        TextArea textArea = new TextArea(); //Creates parameters of the menu
        textArea.setPrefWidth(400);
        textArea.setPrefHeight(300);

        printDateTimeItem.setOnAction(event -> { //Method for printing the time and date to the log
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            textArea.appendText(formatter.format(now) + "\n");
        });

        writeToLogFileItem.setOnAction(event -> { //Method for writing the log to the file
            try {
                FileWriter writer = new FileWriter("log.txt", true);
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        changeBackgroundColorItem.setOnAction(event -> { //Method for changing the color of the background
            Random random = new Random();
            Color randomColor = Color.color(random.nextDouble(), 1.0, random.nextDouble());
            textArea.setStyle("-fx-control-inner-background: " + toHex(randomColor) + ";");
        });

        exitItem.setOnAction(event -> { //Method for exiting the program
            primaryStage.close();
        });

        menuBar.getMenus().addAll(fileMenu, dateMenu, colorMenu, exitMenu); //Adds the menus to the main menu bar

        BorderPane layout = new BorderPane(); //Creates the layout in which the window will use
        layout.setTop(menuBar);
        layout.setCenter(textArea);

        Scene scene = new Scene(layout, 600, 400); //Creates the scene for the window
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String toHex(Color color) { //Creates the parameters in which the randomly chosen colors for the background to follow keeping the background colors green
        int r = (int) (color.getRed() * 165);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 145);
        return String.format("#%02X%02X%02X", r, g, b);
    }
}