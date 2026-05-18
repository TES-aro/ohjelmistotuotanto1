package Structs;

public class Mokki {
    int ID;
    //String omistajaID;
    // voidaan myös toteuttaa luokallisesti vaatimalla Omistaja ryhmän olio.
    // Omistaja omistaja;
    //String osoite;
    int maksimiAsukkaat;
    boolean onkoVarattu;
    double hinta;
    // mahdollisesti muut tiedot kuten koko, huoneet jne.

    public Mokki(int ID, int maksimiAsukkaat, boolean onkoVarattu, double hinta){
        this.ID = ID;
        //this.omistajaID = omistajaID;
        //this.osoite = osoite;
        this.maksimiAsukkaat = maksimiAsukkaat;
        this.onkoVarattu = onkoVarattu;
        this.hinta = hinta;
    }
    public Mokki(int ID, int maksimiAsukkaat, double hinta){
        this.ID = ID;
        this.maksimiAsukkaat = maksimiAsukkaat;
        this.hinta = hinta;
    }


    // getterit
    public int getID() {
        return ID;
    }
    //public String getOmistajaID() {
    //    return omistajaID;
    //}

    //public String getOsoite() {
    //    return osoite;
    //}

    public int getMaksimiAsukkaat() {return maksimiAsukkaat;}
    public boolean getOnkoVarattu() {return onkoVarattu;}
    public double getHinta() {return hinta;}

    // setterit
    //public void setOsoite(String osoite) { this.osoite = osoite; }
    public void setMaksimiAsukkaat(int maksimiAsukkaat) { this.maksimiAsukkaat = maksimiAsukkaat; }
    public void setOnkoVarattu(boolean onkoVarattu) {this.onkoVarattu = onkoVarattu;}
}
