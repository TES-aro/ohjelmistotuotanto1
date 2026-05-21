package Logic;

import Structs.Lasku;
import Structs.Varaus;
import Structs.Kayttaja;
import Structs.Laskutettava;

import java.io.FileWriter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

// Täällä on ainakin laskun luominen, maksetuksi merkitseminen ja tiedostojen luominen

public class LaskuLogic {
    private final TallennusLogic tallennus;
    public LaskuLogic(TallennusLogic tallennus) {
        this.tallennus = tallennus;
    }


    public Lasku luoLasku(Kayttaja maksaja, Varaus varaus, double kokonaishinta, Date erapaiva) {
        Lasku lasku = new Lasku(maksaja, varaus, erapaiva, kokonaishinta );

        // Tallennetaan tietokantaan
        tallennus.lisaaLasku(lasku);

        // Tallennetaan tekstitiedosto
        String tiedostonimi = "lasku_" + varaus.getID() + ".txt";
        try {
            tallennaTiedostoon(lasku, tiedostonimi);
            System.out.println("Lasku tallennettu tiedostoon: " + tiedostonimi);
        } catch (IOException e) {
            System.err.println("Laskun tallennus tiedostoon epäonnistui: " + e.getMessage());
        }

        return lasku;
    }


    public void merkitseMaksetuksi(Lasku lasku, boolean maksettu) {
        lasku.setMaksettu(maksettu);
        tallennus.paivitaLasku(lasku);
    }

    public List<Lasku> haeLaskut() {
        return tallennus.haeLaskut();
    }

    public List<Lasku> haeAsiakkaanLaskut(Kayttaja kayttaja) {
        return tallennus.haeLaskutKayttajalle(kayttaja.getID());
    }

    private void tallennaTiedostoon(Lasku lasku, String tiedostonimi) throws IOException {
        try (FileWriter kirjuri = new FileWriter(tiedostonimi)) {
            kirjuri.write("Lasku\n\n");
            kirjuri.write("Asiakas:   " + lasku.getMaksaja().getEtunimi() +" " + lasku.getMaksaja().getSukunimi() + "\n");
            kirjuri.write("Sähköpostiosoite:" + lasku.getMaksaja().getSahkoposti() + "\n");
            kirjuri.write("Varaus ID: " + lasku.getVaraus().getID() + "\n");
            kirjuri.write("Mökki ID:     " + lasku.getVaraus().getVarattuMokki().getID() + "\n");
            kirjuri.write("Ajalla:    " + lasku.getVaraus().getAlku() + " – " + lasku.getVaraus().getLoppu() + "\n");
            kirjuri.write("Eräpäivä:  " + lasku.getErapaiva() + "\n\n");

            kirjuri.write(String.format("Yhteensä:  %.2f €%n", lasku.getKokonaissumma()));

            if (lasku.isMaksettu()) {kirjuri.write("Maksettu: Kyllä");}
            else if (!lasku.isMaksettu()) {kirjuri.write("Maksettu: Ei");}

        }
    }


}
