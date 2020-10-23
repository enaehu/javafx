package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.Details;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<String> lortuZerbitzuak() {

        String query = "select id, izena from services";
        DBKudeatzailea dbKudeatzaile = DBKudeatzailea.getInstantzia();
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
    public void txertatuUpdate(String zerbitzua, Details det){//METODO HAU ALDATU--> UPDATE
        String argit= det.getArgitaletxea();
        int orriKop= det.getOrrikop();
        String irudia= det
        System.out.println(argit);
        String query= "update Liburua set argitaletxea=\""+argit+"\",orrikop='"+orriKop+"', irudia where ISBN= '"+zerbitzua+"';";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        kud.execSQL(query);
    }
   public boolean dbanDago(String izena) throws SQLException {
        boolean badago= false;
        String query= "select argitaletxea from Liburua where izena='"+izena+"';";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        ResultSet rs=kud.execSQL(query);
        if(rs.next()){
            badago=true;
        }
        return badago;
    }

    public Book dbtikLiburua(String izena) throws SQLException {
        String query= "select ISBN,izena,argitaletxea,orrikop from Liburua where izena='"+izena+"';";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        ResultSet rs=kud.execSQL(query);
        String ize = null;
        String ISBN = null;
        String argitaletxea = null;
        int orrikop = 0;
        while(rs.next()){  //while egin zeharkatzeko
            ize = rs.getString("izena");
            ISBN = rs.getString("ISBN");
            argitaletxea = rs.getString("argitaletxea");
            orrikop= rs.getInt("orrikop");
        }
        Book b = new Book(ize,ISBN);
        Details det= new Details();
        det.setNumber_of_pages(orrikop);
        det.setPublishers(argitaletxea);
        b.setDetails(det);
        return b; //liburu berria sortu dugu datu basearen datuekin
    }
    //DATU BASEAN LIBURUAK(IZENA+ISBN) SARTZEKO METODOA SORTU ETA LIBURUAKUD KLASETIK DEITU
    public ObservableList<Book> liburuakDBnGorde() throws SQLException {
        //liburuak aukeratu SELECT ISBN,IZENA FROM LIBURUA
        //rs zeharkatu eta liburu berrian sartu, liburuak sortuz eta observablelist-ean sartuz.
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        String query= "select ISBN,izena from Liburua";
        ResultSet rs=kud.execSQL(query);
        String iz=null, ISBN=null;
        ObservableList<Book> liburuak= FXCollections.observableArrayList();
        while(rs.next()){
            iz=rs.getString("izena");
            ISBN=rs.getString("ISBN");
            Book b= new Book(iz,ISBN); //liburu bat sortuko du datu lerro bakoitzeko
            liburuak.add(b); //observableList-ean sartu
        }
        return liburuak;
    }
}