package Logic;


import Structs.Kayttaja;
import Structs.Mokki;
import Structs.Varaus;

import java.util.Date;
import java.util.List;

// varauksen luonti peruutus ja haku

public class VarausLogic {

    private final TallennusLogic tallennus;
    private final MokkiLogic mokkiLogic;


    public VarausLogic(TallennusLogic tallennus, MokkiLogic mokkiLogic) {
        this.tallennus = tallennus;
        this.mokkiLogic = mokkiLogic;
    }

    public Varaus luoVaraus(int varausID, Mokki mokki, Kayttaja varaaja, Date alku, Date loppu) {
        if (onPaallekkain(mokki.getID(), alku, loppu)) { // päällekkäisyyden tarkastus
            throw new IllegalStateException(
                    "Mökki " + mokki.getID() + " on jo varattu tälle ajalle."
            );
        }

        long aikaero = loppu.getTime() - alku.getTime(); // tämmöinen lasku, jolla lasketaan yhteen hinta yömäärän perusteella
        long yomaara = aikaero / (1000 * 60 * 60 * 24);
        double hinta = yomaara * mokki.getHinta();


        Varaus varaus = new Varaus(varausID, mokki, varaaja, alku, loppu, hinta);
        tallennus.lisaaVaraus(varaus);
        return varaus;
    }

    private boolean onPaallekkain(int mokkiID, Date alku, Date loppu) {
        List<Varaus> varaukset = tallennus.haeVaraukset();
        for (Varaus v : varaukset) {
            if (v.getVarattuMokki().getID() == mokkiID) {
                if (alku.before(v.getLoppu()) && loppu.after(v.getAlku())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void peruutaVaraus(Varaus varaus) { // peruuta varaus ja muuta mökin tila varaamattomaksi
        tallennus.poistaVaraus(varaus.getID());
        //mokkiLogic.asetaVarausTila(varaus.getVarattuMokki().getID(), false);
    }


    public List<Varaus> haeKaikkiVaraukset() { // palauta kaikki varaukset
        return tallennus.haeVaraukset();
    }

    public List<Varaus> haeAsiakkaanVaraukset(Kayttaja kayttaja) { // palauta yksittäisen käyttäjän varaukset
        return tallennus.haeVarauksetKayttajalle(kayttaja.getID());
    }


}
