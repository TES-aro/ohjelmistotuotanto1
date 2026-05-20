package Structs;

import java.util.UUID;

public class Kayttaja {
    String etunimi;
    String sukunimi;
    int ID;
    String sahkoposti;
    String puhelinNro; // lisätty
    //String salasanaHash;
    //boolean endUser = true;
    // TODO mitä nyt vielä tarvitsee.
    // Varaus[] varaukset;


    //getterit + setterit

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
	    	return sukunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public void setSukunimi(String sukunimi) {
	    this.sukunimi = sukunimi;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /*
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
*/

    public Kayttaja(String etunimi, String sukunimi, int ID, String sahkoposti, String puhelinNro) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.ID = ID;
        this.sahkoposti = sahkoposti;
        this.puhelinNro = puhelinNro;
    }
}

