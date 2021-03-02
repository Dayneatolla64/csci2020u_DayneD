package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 05 Solution");

        //SID Column
        TableColumn<StudentRecord, String> SIDColumn = new TableColumn<>("SID");
        SIDColumn.setMinWidth(200);
        SIDColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));

        //Assignments Column
        TableColumn<StudentRecord, String> assignColumn = new TableColumn<>("Assignments");
        assignColumn.setMinWidth(200);
        assignColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        //Midterm Column
        TableColumn<StudentRecord, String> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(200);
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        //Final Exam Column
        TableColumn<StudentRecord, String> fExamColumn = new TableColumn<>("Final Exam");
        fExamColumn.setMinWidth(200);
        fExamColumn.setCellValueFactory(new PropertyValueFactory<>("Exam"));

        //Final Mark Column
        TableColumn<StudentRecord, String> fMarkColumn = new TableColumn<>("Final Mark");
        fMarkColumn.setMinWidth(200);
        fMarkColumn.setCellValueFactory(new PropertyValueFactory<>("Mark"));

        //Letter Grade Column
        TableColumn<StudentRecord, String> letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setMinWidth(200);
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

        //Init Table
        TableView table = new TableView();
        DataSource data = new DataSource();

        table.setItems(data.getAllMarks());
        table.getColumns().addAll(SIDColumn, assignColumn, midtermColumn, fExamColumn, fMarkColumn, letterGradeColumn);

        //Init VBox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
