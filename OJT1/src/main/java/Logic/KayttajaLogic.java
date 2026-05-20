package Logic;

import Structs.Kayttaja;

import java.util.List;

public class KayttajaLogic {

    private final TallennusLogic tallennus;

    public KayttajaLogic(TallennusLogic tallennus) {
        this.tallennus = tallennus;
    }

    public Kayttaja lisaaKayttaja(String etunimi, String sukunimi, String sahkoposti, String puhelinNro) {
        Kayttaja kayttaja = new Kayttaja(etunimi, sukunimi, 0, sahkoposti, puhelinNro);
        tallennus.lisaaKayttaja(kayttaja);
        return kayttaja;
    }

    public void paivitaKayttaja(Kayttaja kayttaja) {
        tallennus.paivitaKayttaja(kayttaja);
    }

    public void poistaKayttaja(Kayttaja kayttaja) {
        tallennus.poistaKayttaja(kayttaja);
    }
    public List<Kayttaja> haeKayttajat() {
        return tallennus.haeKayttajat();
    }
    public Kayttaja haeKayttaja(int id) {
        return tallennus.haeKayttaja(id);
    }
}