package GUI;

import Structs.Kayttaja;

public class Stumps {
		//SQLbridge bridge;

    public Kayttaja getKayttaja(String ID){
        //TODO hakee kayttajan databasesta.
        return new Kayttaja("Erkki Pertti", "12345", 567, "test@test.com", "045045045");
    }
}

// tällä tyylillä kaikki viittaukset backend operaatioihin
// GUIa kirjoittaessa, kiitos. Myöhemmin näihin helppo upottaa ne oikeat funktiot.
