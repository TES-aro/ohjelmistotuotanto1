package Structs;

public class Mokki {
    int ID;
    String omistajaID;
    String osoite;
    int maksimiAsukkaat;
    boolean onkoVarattu;
    double hinta;

    public Mokki(int ID, String omistajaID, String osoite, int maksimiAsukkaat, boolean onkoVarattu, double hinta){
        this.ID = ID;
        this.omistajaID = omistajaID;
        this.osoite = osoite;
        this.maksimiAsukkaat = maksimiAsukkaat;
        this.onkoVarattu = onkoVarattu;
        this.hinta = hinta;
    }

    // getterit
    public int getID() {
        return ID;
    }


    public int getMaksimiAsukkaat() {return maksimiAsukkaat;}
    public boolean getOnkoVarattu() {return onkoVarattu;}
    public double getHinta() {return hinta;}

    // setterit
    public void setID(int ID) { this.ID = ID; }
    public void setOmistajaID(String omistajaID) { this.omistajaID = omistajaID; }
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public void setMaksimiAsukkaat(int maksimiAsukkaat) { this.maksimiAsukkaat = maksimiAsukkaat; }
    public void setOnkoVarattu(boolean onkoVarattu) {this.onkoVarattu = onkoVarattu;}
    public void setHinta(double hinta) {this.hinta = hinta;}
}
