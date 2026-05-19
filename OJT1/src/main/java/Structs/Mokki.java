package Structs;

public class Mokki {
    int ID;
    //String omistajaID;
    // voidaan myös toteuttaa luokallisesti vaatimalla Omistaja ryhmän olio.
    // Omistaja omistaja;
    //String osoite;
    int kapasiteetti;
    double hinta;
    // mahdollisesti muut tiedot kuten koko, huoneet jne.

    public Mokki(int ID, int kapasiteetti, double hinta){
        this.ID = ID;
        this.kapasiteetti = kapasiteetti;
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

    public int getKapasiteetti() {return kapasiteetti;}
    public double getHinta() {return hinta;}

    // setterit
    //public void setOsoite(String osoite) { this.osoite = osoite; }
    public void setKapasiteetti(int kapasiteetti) { this.kapasiteetti = kapasiteetti; }
    public void setHinta(double hinta) { this.hinta = hinta; }
}
