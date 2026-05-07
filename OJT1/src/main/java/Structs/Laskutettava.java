package Structs;

public class Laskutettava {
    private String tuote;
    private double hinta;

    public Laskutettava(String tuote, Double hinta){
        this.tuote = tuote;
        this.hinta = hinta;
    }

    // getterit

    public String getTuote() {
        return tuote;
    }

    public double getHinta() {
        return hinta;
    }

    // setterit

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public void setTuote(String tuote) {
        this.tuote = tuote;
    }

}
