package Structs;

public class Mokki {
    String ID;
    String omistajaID;
    // voidaan myös toteuttaa luokallisesti vaatimalla Omistaja ryhmän olio.
    // Omistaja omistaja;
    String osoite;
    // mahdollisesti muut tiedot kuten koko, huoneet jne.

    public Mokki(String ID, String omistajaID){
        this.ID = ID;
        this.omistajaID = omistajaID;
    }
}
