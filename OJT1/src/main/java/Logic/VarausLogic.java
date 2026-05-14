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

    public Varaus luoVaraus(String varausID, Mokki mokki, Kayttaja varaaja, Date alku, Date loppu) {     // TODO päällekäisyyden esto (ei kahta varausta samalle ajalle yhdelle mökille)

        Varaus varaus = new Varaus(varausID, mokki, varaaja, alku, loppu);
        tallennus.lisaaVaraus(varaus);

        mokkiLogic.asetaVarausTila(mokki.getID(), true); // merkitse mökki varatuksi
        return varaus;
    }


    public void peruutaVaraus(Varaus varaus) { // peruuta varaus ja muuta mökin tila varaamattomaksi
        tallennus.poistaVaraus(varaus.getID());
        mokkiLogic.asetaVarausTila(varaus.getVarattuMokki().getID(), false);
    }


    public List<Varaus> haeKaikkiVaraukset() { // palauta kaikki varaukset
        return tallennus.haeVaraukset();
    }

    public List<Varaus> haeAsiakkaanVaraukset(Kayttaja kayttaja) { // palauta yksittäisen käyttäjän varaukset
        return tallennus.haeVarauksetKayttajalle(kayttaja.getUUID());
    }


}
