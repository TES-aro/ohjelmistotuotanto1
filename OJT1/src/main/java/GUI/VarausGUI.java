package GUI;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import Structs.*;

import javafx.application.Application;
import javafx.scene.Node;
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

import java.time.LocalDate;

public class VarausGUI extends Application {
    // Napit näkymän vaihtoon
    Button mokit          = new Button("Mökit");
    Button laskut         = new Button("Laskut");
    Button varaukset      = new Button("Varaukset");
    Button asiakkaat      = new Button("Asiakkaat");
    Button raportit       = new Button("Raportit");

    //Napit tietokannan muutteluun
    Button muokkaa        = new Button("Muokkaa");
    Button tallenna       = new Button("Tallenna");
    Button poista         = new Button("Poista");
    Button luo            = new Button("Luo");
    HBox poistaJaLuo      = new HBox(poista, luo);

    // Pitää kirjaa valitusta näkymästä
    private enum Nakymatyyppi { ASIAKKAAT, VARAUKSET, LASKUT, MOKIT }
    private Nakymatyyppi nykyinenNakyma = Nakymatyyppi.MOKIT;

    // Muuttujat pitämään kirjaa valitusta kohteesta listassa
    private Kayttaja valittuAsiakas = null;
    private Varaus valittuVaraus = null;
    private Lasku valittuLasku = null;
    private Mokki valittuMokki = null;

    // Mökki-oliot testaamiseen
    Mokki mokki1 = new Mokki("Kanada", 10,false, 200);
    Mokki mokki2 = new Mokki("Turku", 15, true, 400);
    Mokki mokki3 = new Mokki("Suomi", 20, false, 600);

    // Kayttaja-oliot testaamiseen
    Kayttaja kayttaja1 = new Kayttaja("Jussi", "Jokinen",0, "jussi.jokinen@example.com", "0401234567");
    Kayttaja kayttaja2 = new Kayttaja("Matti", "Meikäläinen",1, "matti.meikalainen@example.com", "0409876543");
    Kayttaja kayttaja3 = new Kayttaja("Risto", "Rauta",2, "risto.rauta@example.com", "0407654321");
    Kayttaja kayttaja = new Kayttaja("Testi", "Testaaja",3, "testi.testaaja@example.com", "0401234567");

    // Varaus-oliot testaamiseen
    Varaus varaus1 = new Varaus(0, mokki1, kayttaja1, new Date(2026, Calendar.MAY,22), new Date(2026, Calendar.MAY,25), 200.0);
    Varaus varaus2 = new Varaus(1, mokki2, kayttaja2, new Date(2026, Calendar.MAY,22), new Date(2026, Calendar.MAY,27), 400.0);
    Varaus varaus3 = new Varaus(2, mokki3, kayttaja3, new Date(2026, Calendar.MAY,22), new Date(2026, Calendar.MAY,29), 600.0);
    Varaus varaus = new Varaus(3, mokki1, kayttaja, new Date(2026, Calendar.MAY,22), new Date(2026, Calendar.JUNE,1), 300.0);

    // Lasku-oliot testaamiseen
    Lasku lasku1 = new Lasku(kayttaja1, varaus1,new Date(2026, Calendar.JUNE,1),varaus1.getHinta());
    Lasku lasku2 = new Lasku(kayttaja2, varaus2,new Date(2026, Calendar.JUNE,4),varaus2.getHinta());
    Lasku lasku3 = new Lasku(kayttaja3, varaus3,new Date(2026, Calendar.MAY,23),varaus3.getHinta());
    Lasku lasku = new Lasku(kayttaja, varaus,new Date(2026, Calendar.JUNE,1), varaus.getHinta());

    // Listat testiolioiden säilömiseen
    private final List<Kayttaja> asiakasLista = new ArrayList<>(List.of(kayttaja1, kayttaja2, kayttaja3));
    private final List<Varaus> varausLista = new ArrayList<>(List.of(varaus1, varaus2, varaus3));
    private final List<Lasku> laskuLista = new ArrayList<>(List.of(lasku1, lasku2, lasku3));
    private final List<Mokki> mokkiLista = new ArrayList<>(List.of(mokki1, mokki2, mokki3));

    // Panet käyttöliittymäelementtien säilömiseen
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

    // Sisältää näkymän asetuksia
    private void nakymaAsetukset() {
        mokit.setDisable(true);
        poistaJaLuo.setSpacing(10);
        lisatietonapit.setSpacing(10);
        lisatietonakyma.setSpacing(10);
        listaus.setSpacing(10);
        nakyma.setSpacing(10);
        scrollPane.setPrefSize(200, 300);
    }

    // Luo tekstikentät mökin tietojen näyttämiseen
    private void luoMokkiKentat(GridPane kentat) {
        if (!kentat.getChildren().isEmpty()) {  kentat.getChildren().clear();  }
        TextField mokki_ID = new TextField();
        mokki_ID.setEditable(false);
        Label mokki_IDLabel = new Label("Mökki ID:");
        mokki_IDLabel.setLabelFor(mokki_ID);
        kentat.add(mokki_IDLabel, 0, 0);
        kentat.add(mokki_ID, 0, 1);

        TextField mokkiOmistaja = new TextField();
        mokkiOmistaja.setEditable(false);
        Label mokkiOmistajaLabel = new Label("Hinta:");
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

    // Luo tekstikentät laskun tiedoille
    private void luoLaskuKentat(GridPane kentat) {
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

        TextField kokonaisSumma = new TextField();
        kokonaisSumma.setEditable(false);
        Label kokonaisSummaLabel = new Label("Kokonaissumma:");
        kokonaisSummaLabel.setLabelFor(kokonaisSumma);
        kentat.add(kokonaisSummaLabel, 1, 4);
        kentat.add(kokonaisSumma, 1, 5);

        TextField maksaja = new TextField();
        maksaja.setEditable(false);
        Label maksajaLabel = new Label("Maksaja:");
        maksajaLabel.setLabelFor(maksaja);
        kentat.add(maksajaLabel, 0, 6);
        kentat.add(maksaja, 0, 7);
    }

    // Luo tekstikentät varaustiedoille
    private void luoVarausKentat(GridPane kentat) {
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

    // Luo tekstikentät asiakkaan tiedoille
    private void luoAsiakasKentat(GridPane kentat) {
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
        kentat.add(etuNimiLabel, 1, 0);
        kentat.add(etuNimi, 1, 1);

        TextField sukuNimi = new TextField();
        sukuNimi.setEditable(false);
        Label sukuNimiLabel = new Label("Sukunimi:");
        sukuNimiLabel.setLabelFor(sukuNimi);
        kentat.add(sukuNimiLabel, 0, 2);
        kentat.add(sukuNimi, 0, 3);

        TextField puhNro = new TextField();
        puhNro.setEditable(false);
        Label puhNroLabel = new Label("Puhelinnumero:");
        puhNroLabel.setLabelFor(puhNro);
        kentat.add(puhNroLabel, 1, 2);
        kentat.add(puhNro, 1, 3);

        TextField sPosti = new TextField();
        sPosti.setEditable(false);
        Label sPostiLabel = new Label("Sähköposti:");
        sPostiLabel.setLabelFor(sPosti);
        kentat.add(sPostiLabel, 0, 4);
        kentat.add(sPosti, 0, 5);
    }

    // Asettaa tekstikentät muokattaviksi, jotta käyttäjä voi muokata tietoja
    private void asetaKentatMuokattaviksi(GridPane kentat) {
        kentat.getChildren().forEach(node -> {
            if (node instanceof TextField textField) {
                textField.setEditable(true);
            }
        });
    }

    private void asetaKentatEiMuokattaviksi(GridPane kentat) {
        kentat.getChildren().forEach(node -> {
            if (node instanceof TextField textField) {
                textField.setEditable(false);
            }
        });
    }

    // Asettaa mökkiin liittyvät tiedot tekstikenttiin
    private void mokinTiedotKenttiin(Mokki mokki) {
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        textField.setText(String.valueOf(mokki.getID()));
                        break;
                    case 1:
                        textField.setText(String.valueOf(mokki.getHinta()));
                        break;
                    case 2:
                        textField.setText(mokki.getOsoite());
                        break;
                    case 3:
                        textField.setText(String.valueOf(mokki.getKapasiteetti()));
                        break;
                    case 4:
                        textField.setText(mokki.getOnkoVarattu() ? "Kyllä" : "Ei");
                        break;
                }
                i++;
            }
        }
    }

    // Asettaa laskun tiedot tekstikenttiin
    private void laskunTiedotKenttiin(Lasku lasku) {
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        textField.setText(String.valueOf(lasku.getLaskuID()));
                        break;
                    case 1:
                        textField.setText(String.valueOf(lasku.getVaraus().getID()));
                        break;
                    case 2:
                        textField.setText(String.valueOf(lasku.getLuontipvm()));
                        break;
                    case 3:
                        textField.setText(String.valueOf(lasku.getErapaiva()));
                        break;
                    case 4:
                        textField.setText(lasku.isMaksettu() ? "Maksettu" : "Ei maksettu");
                        break;
                    case 5:
                        textField.setText(String.valueOf(lasku.getKokonaissumma()));
                        break;
                    case 6:
                        textField.setText(lasku.getMaksaja().getSukunimi() + " " + lasku.getMaksaja().getEtunimi());
                }
                i++;
            }
        }
    }

    // Asettaa varaustiedot tekstikenttiin
    private void varauksenTiedotKenttiin(Varaus varaus) {
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        textField.setText(String.valueOf(varaus.getID()));
                        break;
                    case 1:
                        textField.setText(String.valueOf(varaus.getVaraaja().getID()));
                        break;
                    case 2:
                        textField.setText("VarausID");
                        break;
                    case 3:
                        textField.setText(String.valueOf(varaus.getAlku()));
                        break;
                    case 4:
                        textField.setText(String.valueOf(varaus.getLoppu()));
                        break;
                    case 5:
                        textField.setText("Hinta/yö");
                }
                i++;
            }
        }
    }

    // Asettaa asiakkaan tiedot tekstikenttiin
    private void asiakkaanTiedotKenttiin(Kayttaja asiakas) {
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        textField.setText(String.valueOf(asiakas.getID()));
                        break;
                    case 1:
                        textField.setText(asiakas.getEtunimi());
                        break;
                    case 2:
                        textField.setText(asiakas.getSukunimi());
                        break;
                    case 3:
                        textField.setText(asiakas.getPuhelinNro());
                        break;
                    case 4:
                        textField.setText(asiakas.getSahkoposti());
                }
                i++;
            }
        }
    }

    // Luo listan kaikista tietokannan mökeistä
    private void luoMokkiLista() {
        VBox vbox = new VBox();
        for (Mokki mokki : mokkiLista) {
            Button text = new Button(mokki.getID() +", "+ mokki.getOsoite());
            text.setOnAction(e -> {
                mokinTiedotKenttiin(mokki);
                valittuMokki = mokki;
                luoMokkiLista();
            });
            text.setPrefWidth(195);
            vbox.getChildren().add(text);
        }
        scrollPane.setContent(vbox);
    }

    // Luo listan kaikista tietokannan laskuista
    private void luoLaskuLista() {
        VBox vbox = new VBox();
        for (Lasku lasku : laskuLista) {
            Button text = new Button(lasku.getMaksaja().getSukunimi() +", "+ lasku.getErapaiva());
            text.setOnAction(e -> {
                laskunTiedotKenttiin(lasku);
                valittuLasku = lasku;
                luoLaskuLista();
            });
            vbox.getChildren().add(text);
            text.setPrefWidth(195);
        }
        scrollPane.setContent(vbox);
    }

    // Luo listan kaikista tietokannan varauksista
    private void luoVarausLista() {
        VBox vbox = new VBox();
        for (Varaus varaus : varausLista) {
            Button text = new Button(varaus.getVaraaja().getSukunimi() +", "+ varaus.getAlku());
            text.setOnAction(e -> {
                valittuVaraus = varaus;
                varauksenTiedotKenttiin(varaus);
                luoVarausLista();
            });
            vbox.getChildren().add(text);
            text.setPrefWidth(195);
        }
        scrollPane.setContent(vbox);
    }

    // Luo listan kaikista tietokannan asiakkaista
    private void luoAsiakasLista() {
        VBox vbox = new VBox();
        for (Kayttaja k : asiakasLista) {
            Button text = new Button(k.getSukunimi() +" "+ k.getEtunimi());
            text.setOnAction(e -> {
                valittuAsiakas = k;
                asiakkaanTiedotKenttiin(k);
                luoAsiakasLista();
            });
            vbox.getChildren().add(text);
            text.setPrefWidth(195);
        }
        scrollPane.setContent(vbox);
    }

    private Mokki lueMokinTiedot(GridPane kentat) {
        Mokki mokki = new Mokki(67, 67, 67.0, "");
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        textField.getText();
                        break;
                        case 1:
                            mokki.setHinta(Double.parseDouble(textField.getText()));
                            break;
                            case 2:
                                mokki.setOsoite(textField.getText());
                                break;
                            case 3:
                                mokki.setKapasiteetti(Integer.parseInt(textField.getText()));
                                break;
                            case 4:
                                switch (textField.getText()) {
                                    case "Kyllä":
                                        mokki.setOnkoVarattu(true);
                                        break;
                                        case "Ei":
                                            mokki.setOnkoVarattu(false);
                                            break;
                                            default:
                                                throw new IllegalArgumentException("Invalid value for onkoVarattu: " + textField.getText());
                                        }
                        }
                        i++;
                    }
        }
        return mokki;
    }

    private Lasku lueLaskunTiedot(GridPane kentat) {
        Lasku lasku = this.lasku;
                int i = 1;
                for (Node node : kentat.getChildren()) {
                    if (node instanceof TextField textField) {
                        switch (i) {
                            case 1:
                                lasku.setLaskuID(Integer.parseInt(textField.getText()));
                                break;
                            case 2:
                                // lasku.setVarausID(Integer.parseInt(textField.getText()));
                                break;
                            case 3:
                                lasku.setLuontipvm(new Date(String.valueOf(textField.getText())));
                                break;
                            case 4:
                                lasku.setErapaiva(new Date(textField.getText()));
                                break;
                                case 5:
                                    switch (textField.getText()) {
                                        case "Maksettu":
                                            lasku.setMaksettu(true);
                                            break;
                                            case "Ei maksettu":
                                                lasku.setMaksettu(false);
                                                break;
                                                default:
                                                    throw new IllegalArgumentException("Invalid value for maksettu: " + textField.getText());
                                    }
                                    break;
                                    case 6:
                                        // lasku.setKokonaisSumma(Double.parseDouble(textField.getText()));
                                        break;
                                        case 7:
                                            // lasku.setMaksaja(textField.getText());
                                            break;
                        }
                        i++;
                    }
        }
        return lasku;
    }

    private Varaus lueVarauksenTiedot(GridPane kentat) {
        Varaus varaus = this.varaus;
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        varaus.setID(Integer.parseInt(textField.getText()));
                        break;
                    case 1:
                        // TODO: etsi varaaja tietokannasta ID:llä
                        // varaus.setVaraaja(textField.getText());
                        break;
                    case 2:
                        // TODO: etsi mökki tietokannasta ID:llä
                        // varaus.setVarattuMokki(textField.getText());
                        break;
                    case 3:
                        varaus.setAlku(new Date(textField.getText()));
                        break;
                    case 4:
                        varaus.setLoppu(new Date(textField.getText()));
                        break;
                    case 5:
                        varaus.setHinta(Double.parseDouble(textField.getText()));
                        break;
                }
                i++;
            }
        }
        return varaus;
    }

    private Kayttaja lueAsiakkaanTiedot(GridPane kentat) {
        Kayttaja asiakas = kayttaja;
        int i = 0;
        for (Node node : kentat.getChildren()) {
            if (node instanceof TextField textField) {
                switch (i) {
                    case 0:
                        asiakas.setID(Integer.parseInt(textField.getText()));
                        break;
                    case 1:
                        asiakas.setEtunimi(textField.getText());
                        break;
                    case 2:
                        asiakas.setSukunimi(textField.getText());
                        break;
                    case 3:
                        asiakas.setPuhelinNro(textField.getText());
                        break;
                    case 4:
                        asiakas.setSahkoposti(textField.getText());
                        break;
                }
                i++;
            }
        }
        return asiakas;
    }

    @Override
    public void start(Stage primaryStage) {
        luoMokkiKentat(kentat);
        nakymaAsetukset();
        luoMokkiLista();

        mokit.setOnAction(e -> {
            // Valitsee mökkinäkymän
            nykyinenNakyma = Nakymatyyppi.MOKIT;
            luoMokkiKentat(kentat);
            luoMokkiLista();
            mokit.setDisable(true);
            laskut.setDisable(false);
            varaukset.setDisable(false);
            asiakkaat.setDisable(false);
        });
        laskut.setOnAction(e -> {
            // Valitsee laskunäkymän
            nykyinenNakyma = Nakymatyyppi.LASKUT;
            luoLaskuKentat(kentat);
            luoLaskuLista();
            mokit.setDisable(false);
            laskut.setDisable(true);
            varaukset.setDisable(false);
            asiakkaat.setDisable(false);
        });
        varaukset.setOnAction(e -> {
            // Valitsee varausnäkymän
            nykyinenNakyma = Nakymatyyppi.VARAUKSET;
            luoVarausKentat(kentat);
            luoVarausLista();
            mokit.setDisable(false);
            laskut.setDisable(false);
            varaukset.setDisable(true);
            asiakkaat.setDisable(false);
        });
        asiakkaat.setOnAction(e -> {
            nykyinenNakyma = Nakymatyyppi.ASIAKKAAT;
            luoAsiakasKentat(kentat);
            luoAsiakasLista();
            mokit.setDisable(false);
            laskut.setDisable(false);
            varaukset.setDisable(false);
            asiakkaat.setDisable(true);
        });

        raportit.setOnAction(e -> {
            // Luo uuden ikkunan, jossa napit raporttien luomiseen
            Text text = new Text("TODO: Raportti namiskat tähän");
            VBox vbox = new VBox(text);
            Scene scene = new Scene(vbox, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        muokkaa.setOnAction(e -> {asetaKentatMuokattaviksi(kentat);});

        //TODO: tallenna muokatut tiedot tietokantaan
        tallenna.setOnAction(e -> {
            // Lukee tiedot tekstikentistä, ja muuttaa tietokantaan
            switch (nykyinenNakyma) {
                case MOKIT:
                    mokkiLista.remove(valittuMokki);
                    mokkiLista.add(lueMokinTiedot(kentat));
                    break;
                case LASKUT:
                    laskuLista.remove(valittuLasku);
                    laskuLista.add(lueLaskunTiedot(kentat));
                    break;
                case VARAUKSET:
                    varausLista.remove(valittuVaraus);
                    varausLista.add(lueVarauksenTiedot(kentat));
                    break;
                case ASIAKKAAT:
                    asiakasLista.remove(valittuAsiakas);
                    asiakasLista.add(lueAsiakkaanTiedot(kentat));
                    break;
            }
            asetaKentatEiMuokattaviksi(kentat);
        });

        luo.setOnAction(e -> {
            // Luo uuden ikkunan, jossa lisätään uusi tieto tietokantaan
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(10);
            Button lisaaTietoKantaan = new Button("Tallenna");
            VBox vbox = new VBox(root, lisaaTietoKantaan);
            Stage stage = new Stage();
            Scene scene = new Scene(vbox);
            switch (nykyinenNakyma) {
                case MOKIT:
                    luoMokkiKentat(root);
                    break;
                case LASKUT:
                    luoLaskuKentat(root);
                    break;
                case VARAUKSET:
                    luoVarausKentat(root);
                    break;
                case ASIAKKAAT:
                    luoAsiakasKentat(root);
                    break;
            }

            lisaaTietoKantaan.setOnAction(ee -> {
                // Lukee tiedot tekstikentistä, ja lisää ne tietokantaan
                switch (nykyinenNakyma) {
                    case MOKIT: {
                        mokkiLista.add(lueMokinTiedot(root));
                    }
                    case LASKUT: {
                        laskuLista.add(lueLaskunTiedot(root));
                    }
                    case VARAUKSET: {
                        varausLista.add(lueVarauksenTiedot(root));
                    }
                    case ASIAKKAAT: {
                        asiakasLista.add(lueAsiakkaanTiedot(root));
                    }
                }
                stage.close();
            });
            stage.setTitle("Lisää");
            stage.setScene(scene);
            stage.show();
        });

        poista.setOnAction(e -> {
            switch (nykyinenNakyma) {
                case ASIAKKAAT: {
                    if (valittuAsiakas != null) {
                        asiakasLista.remove(valittuAsiakas);
                        valittuAsiakas = null;
                        luoAsiakasLista();
                    }
                }
                case VARAUKSET: {
                    if (valittuVaraus != null) {
                        varausLista.remove(valittuVaraus);
                        valittuVaraus = null;
                        luoVarausLista();
                    }
                }
                case LASKUT: {
                    if (valittuLasku != null) {
                        laskuLista.remove(valittuLasku);
                        valittuLasku = null;
                        luoLaskuLista();
                    }
                }
                case MOKIT: {
                    if (valittuMokki != null) {
                        mokkiLista.remove(valittuMokki);
                        valittuMokki = null;
                        luoMokkiLista();
                    }
                }
            }
        });

        VBox root = new VBox(nakymavalinta, nakyma);
        root.setSpacing(30);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Varausjärjestelmä 3000");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
