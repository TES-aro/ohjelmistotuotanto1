package Structs;

public class Lasku {
    Kayttaja maksaja;
    Varaus varaus;
    Laskutettava[] laskutettavat;

    public Lasku(Kayttaja maksaja, Varaus varaus, Laskutettava[] laskutettavat){
        this.maksaja = maksaja;
        this.varaus = varaus;
        this.laskutettavat = laskutettavat;
    }
}
