package Structs;

public class Mokki {
    int ID;
    String osoite;
    int kapasiteetti;
    boolean onkoVarattu;
    double hinta;

    public Mokki(int ID, String osoite, int kapasiteetti, boolean onkoVarattu, double hinta){
        this.ID = ID;
        this.osoite = osoite;
        this.kapasiteetti = kapasiteetti;
        this.onkoVarattu = onkoVarattu;
        this.hinta = hinta;
    }

    public Mokki(int ID, int kapasiteetti, double hinta, String osoite) {
        this.ID = ID;
        this.kapasiteetti = kapasiteetti;
        this.hinta = hinta;
        this.onkoVarattu = false;
        this.osoite = osoite;
    }

    // getterit
    public int getID() {
        return ID;
    }


    public int getKapasiteetti() {return kapasiteetti;}
    public boolean getOnkoVarattu() {return onkoVarattu;}
    public double getHinta() {return hinta;}

    public String getOsoite() {
        return osoite;
    }

    // setterit
    public void setID(int ID) { this.ID = ID; }
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public void setKapasiteetti(int kapasiteetti) { this.kapasiteetti = kapasiteetti; }
    public void setOnkoVarattu(boolean onkoVarattu) {this.onkoVarattu = onkoVarattu;}
    public void setHinta(double hinta) {this.hinta = hinta;}
}
