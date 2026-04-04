package Structs;

public class Omistaja {
    String ID;
    String nimi;
    String sahkoposti;
    Mokki[] mokit;
    // vaihtoehtoisesti
    //String[] MokkiID;

    public Omistaja(String ID, String nimi, String sahkoposti, Mokki[] mokit){
        this.ID = ID;
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        this.mokit = mokit;
    }
}
