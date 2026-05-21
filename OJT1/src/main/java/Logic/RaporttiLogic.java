package Logic;

import Structs.Mokki;
import Structs.Varaus;

import java.util.List;

public class RaporttiLogic {

    private final TallennusLogic tallennus;

    public RaporttiLogic(TallennusLogic tallennus) {
        this.tallennus = tallennus;
    }

    public String muodostaRaportti() {
        List<Mokki> mokit = tallennus.haeMokit();
        List<Varaus> varaukset = tallennus.haeVaraukset();

        StringBuilder sb = new StringBuilder();
        sb.append("Raportti\n\n");

        for (Mokki m : mokit) {
            String tila;

            if (onVarattuNyt(m, varaukset) == true) {
                tila = "VARATTU";
            } else {
                tila = "VAPAA";
            }
            
            sb.append("Mökki #").append(m.getID())
                    .append(" - ").append(m.getOsoite())
                    .append(" - ").append(m.getKapasiteetti()).append(" hlö")
                    .append(" - ").append(m.getHinta()).append(" euroa/yö")
                    .append(" - ").append(tila)
                    .append("\n");
        }

        return sb.toString();
    }

    private boolean onVarattuNyt(Mokki mokki, List<Varaus> varaukset) {
        long nyt = System.currentTimeMillis();
        for (Varaus v : varaukset) {
            if (v.getVarattuMokki().getID() == mokki.getID()
                    && nyt >= v.getAlku().getTime()
                    && nyt <= v.getLoppu().getTime()) {
                return true;
            }
        }
        return false;
    }
}
