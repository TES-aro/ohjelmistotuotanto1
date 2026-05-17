package Logic;
import SQLbackend.SQLbridge;
import Structs.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// kun luodaan esim. uusi mökki tai tehdään muutoksia, niin tätä kautta ne lisätään SQL tietokantaan. Jos on järkevämpi tapa niin kertokaa,
// SQLbackend luokan toimintaan en oo niin tutustunut.

public class TallennusLogic {

    private Connection conn;

    public TallennusLogic(){
        conn = new SQLbridge().getConnection();
    }


    public void lisaaMokki(Mokki mokki) {
        // TODO Näihin metodeihin tarvitaan joku menetelmä, jolla tehdään nämä muutokset SQL tietokantaan.
        //  Nämä viittaa muihin logic-luokkiin, joihin ei puolestaan pitäisi tarvita koskea.

        // esimerkkitoteutus. ehkä toimii *shrug*
        String query = """
            INSERT INTO mokit(mokki_id, kapasiteetti, hinta_per_yo)
            VALUES(?, ?, ?);
            """;
        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1,mokki.getID());
            ps.setInt(2,mokki.getMaksimiAsukkaat());
            ps.setInt(3,mokki.getHinta());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void poistaMokki(String mokkiID) {
    }

    public void paivitaMokki(Mokki mokki) {
    }

    public List<Mokki> haeMokit() {// hae lista kaikista mökeistä
        String q = "SELECT * FROM mokkit;";
        List<Mokki> mokit = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(q);
            while(rs.next()){
                int mokkiID = rs.getInt("mokki_id");
                int kapasiteetti = rs.getInt("kapasiteetti");
                int hinta = rs.getInt("hinta_per_yo");
                System.out.format("ID: %d , kapasiteetti: %d , hinta per yö: %d €\n",
                        mokkiID, kapasiteetti, hinta);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
