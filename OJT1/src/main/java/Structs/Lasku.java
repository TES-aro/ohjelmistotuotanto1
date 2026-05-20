package Structs;

import java.util.Date;

public class Lasku {

    private int laskuID;
    private Kayttaja maksaja;
    private Varaus varaus;
    private double kokonaissumma;
    private boolean maksettu;
    private Date luontipvm;
    private Date erapaiva;

    public Lasku(int id, Kayttaja maksaja, Varaus varaus, boolean maksettu,
                 Date luontipvm, Date erapaiva, double kokonaissumma){
        this.maksaja = maksaja;
        this.varaus = varaus;
        this.maksettu = maksettu;
        this.luontipvm = luontipvm;
        this.erapaiva = erapaiva;
    }

    public Lasku(Kayttaja maksaja, Varaus varaus,  Date erapaiva,
                  double kokonaissumma){
        this.maksaja = maksaja;
        this.varaus = varaus;
        this.maksettu = false;

        this.kokonaissumma = kokonaissumma;
        this.luontipvm = new Date();
        this.erapaiva = erapaiva;
    }
    // getterit

    public int getLaskuID() {
        return laskuID;
    }

    public Date getLuontipvm() {
        return luontipvm;
    }


    /*public int getPaivat() {
	    return paivat;
    }

     */

    public double getKokonaissumma() {
	    return kokonaissumma;
    }

    public Kayttaja getMaksaja() {
        return maksaja;
    }

    public Varaus getVaraus() {
        return varaus;
    }

   // public Laskutettava[] getLaskutettavat() {
   //     return laskutettavat;
   // }

    public boolean isMaksettu() {
        return maksettu;
    }

    public Date getErapaiva() {
        return erapaiva;
    }


    // setterit

    public void setMaksettu (boolean maksettu) {this.maksettu = maksettu;}
    public void setLaskuID(int laskuID) { this.laskuID = laskuID; }
    public void setLuontipvm(Date luontipvm) { this.luontipvm = luontipvm; }
    public void setErapaiva(Date erapaiva)    { this.erapaiva = erapaiva; }

}
