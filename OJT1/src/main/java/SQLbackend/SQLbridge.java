package SQLbackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLbridge {
    //TODO ottaa yhteyden SQL palvelimeen.
    //write ja fetch operaatiot.
    //käyttäkää Struct paketin olioita public
    //metodejen palautuksissa ja inputeissa.

    //jos ja kun jotain puuttuu niistä niin lisätkää
    //ja mainitkaa disccordissa

    Connection connection;

    private SQLbridge(Connection connection){
        this.connection = connection;
    }

    static public SQLbridge connect(){
	    // error handling on vähän sinne päin. en ole vähään aikaan leikkinyt javalla
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }

        String sqlUrl = System.getenv("OJT1_SQL");
        if (sqlUrl == null) {
            throw new Error("could not connect to SQL. Missing OJT1_SQL env var");
        }
        String password = System.getenv("OJT1_PASSWORD");
        if (password == null) {
            throw new Error("could not find password. Missing OJT1_PASSWORD env var");
        }
        String user = System.getenv("OJT1_USER");
        if (user == null) {
            throw new Error("could not find user. Missing QJT1_USER env var");
        }

        try{
            Connection connection = DriverManager.getConnection(sqlUrl, user, password);
            return new SQLbridge(connection);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
}
