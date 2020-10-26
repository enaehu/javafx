package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.Details;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
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
    public void txertatuUpdate(String zerbitzua, Details det, File irudi){//METODO HAU ALDATU--> UPDATE
        String argit= det.getArgitaletxea();
        int orriKop= det.getOrrikop();
        //String irudia= det
        System.out.println(argit);
        String query= "update Liburua set argitaletxea=\""+argit+"\",orrikop='"+orriKop+"', irudia='"+irudi+"' where ISBN= '"+zerbitzua+"'";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        kud.execSQL(query);
    }


    public boolean dbanDago(String izena) throws SQLException {
        boolean badago= false;
        String query= "select argitaletxea from Liburua where izena='"+izena+"' AND argitaletxea is not null";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        ResultSet rs=kud.execSQL(query);
        if(rs.next()){ //ZERGATIK rs.next() EGITEAN null BEZALA AGERTZEN DA?--> ez da berdina baliorik ez egotea edo balioa=NULL izatea, horregatik emango du rs.next()=true
            badago=true;
        }
        return badago;
    }
    public Book dbtikLiburua(String izena) throws SQLException {
        String query= "select ISBN,izena,argitaletxea,orrikop,irudia from Liburua where izena='"+izena+"';";
        DBKudeatzailea kud = DBKudeatzailea.getInstantzia();
        ResultSet rs=kud.execSQL(query);
        String ize = null;
        String ISBN = null;
        String argitaletxea = null;
        int orrikop = 0;
        String url=null;
        while(rs.next()){  //while egin zeharkatzeko
            ize = rs.getString("izena");
            ISBN = rs.getString("ISBN");
            argitaletxea = rs.getString("argitaletxea");
            orrikop= rs.getInt("orrikop");
            url= rs.getString("irudia");
        }
        Book b = new Book(ize,ISBN);
        b.setThumbnail_url(url);
        Details det= new Details();
        det.setNumber_of_pages(orrikop);
        det.setPublishers(argitaletxea);
        b.setDetails(det);
        return b; //liburu berria sortu dugu datu basearen datuekin
    }

    public ObservableList<Book> liburuakDBnGorde() throws SQLException {//rs zeharkatu eta liburu berrian sartu, liburuak sortuz eta observablelist-ean sartuz.
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