package ehu.isad.controller.db;

import ehu.isad.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    private String unekoEskaera;

    private DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<String> lortuZerbitzuak() {

        String query = "select id, izena from services";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                int kodea = rs.getInt("id");
                String izena = rs.getString("izena");

                System.out.println(kodea + ":" + izena);
                emaitza.add(izena);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void ezabatu(String zerbitzua){
        unekoEskaera = "delete from zerbitzuak.services where izena = '"+zerbitzua+"';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(unekoEskaera);
    }

    public Book liburuaEskatu(String isbn) throws SQLException {

        Book emaitza;
        unekoEskaera = "select isbn from book where isbn ='"+isbn+"'";
        ResultSet rs = dbk.execSQL(unekoEskaera);
        while(rs.next()){
            String izena = rs.getString("title");
            String kodea = rs.getString("isbn");
        }
        return emaitza;
    }
}
