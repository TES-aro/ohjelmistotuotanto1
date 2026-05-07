package Structs;

public class Mokki {
    String ID;
    String omistajaID;
    // voidaan myös toteuttaa luokallisesti vaatimalla Omistaja ryhmän olio.
    // Omistaja omistaja;
    String osoite;
    int maksimiAsukkaat;
    boolean onkoVarattu;
    // mahdollisesti muut tiedot kuten koko, huoneet jne.

    public Mokki(String ID, String omistajaID, String osoite, int maksimiAsukkaat, boolean onkoVarattu){
        this.ID = ID;
        this.omistajaID = omistajaID;
        this.osoite = osoite;
        this.maksimiAsukkaat = maksimiAsukkaat;
        this.onkoVarattu = onkoVarattu;
    }


    // getterit
    public String getID() {
        return ID;
    }
    public String getOmistajaID() {
        return omistajaID;
    }
    public String getOsoite() {
        return osoite;
    }
    public boolean getOnkoVarattu() {return onkoVarattu;}

    // setterit
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public void setMaksimiAsukkaat(int maksimiAsukkaat) { this.maksimiAsukkaat = maksimiAsukkaat; }
    public void setOnkoVarattu(boolean onkoVarattu) {this.onkoVarattu = onkoVarattu;}
}
