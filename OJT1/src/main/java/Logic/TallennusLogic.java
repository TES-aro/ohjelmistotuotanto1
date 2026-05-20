package Logic;
import Structs.*;
import SQLbackend.LG;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
        String q = """
            INSERT INTO asiakkaat(asiakas_id, etunimi, sukunimi, email, puhelin)
            VALUES(?, ?, ?, ?, ?);
            """;
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, kayttaja.getID());
            stm.setString(2, kayttaja.getEtunimi());
            stm.setString(3, kayttaja.getSukunimi());
            stm.setString(4, kayttaja.getSahkoposti());
            stm.setString(5, kayttaja.getPuhelinNro());
            stm.execute();

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void postaKayttaja(Kayttaja kayttaja){
        try{
            poistaKayttaja(kayttaja.getID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void poistaKayttaja(int id) {
        String q = "DELETE FROM asiakkaat WHERE asiakas_id = ?;";
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, id);
            stm.execute();

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void paivitaKayttaja(Kayttaja kayttaja) {
        String q = """
                UPDATE asiakkaat
                SET etunimi = ?, sukunimi = ?, email = ?, puhelin = ?
                WHERE asiakas_id = ?;
                """;
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setString(1, kayttaja.getEtunimi());
            stm.setString(2, kayttaja.getSukunimi());
            stm.setString(3, kayttaja.getSahkoposti());
            stm.setString(4, kayttaja.getPuhelinNro());
            stm.setInt(5, kayttaja.getID());
            stm.execute();

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public List<Kayttaja> haeKayttajat() {// hae kaikki käyttäjät
        String q = "SELECT * FROM asiakkaat;";
        ArrayList<Kayttaja> kayttajat = new ArrayList<>();
        try (ResultSet rs = conn.createStatement().executeQuery(q)){
            while (rs.next()){
                int ID = rs.getInt(1);
                String etunimi = rs.getString(2);
                String sukunimi = rs.getString(3);
                String email = rs.getString(4);
                String puhelin = rs.getString(5);

                kayttajat.add(new Kayttaja(etunimi, sukunimi, ID, email, puhelin));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return kayttajat;
    }

    public Kayttaja haeKayttaja(int id) { // hae yksittäinen käyttäjä
        String q = "SELECT * FROM asiakkaat WHERE asiakas_id = ?;";
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int ID = rs.getInt(1);
            String etunimi = rs.getString(2);
            String sukunimi = rs.getString(3);
            String email = rs.getString(4);
            String puhelin = rs.getString(5);

            return new Kayttaja(etunimi, sukunimi, ID, email, puhelin);

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    // varaukset

    public boolean lisaaVaraus(Varaus varaus) {
        String q = """
                INSERT INTO varaukset(varaus_id, alku_pvm, loppu_pvm, hinta,
                asiakas_id, mokki_id)
                VALUES(?, ?, ?, ?, ?, ?);
                """;
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, varaus.getID());
            stm.setDate(2, LG.convert2SQL(varaus.getAlku()));
            stm.setDate(3, LG.convert2SQL(varaus.getLoppu()));
            stm.setDouble(4, varaus.getHinta());
            stm.setInt(5, varaus.getVaraaja().getID());
            stm.setInt(6, varaus.getVarattuMokki().getID());
            return stm.execute();

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public boolean poistaVaraus(int varausID) {
        String q = "DELETE * FROM varaukset WHERE varaus_id = ?;";
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, varausID);
            return stm.execute();
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public boolean poistaVaraus(Varaus varaus){
        try {
            return poistaVaraus(varaus.getID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
    CREATE TABLE `varaukset` (
  `varaus_id` int NOT NULL AUTO_INCREMENT,
  `alku_pvm` date NOT NULL DEFAULT (curdate()),
  `loppu_pvm` date NOT NULL,
  `hinta` decimal(8,2) NOT NULL,
  `asiakas_id` int DEFAULT NULL,
  `mokki_id` int DEFAULT NULL,

     */

    //TODO tämä etenkin pitää testata!

    public List<Varaus> haeVaraukset() {// kaikki
        String q = """
                SELECT * FROM varaukset
                JOIN mokit ON varaukset.mokki_id = mokit.mokki_id
                JOIN asiakkaat ON varaukset.asiakas_id = asiakkaat.asiakas_id;
                """;
        ArrayList<Varaus> varaukset = new ArrayList<>();
        try (ResultSet rs = conn.createStatement().executeQuery(q)){
            while (rs.next()){
                int id = rs.getInt("varaus_id");
                Date alku = LG.convert2util(rs.getDate("alku_pvm"));
                Date loppu = LG.convert2util(rs.getDate("loppu_pvm"));
                Double hinta = rs.getDouble("hinta");

                Mokki mokki = new Mokki(rs.getInt("mokit.mokki_id"),
                        rs.getInt("mokit.kapasiteetti"),
                        rs.getDouble("mokit.hinta_per_yo"));

                Kayttaja asiakas = new Kayttaja(
                        rs.getString("asiakkaat.etunimi"),
                        rs.getString("asiakkaat.sukunimi"),
                        rs.getInt("asiakkaat.asiakas_id"),
                        rs.getString("asiakkaat.email"),
                        rs.getString("asiakkaat.puhelin"));
                varaukset.add(new Varaus(id, mokki, asiakas, alku, loppu, hinta));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return varaukset;
    }

    public List<Varaus> haeVarauksetKayttajalle(int uuid) { // yksittäinen
        String q = """
                SELECT varaukset.*, asiakkaat.*, mokit.*
                FROM varaukset
                    JOIN mokit
                    ON varaukset.mokki_id = mokit.mokki_id
                
                    JOIN asiakkaat
                    ON varaukset.asiakas_id = asiakkaat.asiakas_id
                WHERE varaukset.asiakas_id = ?;
                """;
        ArrayList<Varaus> varaukset = new ArrayList<>();
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, uuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("varaukset.varaus_id");
                Date alku = LG.convert2util(rs.getDate("varaukset.alku_pvm"));
                Date loppu = LG.convert2util(rs.getDate("varaukset.loppu_pvm"));
                Double hinta = rs.getDouble("varaukset.hinta");

                Mokki mokki = new Mokki(rs.getInt("mokit.mokki_id"),
                        rs.getInt("mokit.kapasiteetti"),
                        rs.getDouble("mokit.hinta_per_yo"));

                Kayttaja asiakas = new Kayttaja(
                        rs.getString("asiakkaat.etunimi"),
                        rs.getString("asiakkaat.sukunimi"),
                        rs.getInt("asiakkaat.asiakas_id"),
                        rs.getString("asiakkaat.email"),
                        rs.getString("asiakkaat.puhelin"));
                varaukset.add(new Varaus(id, mokki, asiakas, alku, loppu, hinta));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return varaukset;
    }

    public List<Varaus> haeVarauksetMokille(int mokkiID) {
        String q = """
                SELECT varaukset.*, asiakkaat.*, mokit.*
                FROM varaukset
                    JOIN mokit
                    ON varaukset.mokki_id = mokit.mokki_id
                    JOIN asiakkaat
                    ON varaukset.asiakas_id = asiakkaat.asiakas_id
                WHERE varaukset.mokki_id = ?;
                """;
        ArrayList<Varaus> varaukset = new ArrayList<>();
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, mokkiID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("varaukset.varaus_id");
                Date alku = LG.convert2util(rs.getDate("varaukset.alku_pvm"));
                Date loppu = LG.convert2util(rs.getDate("varaukset.loppu_pvm"));
                double hinta = rs.getDouble("varaukset.hinta");

                Mokki mokki = new Mokki(rs.getInt("mokit.mokki_id"),
                        rs.getInt("mokit.kapasiteetti"),
                        rs.getDouble("mokit.hinta_per_yo"));

                Kayttaja asiakas = new Kayttaja(
                        rs.getString("asiakkaat.etunimi"),
                        rs.getString("asiakkaat.sukunimi"),
                        rs.getInt("asiakkaat.asiakas_id"),
                        rs.getString("asiakkaat.email"),
                        rs.getString("asiakkaat.puhelin"));
                varaukset.add(new Varaus(id, mokki, asiakas, alku, loppu, hinta));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return varaukset;
    }

    // laskut

    public boolean lisaaLasku(Lasku lasku) {
        String q = """
                INSERT INTO laskut(lasku_id, luonti_pvm, erapaiva, summa, varaus_id
                VALUES(?, ?, ?, ?, ?);
                """;
        try (PreparedStatement ps = conn.prepareStatement(q)){
            ps.setInt(1, lasku.getLaskuID());
            ps.setDate(2, LG.convert2SQL(lasku.getLuontipvm()));
            ps.setDate(3, LG.convert2SQL(lasku.getErapaiva()));
            ps.setDouble(4, lasku.getVaraus().getHinta());
            ps.setInt(5, lasku.getVaraus().getID());
            return ps.execute();
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public boolean paivitaLasku(Lasku lasku) {
        String q = """
                UPDATE laskut
                SET luonti_pvm = ?, erapaiva = ?, summa = ?, status = ?, varaus_id = ?
                WHERE lasku_id = ?;
                """;
        try (PreparedStatement ps = conn.prepareStatement(q)){
            ps.setDate(1, LG.convert2SQL(lasku.getLuontipvm()));
            ps.setDate(2, LG.convert2SQL(lasku.getErapaiva()));
            ps.setBoolean(3, lasku.isMaksettu());
            ps.setInt(4, lasku.getVaraus().getID());
            ps.setInt(5, lasku.getLaskuID());
            return ps.execute();

        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
    }

    public List<Lasku> haeLaskut() {
        ArrayList<Lasku> laskut = new ArrayList<>();
        String q = """
                SELECT l.*, v.* ,asiakkaat.*, mokit.* 
                FROM laskut AS l
                    JOIN varaukset AS v
                    ON l.varaus_id = v.varaus_id;
                    JOIN asiakkaat
                    ON v.asiakas_id = asiakkaat.asiakas_id
                    JOIN mokit
                    ON mokit.mokki_id = v.mokki_id
                """;
        try (ResultSet rs = conn.createStatement().executeQuery(q)){
            while (rs.next()){
                int ID = rs.getInt("l.lasku_id");
                Date luonti = LG.convert2util(rs.getDate("l.luonti_pvm"));
                Date erapaiva = LG.convert2util(rs.getDate("l.erapaiva"));
                double summa = rs.getDouble("l.summa");
                boolean maksettu = rs.getBoolean("l.status");

                int id = rs.getInt("varaukset.varaus_id");
                Date alku = LG.convert2util(rs.getDate("varaukset.alku_pvm"));
                Date loppu = LG.convert2util(rs.getDate("varaukset.loppu_pvm"));
                double hinta = rs.getDouble("varaukset.hinta");

                Mokki mokki = new Mokki(rs.getInt("mokit.mokki_id"),
                        rs.getInt("mokit.kapasiteetti"),
                        rs.getDouble("mokit.hinta_per_yo"));

                Kayttaja asiakas = new Kayttaja(
                        rs.getString("asiakkaat.etunimi"),
                        rs.getString("asiakkaat.sukunimi"),
                        rs.getInt("asiakkaat.asiakas_id"),
                        rs.getString("asiakkaat.email"),
                        rs.getString("asiakkaat.puhelin"));
                Varaus varaus = new Varaus(id, mokki, asiakas, alku, loppu, hinta);

                laskut.add(new Lasku(ID, asiakas, varaus, maksettu, luonti, erapaiva, summa));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return laskut;
    }
/*
  `lasku_id` int NOT NULL AUTO_INCREMENT,
  `luonti_pvm` date NOT NULL,
  `erapaiva` date NOT NULL,
  `summa` decimal(10,2) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `varaus_id` int DEFAULT NULL,

 */
    public List<Lasku> haeLaskutKayttajalle(int uuid) {
        ArrayList<Lasku> laskut = new ArrayList<>();
        String q = """
                SELECT l.*, v.* ,asiakkaat.*, mokit.* 
                FROM laskut AS l
                    JOIN varaukset AS v
                    ON l.varaus_id = v.varaus_id;
                    JOIN asiakkaat
                    ON v.asiakas_id = asiakkaat.asiakas_id
                    JOIN mokit
                    ON mokit.mokki_id = v.mokki_id
                WHERE asiakkaat.asiakas_id = ?;
                """;
        try (PreparedStatement stm = conn.prepareStatement(q)){
            stm.setInt(1, uuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("l.lasku_id");
                Date luonti = LG.convert2util(rs.getDate("l.luonti_pvm"));
                Date erapaiva = LG.convert2util(rs.getDate("l.erapaiva"));
                double summa = rs.getDouble("l.summa");
                boolean maksettu = rs.getBoolean("l.status");

                int id = rs.getInt("varaukset.varaus_id");
                Date alku = LG.convert2util(rs.getDate("varaukset.alku_pvm"));
                Date loppu = LG.convert2util(rs.getDate("varaukset.loppu_pvm"));
                double hinta = rs.getDouble("varaukset.hinta");

                Mokki mokki = new Mokki(rs.getInt("mokit.mokki_id"),
                        rs.getInt("mokit.kapasiteetti"),
                        rs.getDouble("mokit.hinta_per_yo"));

                Kayttaja asiakas = new Kayttaja(
                        rs.getString("asiakkaat.etunimi"),
                        rs.getString("asiakkaat.sukunimi"),
                        rs.getInt("asiakkaat.asiakas_id"),
                        rs.getString("asiakkaat.email"),
                        rs.getString("asiakkaat.puhelin"));
                Varaus varaus = new Varaus(id, mokki, asiakas, alku, loppu, hinta);

                laskut.add(new Lasku(ID, asiakas, varaus, maksettu, luonti, erapaiva, summa));
            }
        } catch (Exception e) {
            LG.log(e.toString());
            throw new RuntimeException(e);
        }
        return laskut;
    }
}
