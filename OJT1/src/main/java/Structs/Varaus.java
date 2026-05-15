package Structs;

import java.util.Date;

public class Varaus {
    private String ID;
    private Mokki varattuMokki;
    private Kayttaja varaaja;
    private Date alku;
    private Date loppu;

    public Varaus(String ID, Mokki varattuMokki, Kayttaja varaaja, Date alku, Date loppu){
        this.ID = ID;
        this.varattuMokki = varattuMokki;
        this.varaaja = varaaja;
        this.alku = alku;
        this.loppu = loppu;
    }

    // getterit

    public String getID() {
        return ID;
    }

    public Mokki getVarattuMokki() {
        return varattuMokki;
    }

    public Kayttaja getVaraaja() {return varaaja;}

    public Date getAlku() {
        return alku;
    }

    public Date getLoppu() {
        return loppu;
    }

    // setterit

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAlku(Date alku) {
        this.alku = alku;
    }

    public void setLoppu(Date loppu) {
        this.loppu = loppu;
    }

    public void setVaraaja(Kayttaja varaaja) {
        this.varaaja = varaaja;
    }

    public void setVarattuMokki(Mokki varattuMokki) {
        this.varattuMokki = varattuMokki;
    }
}
