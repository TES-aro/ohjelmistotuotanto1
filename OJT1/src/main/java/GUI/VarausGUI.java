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
    Button poista         = new Button("Poista");
    Button luo            = new Button("Luo");
    HBox poistaJaLuo      = new HBox(poista, luo);

    //Mökki-oliot
    Mokki mokki1 = new Mokki(1, 10, 10);
    Mokki mokki2 = new Mokki(2, 15,  15);
    Mokki mokki3 = new Mokki(3, 20,  20);
    List<Mokki> mokkilista = List.of(mokki1, mokki2, mokki3);

    ScrollPane scrollPane = new ScrollPane();
    VBox listaus          = new VBox(scrollPane, poistaJaLuo);
    HBox nakymavalinta    = new HBox(mokit, laskut, varaukset, asiakkaat, raportit);
    GridPane kentat       = new GridPane(10,10);
    HBox lisatietonapit   = new HBox(muokkaa, tallenna);
    VBox lisatietonakyma  = new VBox(10,kentat, lisatietonapit);
    HBox nakyma           = new HBox(listaus, lisatietonakyma);

    public static void main(String[] args) {
        launch(args);
    }

    // Sisältää kaikki asetukset ja toiminnot näkymän osalta
    public void nakymaAsetukset() {
        mokit.setDisable(true);
        poistaJaLuo.setSpacing(10);
        lisatietonapit.setSpacing(10);
        lisatietonakyma.setSpacing(10);
        listaus.setSpacing(10);
        nakyma.setSpacing(10);
        scrollPane.setPrefSize(300, 200);
    }

    // Luo tekstikentät mökin tietojen näyttämiseen
    public void luoMokkiKentat() {
        if (!kentat.getChildren().isEmpty()) {  kentat.getChildren().clear();  }
        TextField mokki_ID = new TextField();
        mokki_ID.setEditable(false);
        Label mokki_IDLabel = new Label("Mökki ID:");
        mokki_IDLabel.setLabelFor(mokki_ID);
        kentat.add(mokki_IDLabel, 0, 0);
        kentat.add(mokki_ID, 0, 1);

        TextField mokkiOmistaja = new TextField();
        mokkiOmistaja.setEditable(false);
        Label mokkiOmistajaLabel = new Label("Mokin Omistaja:");
        mokkiOmistajaLabel.setLabelFor(mokkiOmistaja);
        kentat.add(mokkiOmistajaLabel, 0, 2);
        kentat.add(mokkiOmistaja, 0, 3);

        TextField mokkiOsoite = new TextField();
        mokkiOsoite.setEditable(false);
        Label mokkiOsoiteLabel = new Label("Osoite:");
        mokkiOsoiteLabel.setLabelFor(mokkiOsoite);
        kentat.add(mokkiOsoiteLabel, 1, 0);
        kentat.add(mokkiOsoite, 1, 1);

        TextField mokkiMaxAsukkaat = new TextField();
        mokkiMaxAsukkaat.setEditable(false);
        Label mokkiMaxAsukkaatLabel = new Label("Max Asukkaat:");
        mokkiMaxAsukkaatLabel.setLabelFor(mokkiMaxAsukkaat);
        kentat.add(mokkiMaxAsukkaatLabel, 1, 2);
        kentat.add(mokkiMaxAsukkaat, 1, 3);

        TextField varausTila  = new TextField();
        varausTila.setEditable(false);
        Label varausTilaLabel = new Label("Varattu:");
        varausTilaLabel.setLabelFor(varausTila);
        kentat.add(varausTilaLabel, 0, 4);
        kentat.add(varausTila, 0, 5);
    }

    public void luoLaskuKentat() {
        if (!kentat.getChildren().isEmpty()) {  kentat.getChildren().clear();  }
        TextField laskuID = new TextField();
        laskuID.setEditable(false);
        Label lasku_IDLabel = new Label("Lasku ID:");
        lasku_IDLabel.setLabelFor(laskuID);
        kentat.add(lasku_IDLabel, 0, 0);
        kentat.add(laskuID, 0, 1);

        TextField varaus = new TextField();
        varaus.setEditable(false);
        Label varausLabel = new Label("Varaus ID:");
        varausLabel.setLabelFor(varaus);
        kentat.add(varausLabel, 0, 2);
        kentat.add(varaus, 0, 3);

        TextField pvm = new TextField();
        pvm.setEditable(false);
        Label pvmLabel = new Label("Päivämäärä:");
        pvmLabel.setLabelFor(pvm);
        kentat.add(pvmLabel, 1, 0);
        kentat.add(pvm, 1, 1);

        TextField erapaiva = new TextField();
        erapaiva.setEditable(false);
        Label erapaivaLabel = new Label("Eräpäivä:");
        erapaivaLabel.setLabelFor(erapaiva);
        kentat.add(erapaivaLabel, 1, 2);
        kentat.add(erapaiva, 1, 3);

        TextField laskuTila = new TextField();
        laskuTila.setEditable(false);
        Label laskuTilaLabel = new Label("Tila:");
        laskuTilaLabel.setLabelFor(laskuTila);
        kentat.add(laskuTilaLabel, 0, 4);
        kentat.add(laskuTila, 0, 5);

        TextField loppuSumma = new TextField();
        loppuSumma.setEditable(false);
        Label loppuSummaLabel = new Label("Varattu:");
        laskuTilaLabel.setLabelFor(loppuSumma);
        kentat.add(loppuSummaLabel, 1, 4);
        kentat.add(loppuSumma, 1, 5);
    }

    public void luoVarausKentat() {
        if (!kentat.getChildren().isEmpty()) {  kentat.getChildren().clear();  }
        TextField varausID = new TextField();
        varausID.setEditable(false);
        Label varaus_IDLabel = new Label("Varaus ID:");
        varaus_IDLabel.setLabelFor(varausID);
        kentat.add(varaus_IDLabel, 0, 0);
        kentat.add(varausID, 0, 1);

        TextField asiakasID = new TextField();
        asiakasID.setEditable(false);
        Label asiakas_IDLabel = new Label("Asiakas ID:");
        asiakas_IDLabel.setLabelFor(asiakasID);
        kentat.add(asiakas_IDLabel, 1, 0);
        kentat.add(asiakasID, 1, 1);

        TextField mokki_ID = new TextField();
        mokki_ID.setEditable(false);
        Label mokki_IDLabel = new Label("Mökki ID:");
        mokki_IDLabel.setLabelFor(mokki_ID);
        kentat.add(mokki_IDLabel, 0, 2);
        kentat.add(mokki_ID, 0, 3);

        TextField alkuPvm = new TextField();
        alkuPvm.setEditable(false);
        Label alkuPvmLabel = new Label("Alkupäivämäärä:");
        alkuPvmLabel.setLabelFor(alkuPvm);
        kentat.add(alkuPvmLabel, 1, 2);
        kentat.add(alkuPvm, 1, 3);

        TextField loppuPvm = new TextField();
        loppuPvm.setEditable(false);
        Label loppuPvmLabel = new Label("Loppupäivämäärä:");
        loppuPvmLabel.setLabelFor(loppuPvm);
        kentat.add(loppuPvmLabel, 0, 4);
        kentat.add(loppuPvm, 0, 5);

        TextField hintaPerYo = new TextField();
        hintaPerYo.setEditable(false);
        Label hintaPerYoLabel = new Label("Hinta/yö:");
        hintaPerYoLabel.setLabelFor(hintaPerYo);
        kentat.add(hintaPerYoLabel, 1, 4);
        kentat.add(hintaPerYo, 1, 5);
    }

    public void luoAsiakasKentat() {
        if (!kentat.getChildren().isEmpty()) {  kentat.getChildren().clear();  }
        TextField asiakasID = new TextField();
        asiakasID.setEditable(false);
        Label asiakas_IDLabel = new Label("Asiakas ID:");
        asiakas_IDLabel.setLabelFor(asiakasID);
        kentat.add(asiakas_IDLabel, 0, 0);
        kentat.add(asiakasID, 0, 1);

        TextField etuNimi = new TextField();
        etuNimi.setEditable(false);
        Label etuNimiLabel = new Label("Etunimi:");
        etuNimiLabel.setLabelFor(etuNimi);
        kentat.add(etuNimiLabel, 0, 2);
        kentat.add(etuNimi, 0, 3);

        TextField sukuNimi = new TextField();
        sukuNimi.setEditable(false);
        Label sukuNimiLabel = new Label("Sukunimi:");
        sukuNimiLabel.setLabelFor(sukuNimi);
        kentat.add(sukuNimiLabel, 1, 2);
        kentat.add(sukuNimi, 1, 3);

        TextField puhNro = new TextField();
        puhNro.setEditable(false);
        Label puhNroLabel = new Label("Puhelinnumero:");
        puhNroLabel.setLabelFor(puhNro);
        kentat.add(puhNroLabel, 1, 0);
        kentat.add(puhNro, 1, 1);
    }

    public void asetaKentatMuokattaviksi() {
        kentat.getChildren().forEach(node -> {
            if (node instanceof TextField textField) {
                textField.setEditable(true);
            }
        });
    }

    //TODO: luo mökkilista, lisäksi funktiot muille listoille
    public void luoMokkiLista() {
        for (Mokki mokki : mokkilista) {
            Text text = new Text(mokki.toString());
            scrollPane.setContent(text);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        luoMokkiKentat();
        nakymaAsetukset();
        luoMokkiLista();

        mokit.setOnAction(e -> {
            luoMokkiKentat();
            mokit.setDisable(true);
            laskut.setDisable(false);
            varaukset.setDisable(false);
            asiakkaat.setDisable(false);
        });
        laskut.setOnAction(e -> {
            luoLaskuKentat();
            mokit.setDisable(false);
            laskut.setDisable(true);
            varaukset.setDisable(false);
            asiakkaat.setDisable(false);
        });
        varaukset.setOnAction(e -> {
            luoVarausKentat();
            mokit.setDisable(false);
            laskut.setDisable(false);
            varaukset.setDisable(true);
            asiakkaat.setDisable(false);
        });
        asiakkaat.setOnAction(e -> {
            luoAsiakasKentat();
            mokit.setDisable(false);
            laskut.setDisable(false);
            varaukset.setDisable(false);
            asiakkaat.setDisable(true);
        });

        raportit.setOnAction(e -> {
            Text text = new Text("TODO: Raportti namiskat tähän");
            VBox vbox = new VBox(text);
            Scene scene = new Scene(vbox, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        muokkaa.setOnAction(e -> {asetaKentatMuokattaviksi();});

        //TODO: tallenna muokatut tiedot tietokantaan
        tallenna.setOnAction(e -> {});

        //TODO: luo uusi juttu mikänytonkaa tietokantaan
        luo.setOnAction(e -> {});

        //TODO: poista valittu juttu tietokannasta
        poista.setOnAction(e -> {});

        VBox root = new VBox(nakymavalinta, nakyma);
        root.setSpacing(30);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Varausjärjestelmä 3000");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

