package Structs;

import java.util.UUID;

public class Kayttaja {
    String nimi;
    String UUID;
    String sahkoposti;
    // TODO mitä nyt vielä tarvitsee.
    // Varaus[] varaukset;


    public String getNimi() {
        return nimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public String getUUID() {
        return UUID;
    }

    public Kayttaja(String nimi, String UUID, String sahkoposti){
        this.nimi = nimi;
        this.UUID = UUID;
        this.sahkoposti = sahkoposti;
    }
}