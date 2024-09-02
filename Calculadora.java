import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculadora extends Application {

    private TextField display = new TextField();
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);


        display.setEditable(false);
        display.setPrefSize(200, 40);
        grid.add(display, 0, 0, 4, 1);


        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };


        int row = 1;
        int col = 0;
        for (String buttonText : buttons) {
            Button button = new Button(buttonText);
            button.setPrefSize(50, 50);
            grid.add(button, col, row);
            button.setOnAction(e -> processInput(buttonText));  // Manejador de eventos
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }


        Scene scene = new Scene(grid, 230, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculadora");
        primaryStage.show();
    }


    private void processInput(String input) {
        if (input.matches("[0-9]")) {  // Si es un número
            if (start) {
                display.clear();
                start = false;
            }
            display.appendText(input);
        } else if (input.matches("[+\\-*/]")) {  // Si es un operador
            num1 = Double.parseDouble(display.getText());
            operator = input;
            display.clear();
        } else if (input.equals("=")) {  // Si es el botón de igual
            double num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
            start = true;
        } else if (input.equals("C")) {  // Si es el botón de limpiar
            display.clear();
            start = true;
        }
    }


    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/": return num1 / num2;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}