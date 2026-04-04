package GUI;

import Structs.Kayttaja;

public class Stumps {
    static Kayttaja getKayttaja(String ID){
        //TODO hakee kayttajan databasesta.
        return new Kayttaja("Erkki Pertti", "12345", "test@test.com");
    }
}
