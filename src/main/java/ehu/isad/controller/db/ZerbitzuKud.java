package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.Details;

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

    public List<Book> lortuZerbitzuak() {

        String query = "select isbn, title from book";

        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String kodea = rs.getString("isbn");
                String izena = rs.getString("title");

                Book b = new Book(kodea,izena);
                emaitza.add(b);

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

        Book emaitza = null;
        unekoEskaera = "select isbn from book where isbn ='"+isbn+"'";
        ResultSet rs = dbk.execSQL(unekoEskaera);
        if(rs==null){
            String izena = rs.getString("izena");
            String is = rs.getString("isbn");
            emaitza = new Book(izena,is);
        }
        return emaitza;
    }

    public void sartuDb(Book b){
        String izena = b.toString();
        String isbn = b.getISBN();
        unekoEskaera="insert into openlibrary.book values('"+izena+"','"+isbn+"','"+izena+"')";
        dbk.execSQL(unekoEskaera);

        Details d = b.getDetails();
        int numberOfPages = d.getPages();
        String publisher = d.getArgitaretxea();
        unekoEskaera = "insert into details values('"+numberOfPages+"','"+isbn+"','"+publisher+"')";
        dbk.execSQL(unekoEskaera);

    }

    public int orriakEskuratu(Book b) throws SQLException {
        unekoEskaera= "select numberOfPages from details where isbn='"+b.getISBN()+"'";
        ResultSet rs = dbk.execSQL(unekoEskaera);
        int orriKop = 0;
        while(rs.next()){
            orriKop = rs.getInt("numberOfPages");
        }
        return orriKop;
    }

    public String getPublisher(Book b) {
        unekoEskaera= "select publisher from details where isbn='"+b.getISBN()+"'";
        ResultSet rs = dbk.execSQL(unekoEskaera);
        String publisher = "";
        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                publisher = rs.getString("publisher");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return publisher;
    }
}
