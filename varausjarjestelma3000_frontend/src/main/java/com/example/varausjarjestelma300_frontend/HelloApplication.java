package com.example.varausjarjestelma300_frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    Button mokit          = new Button("Mökit");
    Button laskut         = new Button("Laskut");
    Button varaukset      = new Button("Varaukset");
    Button asiakkaat      = new Button("Asiakkaat");
    Button raportit       = new Button("Raportit");

    Button muokkaa        = new Button("Muokkaa");
    Button tallenna       = new Button("Tallenna");

    ScrollPane scrollPane = new ScrollPane();
    HBox nakymavalinta    = new HBox(mokit, laskut, varaukset, asiakkaat, raportit);
    GridPane kentat       = new GridPane(10,10);
    HBox lisatietonapit   = new HBox(muokkaa, tallenna);
    VBox lisatietonakyma  = new VBox(10,kentat, lisatietonapit);
    HBox nakyma           = new HBox(scrollPane, lisatietonakyma);
    @Override
    public void start(Stage primaryStage) throws Exception {

        raportit.setOnAction(e -> {
            Text text = new Text("TODO: Raportti namiskat tähän");
            VBox vbox = new VBox(text);
            Scene scene = new Scene(vbox, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        VBox root = new VBox(nakymavalinta, nakyma);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Varausjärjestelmä 3000");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}