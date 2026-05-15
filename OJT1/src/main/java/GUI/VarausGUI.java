package GUI;

import Logic.MokkiLogic;
import Logic.RaporttiLogic;
import Logic.TallennusLogic;
import Logic.VarausLogic;
import Structs.Mokki;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class VarausGUI extends Application {
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

    TallennusLogic tallennus = new TallennusLogic();
    MokkiLogic mokkiLogic = new MokkiLogic(tallennus);
    RaporttiLogic raporttiLogic = new RaporttiLogic();
    VarausLogic varausLogic = new VarausLogic(tallennus, mokkiLogic);

    public static void main(String[] args) {
        launch(args);
    }

    public void luoMokkiKentat() {
        if (!kentat.getChildren().isEmpty()) kentat.getChildren().clear();
        TextField mokki_ID = new TextField();
        Label mokki_IDLabel = new Label("Mökki ID:");
        mokki_IDLabel.setLabelFor(mokki_ID);
        kentat.add(mokki_IDLabel, 0, 0);
        kentat.add(mokki_ID, 0, 1);

        TextField mokkiOmistaja = new TextField();
        Label mokkiOmistajaLabel = new Label("Mokin Omistaja:");
        mokkiOmistajaLabel.setLabelFor(mokkiOmistaja);
        kentat.add(mokkiOmistajaLabel, 0, 2);
        kentat.add(mokkiOmistaja, 0, 3);

        TextField mokkiOsoite = new TextField();
        Label mokkiOsoiteLabel = new Label("Osoite:");
        mokkiOsoiteLabel.setLabelFor(mokkiOsoite);
        kentat.add(mokkiOsoiteLabel, 1, 0);
        kentat.add(mokkiOsoite, 1, 1);

        TextField mokkiMaxAsukkaat = new TextField();
        Label mokkiMaxAsukkaatLabel = new Label("Max Asukkaat:");
        mokkiMaxAsukkaatLabel.setLabelFor(mokkiMaxAsukkaat);
        kentat.add(mokkiMaxAsukkaatLabel, 1, 2);
        kentat.add(mokkiMaxAsukkaat, 1, 3);
    }

    public void luoMokkiLista() {
        List<Mokki> mokkilista = mokkiLogic.haeMokit();
        for (int i = 0; i < mokkiLogic.haeMokit().size(); i++) {
            Mokki m = mokkilista.get(i);
            String s = m.getID() + ", ";
            s += m.getOsoite();
            TextField mokkiTextField = new TextField(s);
            mokkiTextField.setEditable(false);
            scrollPane.setContent(mokkiTextField);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        luoMokkiKentat();

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
