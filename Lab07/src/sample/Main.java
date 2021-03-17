package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javafx.scene.chart.*;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Lab 07");

        int[] data = readFile("D:/Programs/Java/Lab07/resources/weatherwarnings-2015.csv");
        String[] dataName = {"Flash Flood","Severe Thunderstorm","Special Marine","Tornado"};
        System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3]);


        PieChart pieChart = new PieChart(getAllData(dataName, data));
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.LEFT);

        root.getChildren().add(pieChart);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int[] readFile(String dir) throws IOException {
        File file = new File(dir);
        Scanner scanner = new Scanner(file);
        int[] data = new int[4];

        while (scanner.hasNext()){
            String token = scanner.next();
            countLine(token, data);
        }

        return data;
    }

    private void countLine(String token, int[] data) {
        if(token.contains("FLASH")){
            data[0]++;
        } else if(token.contains("SEVERE")){
            data[1]++;
        } else if(token.contains("SPECIAL")){
            data[2]++;
        } else if(token.contains("TORNADO")){
            data[3]++;
        }
    }

    private ObservableList<PieChart.Data> getAllData(String[] dataName, int[] data) {
        ObservableList<PieChart.Data> temp = FXCollections.observableArrayList();

        for(int i = 0; i < dataName.length; i++){
            temp.add(new PieChart.Data(dataName[i], data[i]));
        }

        return temp;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
