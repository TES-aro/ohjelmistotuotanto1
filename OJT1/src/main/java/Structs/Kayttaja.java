package Structs;

import java.util.UUID;

public class Kayttaja {
    String nimi;
    String UUID;
    String sahkoposti;
    String puhelinNro; // lisätty
    String salasanaHash;
    boolean endUser = true;
    // TODO mitä nyt vielä tarvitsee.
    // Varaus[] varaukset;


    //getterit + setterit

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getPuhelinNro() {
        return puhelinNro;
    }

    public void setPuhelinNro(String puhelinNro) {
        this.puhelinNro = puhelinNro;
    }

    public String getUUID() {
        return UUID;
    }

    public boolean getIsEndUser() {
        return endUser;
    }

    public void setIsEndUser(boolean b) {
        this.endUser = b;
    }

    public void setSalasanaHash(String salasanaHash) {
        this.salasanaHash = salasanaHash;
    }

    public void setEndUser(boolean bool) {
        this.endUser = bool;
    }


    public Kayttaja(String nimi, String UUID, String sahkoposti) {
        this.nimi = nimi;
        this.UUID = UUID;
        this.sahkoposti = sahkoposti;
    }
}

