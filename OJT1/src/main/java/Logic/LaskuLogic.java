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


    public Lasku luoLasku(Kayttaja maksaja, Varaus varaus, Laskutettava[] laskutettavat, Date erapaiva) {
        Lasku lasku = new Lasku(maksaja, varaus, false, erapaiva, laskutettavat);

        // TODO tässä vaiheessa TallennusLogic-luokkaan lisätään lasku

        // Tallennetaan tekstitiedosto laskun luonnin yhteydessä
        try {
            String tiedostonimi = "lasku_" + varaus.getID() + ".txt";
            tallennaTiedostoon(lasku, tiedostonimi);
            System.out.println("Lasku tallennettu tiedostoon " + tiedostonimi);
        } catch (IOException e) {
            System.err.println("Laskun tallennus epäonnistui: " + e.getMessage());
        }

        return lasku;
    }

    public void merkitseMaksetuksi(Lasku lasku, boolean maksettu) {
        lasku.setMaksettu(maksettu);
    }


}