package SQLbackend;

import Logic.TallennusLogic;
import Structs.Mokki;
import SQLbackend.SQLbridge;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        public static void main(String[] args) {
            SQLbridge bridge = new SQLbridge();
            System.out.println("Yhteys: " + bridge.getConnection());

            TallennusLogic logic = new TallennusLogic(bridge.getConnection());

            // test inserting a cabin
            int id = logic.getNextID("mokit", "mokki_id");
            System.out.println("Next ID: " + id);

            Mokki mokki = new Mokki(id, 4, false, 120.0);
            logic.lisaaMokki(mokki);
            System.out.println("Mökki lisätty ID:llä: " + id);

            // test fetching it back
            Mokki haettu = logic.haeMokki(id);
            System.out.println("Haettu mökki - kapasiteetti: " + haettu.getKapasiteetti() + " hinta: " + haettu.getHinta());

            // test fetching all
            List<Mokki> kaikki = logic.haeMokit();
            System.out.println("Mökkejä yhteensä: " + kaikki.size());

            // test update
            mokki.setHinta(150.0);
            logic.paivitaMokki(mokki);
            System.out.println("Hinta päivitetty");

            // test delete
            logic.poistaMokki(id);
            System.out.println("Mökki poistettu");

            // verify deletion
            List<Mokki> jaljella = logic.haeMokit();
            System.out.println("Mökkejä jäljellä: " + jaljella.size());
        }
    }
}