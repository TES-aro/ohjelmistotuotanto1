package GUI;

import Structs.Kayttaja;

public class Stumps {
		//SQLbridge bridge;

    public Kayttaja getKayttaja(String ID){
        //TODO hakee kayttajan databasesta.
        return new Kayttaja("Erkki Pertti", "12345", "test@test.com");
    }
}

// tällä tyylillä kaikki viittaukset backend operaatioihin
// GUIa kirjoittaessa, kiitos. Myöhemmin näihin helppo upottaa ne oikeat funktiot.
