package Structs;

import java.util.Date;

public class Lasku {
    Kayttaja maksaja;
    Varaus varaus;
    Laskutettava[] laskutettavat;
    boolean maksettu; // lisätty
    Date erapaiva; // lisätty

    public Lasku(Kayttaja maksaja, Varaus varaus, boolean maksettu, Date erapaiva, Laskutettava[] laskutettavat){
        this.maksaja = maksaja;
        this.varaus = varaus;
        this.maksettu = maksettu;
        this.erapaiva = erapaiva;
        this.laskutettavat = laskutettavat;
    }

    // getterit

    public Kayttaja getMaksaja() {
        return maksaja;
    }

    public Varaus getVaraus() {
        return varaus;
    }

    public Laskutettava[] getLaskutettavat() {
        return laskutettavat;
    }

    public boolean isMaksettu() {
        return maksettu;
    }

    public Date getErapaiva() {
        return erapaiva;
    }

    // setterit

    public void setMaksettu (boolean maksettu) {this.maksettu = maksettu;}
    public void setErapaiva(Date erapaiva)    { this.erapaiva = erapaiva; }

}
