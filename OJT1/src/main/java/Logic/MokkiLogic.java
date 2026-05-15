package Logic;

import Structs.Mokki;

import java.util.List;

public class MokkiLogic {     //Tässä luokassa mökkien lisäys, poisto, haku, ja varaustilanteen vaihtaminen

    private final TallennusLogic tallennus; // Tämän avulla saadaan yhteys tietokantaan, ilman että tätä luokkaa tarvitsee muuttaa

    public MokkiLogic(TallennusLogic tallennus) {
        this.tallennus = tallennus;
    }


    public Mokki lisaaMokki(String ID, String omistajaID, String osoite, int maksimiAsukkaat) { // luo uusi mökki ja tallenna se
        Mokki mokki = new Mokki(ID, omistajaID, osoite, maksimiAsukkaat, false);
        tallennus.lisaaMokki(mokki);
        return mokki;
    }

    public void poistaMokki(String mokkiID) { // poista mökki ID:n perusteella, tallenna muutos
        tallennus.poistaMokki(mokkiID);
    }

    public void paivitaMokki(Mokki mokki) { // päivitä olemassaolevan mökin tietoja
        tallennus.paivitaMokki(mokki);
    }

    public List<Mokki> haeMokit() { // palauttaa listan kaikista mökeistä
        return tallennus.haeMokit();
    }

    public Mokki haeMokki(String mokkiID) { // palauttaa yksittäisen mökin
        return tallennus.haeMokki(mokkiID);
    }

    public void asetaVarausTila(String mokkiID, boolean onkoVarattu) { // merkitse mökki varatuksi/varaamattomaksi
        Mokki mokki = tallennus.haeMokki(mokkiID);
        mokki.setOnkoVarattu(onkoVarattu);
        tallennus.paivitaMokki(mokki);

    }



}