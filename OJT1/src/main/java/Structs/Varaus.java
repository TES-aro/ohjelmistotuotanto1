package Structs;

import java.util.Date;

public class Varaus {
    private int ID;
    private Mokki varattuMokki;
    private Kayttaja varaaja;
    private Date alku;
    private Date loppu;
    private double hinta;

<<<<<<< HEAD
    public Varaus(int ID, Mokki varattuMokki, Kayttaja varaaja, Date alku, Date loppu, Double hinta){
=======
    public Varaus(int ID, Mokki varattuMokki, Kayttaja varaaja, Date alku, Date loppu){
>>>>>>> 5a0f722239bfb7898cea21f07b098071b29478b7
        this.ID = ID;
        this.varattuMokki = varattuMokki;
        this.varaaja = varaaja;
        this.alku = alku;
        this.loppu = loppu;
        this.hinta = hinta;
    }

    // getterit

<<<<<<< HEAD
    public double getHinta() {
	    return hinta;
    }

    public String getID() {
=======
    public int getID() {
>>>>>>> 5a0f722239bfb7898cea21f07b098071b29478b7
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
    public void setHinta(double hinta){
	    this.hinta = hinta;
    }

    public void setID(int ID) {
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
