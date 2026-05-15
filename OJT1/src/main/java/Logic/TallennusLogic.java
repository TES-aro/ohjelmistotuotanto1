package Logic;
import Structs.*;
import java.util.List;

// kun luodaan esim. uusi mökki tai tehdään muutoksia, niin tätä kautta ne lisätään SQL tietokantaan. Jos on järkevämpi tapa niin kertokaa,
// SQLbackend luokan toimintaan en oo niin tutustunut.

public class TallennusLogic {

    // mökit

    public void lisaaMokki(Mokki mokki) {
        // TODO Näihin metodeihin tarvitaan joku menetelmä, jolla tehdään nämä muutokset SQL tietokantaan.
        //  Nämä viittaa muihin logic-luokkiin, joihin ei puolestaan pitäisi tarvita koskea.
    }

    public void poistaMokki(String mokkiID) {
    }

    public void paivitaMokki(Mokki mokki) {
    }

    public List<Mokki> haeMokit() { // hae lista kaikista mökeistä
        return null;
    }

    public Mokki haeMokki(String mokkiID) { // hae ID:n perusteella yksittäinen mökki
        return null;
    }


    // käyttäjät/asiakkaat

    public void lisaaKayttaja(Kayttaja kayttaja) {
    }

    public void poistaKayttaja(String uuid) {
    }

    public void paivitaKayttaja(Kayttaja kayttaja) {
    }

    public List<Kayttaja> haeKayttajat() { // hae kaikki käyttäjät
        return null;
    }

    public Kayttaja haeKayttaja(String uuid) { // hae yksittäinen käyttäjä
        return null;
    }

    // varaukset

    public void lisaaVaraus(Varaus varaus) {
    }

    public void poistaVaraus(String varausID) {
    }

    public List<Varaus> haeVaraukset() { // kaikki
        return null;
    }

    public List<Varaus> haeVarauksetKayttajalle(String uuid) { // yksittäinen
        return null;
    }

    public List<Varaus> haeVarauksetMokille(String mokkiID) {
        return null;
    }

    // laskut

    public void lisaaLasku(Lasku lasku) {
    }

    public void paivitaLasku(Lasku lasku) {
    }

    public List<Lasku> haeLaskut() {
        return null;
    }

    public List<Lasku> haeLaskutKayttajalle(String uuid) {
        return null;
    }
}
