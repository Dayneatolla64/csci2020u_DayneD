package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;



public class Main extends Application {
    private static double[] avgHousingPricesByYear = { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};
    private static String[] years = {"2001","2002","2003","2004","2005","2006","2007","2010"};

    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 06");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Average Prices By Year");
        yAxis.setLabel("Prices");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        for(int i = 0; i < avgHousingPricesByYear.length; i++) {

            series1.getData().add(new XYChart.Data(years[i],avgHousingPricesByYear[i]));
            series2.getData().add(new XYChart.Data(years[i],avgCommercialPricesByYear[i]));

        }

        bc.getData().addAll(series1, series2);

        //PieChart
        PieChart pieChart = new PieChart(getAllCategories(ageGroups, purchasesByAgeGroup));

        //Set Items on Table
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.getChildren().addAll(bc, pieChart);

        primaryStage.setScene(new Scene(hBox));
        primaryStage.show();
    }

    public static ObservableList<PieChart.Data> getAllCategories(String[] ageGroups, int[] purchasesByAgeGroup) {
        ObservableList<PieChart.Data> categories = FXCollections.observableArrayList();

        for(int i = 0; i < ageGroups.length; i++){
            categories.add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }

        return categories;
    }

    public static void main(String[] args) {
        launch(args);
    }
    }


