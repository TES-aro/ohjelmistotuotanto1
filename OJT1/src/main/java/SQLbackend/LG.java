package SQLbackend;

import java.sql.Date;

public class LG {
    public static void log(String s){
        System.out.println(s);
    }
    public static Date convert2SQL(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
    public static java.util.Date convert2util(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
