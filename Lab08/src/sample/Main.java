package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.*;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;

public class Main extends Application {
    private String currentFileName;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 08 Solution");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Button btApp1 = new Button("New");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("Open");
        btApp2.setPrefWidth(200);

        grid.add(btApp1, 0,1);
        grid.add(btApp2, 0,2);

        Scene mainScene = new Scene(grid, 300, 275);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        btApp1.setOnAction(new EventHandler<ActionEvent>() { //New
            @Override
            public void handle(ActionEvent actionEvent) {
                TableView table = getTable();

                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(primaryStage);

                currentFileName = "new.CSV";
                File file = new File(selectedDirectory.getAbsolutePath() + "/" + currentFileName);

                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                primaryStage.setTitle(currentFileName);
                DisplayTable(file, table, mainScene, primaryStage);
            }
        });
        btApp2.setOnAction(new EventHandler<ActionEvent>() { //Open
            @Override
            public void handle(ActionEvent actionEvent) {
                TableView table = getTable();
                File file = chooseFile(primaryStage);

                try {
                    table.setItems(load(file));
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                primaryStage.setTitle(currentFileName);
                DisplayTable(file, table, mainScene, primaryStage);
            }
        });
    }

    private void DisplayTable(File file, TableView table, Scene mainScene, Stage primaryStage){
        Button btReturn = new Button("Exit");
        btReturn.setPrefWidth(80);
        btReturn.setOnAction(e -> {
            System.out.println("PUSHED");
            primaryStage.setTitle("Lab 08 Solution");
            primaryStage.setScene(mainScene);
        });

        Button btSave = new Button("Save");
        btSave.setPrefWidth(80);
        btSave.setOnAction(e -> {
            System.out.println("PUSHED2");
            save(file, table);
        });

        Button btSaveAs = new Button("Save As");
        btSaveAs.setPrefWidth(80);
        btSaveAs.setOnAction(e -> {
            System.out.println("PUSHED3");
            saveAs(primaryStage, table);
        });

        Button btAdd = new Button("Add");

        Label SIDLabel = new Label("SID:");
        TextField SIDBox = new TextField();

        Label assignLabel = new Label("Assignments:");
        TextField assignBox = new TextField();

        Label midtermLabel = new Label("Midterm:");
        TextField midtermBox = new TextField();

        Label finalLabel = new Label("Final Exam:");
        TextField finalBox = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(SIDLabel,0,0);
        gridPane.add(SIDBox,1,0);

        gridPane.add(assignLabel,2,0);
        gridPane.add(assignBox,3,0);

        gridPane.add(midtermLabel,0,1);
        gridPane.add(midtermBox,1,1);

        gridPane.add(finalLabel,2,1);
        gridPane.add(finalBox,3,1);

        gridPane.setPadding(new Insets(5, 0, 5, 12));
        gridPane.setHgap(5);
        gridPane.setVgap(10);

        btAdd.setPrefWidth(80);
        btAdd.setOnAction(e -> {
            StudentRecord newStudent = new StudentRecord(SIDBox.getText(), Float.parseFloat(midtermBox.getText()), Float.parseFloat(assignBox.getText()), Float.parseFloat(finalBox.getText()));
            table.getItems().add(newStudent);
            primaryStage.show();
        });

        HBox hBox = new HBox();
        VBox vBox = new VBox();

        hBox.getChildren().addAll(btReturn,btSave,btSaveAs);
        hBox.setPadding(new Insets(5, 0, 5, 12));
        hBox.setSpacing(5);

        gridPane.add(btAdd,4,1);

        vBox.getChildren().addAll(hBox,table, gridPane);

        Scene scene = new Scene(vBox, 1200, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView getTable(){
        TableView table = new TableView();

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

        table.getColumns().addAll(SIDColumn, assignColumn, midtermColumn, fExamColumn, fMarkColumn, letterGradeColumn);
        return table;
    }

    private File chooseFile(Stage stage){
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            currentFileName = file.getName();

            return file;
    }

    private ObservableList<StudentRecord> load(File file) throws IOException{
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String parts[] = line.split(",");
                if(!line.contains("#")) {
                    marks.add(new StudentRecord(parts[0], Float.parseFloat(parts[2]), Float.parseFloat(parts[1]),Float.parseFloat(parts[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return marks;
    }

    private void save(File file, TableView table){
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());

            writer.write("#SID,Assignments,Midterm,Final Exam" + "\n");

            for(int i = 0; i < table.getItems().size(); i++){
                writer.write(table.getItems().get(i).toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void saveAs(Stage stage, TableView table){
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            File myObj = new File(selectedDirectory.getAbsolutePath() + "/" + currentFileName);

            myObj.createNewFile();
            System.out.println("File created: " + myObj.getName());
            FileWriter writer = new FileWriter(selectedDirectory.getAbsolutePath() + "/" + currentFileName);

            writer.write("#SID,Assignments,Midterm,Final Exam" + "\n");

            for(int i = 0; i < table.getItems().size(); i++){
                writer.write(table.getItems().get(i).toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}