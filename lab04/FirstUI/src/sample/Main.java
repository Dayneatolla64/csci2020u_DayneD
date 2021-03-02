package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 04 Solution");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);

        String[] prompts = {"Username", "Password", "Full Name", "Email", "Phone #"};
        TextField[] inputs = new TextField[prompts.length];

        for(int i = 0; i < prompts.length; i++) {
            Label label = new Label(prompts[i] + ":");
            inputs[i] = new TextField();
            inputs[i].setPromptText(prompts[i]);

            GridPane.setConstraints(label,0, i);
            GridPane.setConstraints(inputs[i], 1, i);

            grid.getChildren().addAll(label, inputs[i]);
        }

        //Date Dropdown
        Label dateLabel = new Label("Date of Birth: ");
        DatePicker dateText = new DatePicker();
        dateText.setPromptText("Date of Birth");
        GridPane.setConstraints(dateText,1, prompts.length);
        GridPane.setConstraints(dateLabel,0, prompts.length);

        //Register Button
        Button regButton = new Button("Register");
        regButton.setOnAction(e -> print(inputs, dateText));

        //Initalize GridPlane
        GridPane.setConstraints(regButton,1, prompts.length + 1);
        grid.getChildren().addAll(dateLabel, dateText, regButton);

        primaryStage.setScene(new Scene(grid, 480, 270));

        primaryStage.show();
    }

    public void print(TextField[] arr, DatePicker date) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].getText().length() != 0)
                System.out.println(arr[i].getText());
        }

        if(date.getValue() != null)
            System.out.println(date.getValue());
    }

    public static void main(String[] args) {
        launch(args);

    }
}
