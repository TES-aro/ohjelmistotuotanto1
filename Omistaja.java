package Structs;

public class Omistaja {
    private String ID;
    private String nimi;
    private String sahkoposti;
    //private Mokki[] mokit;
    // vaihtoehtoisesti
    private String[] MokkiID;

    public Omistaja(String ID, String nimi, String sahkoposti, Mokki[] mokit){
        this.ID = ID;
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        //this.mokit = mokit;
    }

    // getterit

    public String getID() {
        return ID;
    }

    public String getNimi() {
        return nimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public String[] getMokkiID() {
        return MokkiID;
    }

    // setterit


    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setMokkiID(String[] mokkiID) {
        MokkiID = mokkiID;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }
}

