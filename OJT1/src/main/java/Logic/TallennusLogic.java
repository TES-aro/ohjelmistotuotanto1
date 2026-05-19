package Logic;
import Structs.*;
import SQLbackend.LG;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// kun luodaan esim. uusi mökki tai tehdään muutoksia, niin tätä kautta ne lisätään SQL tietokantaan. Jos on järkevämpi tapa niin kertokaa,
// SQLbackend luokan toimintaan en oo niin tutustunut.

public class TallennusLogic {

    private final Connection conn;

    /*
    public TallennusLogic(){
        conn = new SQLbridge().getConnection();
    }
     */

    public TallennusLogic(Connection conn) { this.conn = conn; }


    public void lisaaMokki(Mokki mokki) {
        // TODO Näihin metodeihin tarvitaan joku menetelmä, jolla tehdään nämä muutokset SQL tietokantaan.
        //  Nämä viittaa muihin logic-luokkiin, joihin ei puolestaan pitäisi tarvita koskea.

        // esimerkkitoteutus. ehkä toimii *shrug*
        String query = """
            INSERT INTO mokit(mokki_id, kapasiteetti, hinta_per_yo)
            VALUES(?, ?, ?);
            """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1,mokki.getID());
            ps.setInt(2,mokki.getKapasiteetti());
            ps.setDouble(3,mokki.getHinta());
            ps.execute();
        } catch (SQLException e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void poistaMokki (Mokki mokki) {
        try {
            poistaMokki(mokki.getID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void poistaMokki(int mokkiID) {
        String q = "DELETE FROM mokit WHERE mokki_id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, mokkiID);
            ps.execute();
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void paivitaMokki(Mokki mokki) {
        String q = """
                UPDATE mokit
                SET kapasiteetti = ?,
                hinta_per_yo = ?,
                WHERE mokki_id = ?;
                """;

        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, mokki.getKapasiteetti());
            ps.setDouble(2, mokki.getHinta());
            ps.setInt(3, mokki.getID());
            ps.execute();

        } catch (Exception e){
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public List<Mokki> haeMokit() {// hae lista kaikista mökeistä
        String q = "SELECT * FROM mokit;";
        List<Mokki> mokit = new ArrayList<>();
        try (ResultSet rs = conn.createStatement().executeQuery(q)){
            while(rs.next()){
                int mokkiID = rs.getInt("mokki_id");
                int kapasiteetti = rs.getInt("kapasiteetti");
                double hinta = rs.getDouble("hinta_per_yo");

                Mokki mokki = new Mokki(mokkiID, kapasiteetti, hinta);
                mokit.add(mokki);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mokit;
    }

    public Mokki haeMokki(int mokkiID) {
        // hae ID:n perusteella yksittäinen mökki
        String q = "SELECT * FROM mokit WHERE mokki_id = ?;";
        try (PreparedStatement stm = conn.prepareStatement(q)) {
            stm.setInt(1, mokkiID);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return new Mokki(
                    rs.getInt("mokki_id"),
                    rs.getInt("kapasiteetti"),
                    rs.getDouble("hinta_per_yo")
            );
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
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

    public void poistaVaraus(int varausID) {
    }

    public List<Varaus> haeVaraukset() { // kaikki
        return null;
    }

    public List<Varaus> haeVarauksetKayttajalle(int uuid) { // yksittäinen
        return null;
    }

    public List<Varaus> haeVarauksetMokille(int mokkiID) {
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

    public List<Lasku> haeLaskutKayttajalle(int uuid) {
        return null;
    }
}
