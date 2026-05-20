package Logic;

import Structs.Mokki;

import java.util.List;

public class MokkiLogic {     //Tässä luokassa mökkien lisäys, poisto, haku, ja varaustilanteen vaihtaminen

    private final TallennusLogic tallennus;

    public MokkiLogic(TallennusLogic tallennus) {
        this.tallennus = tallennus;
    }


    public Mokki lisaaMokki(String osoite, int kapasiteetti, boolean onkoVarattu,  int hinta) { // luo uusi mökki ja tallenna se. ID=0 koska se vedetään databasesta heti oikeaksi
        Mokki mokki = new Mokki(0, osoite, kapasiteetti, false, hinta);
        tallennus.lisaaMokki(mokki);
        return mokki;
    }

    public void poistaMokki(int mokkiID) { // poista mökki ID:n perusteella, tallenna muutos
        tallennus.poistaMokki(mokkiID);
    }

    public void paivitaMokki(Mokki mokki) { // päivitä olemassaolevan mökin tietoja
        tallennus.paivitaMokki(mokki);
    }

    public List<Mokki> haeMokit() { // palauttaa listan kaikista mökeistä
        return tallennus.haeMokit();
    } // hae kaikki mökit

    public Mokki haeMokki(int mokkiID) { // palauttaa yksittäisen mökin
        return tallennus.haeMokki(mokkiID);
    } // hae mökki ID:n perusteella
/*
    public void asetaVarausTila(int mokkiID, boolean onkoVarattu) { // merkitse mökki varatuksi/varaamattomaksi
        Mokki mokki = tallennus.haeMokki(mokkiID);
        mokki.setOnkoVarattu(onkoVarattu);
        tallennus.paivitaMokki(mokki);

    }

 */
}
