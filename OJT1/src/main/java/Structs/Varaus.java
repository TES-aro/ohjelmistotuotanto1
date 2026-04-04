package Structs;

import java.util.Date;

public class Varaus {
    String ID;
    Mokki varattuMokki;
    Kayttaja varaaja;
    Date alku;
    Date loppu;

    public Varaus(String ID, Mokki varattuMokki, Kayttaja varaaja, Date alku, Date loppu){
        this.ID = ID;
        this.varattuMokki = varattuMokki;
        this.varaaja = varaaja;
        this.alku = alku;
        this.loppu = loppu;
    }
}
