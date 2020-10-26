package ehu.isad.controller.ui;

import com.google.gson.Gson;
import ehu.isad.Main;
import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Utils.Sarea;
import ehu.isad.Utils.Utils;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DetailsKud implements Initializable {

  // Reference to the main application.
  private Main mainApp;
  private Gson gson;
  private Book book;
  ZerbitzuKud zb = ZerbitzuKud.getInstance();

  @FXML
  private Label labelIrudi;

  @FXML
  private ImageView irudiaField;

  @FXML
  private Button atzeraBotoia;

  @FXML
  private Label labelIzenburua;

  @FXML
  private Label labelArgit;

  @FXML
  private Label labelOrriKop;
  @FXML
  private Label lblIzenburua;

  @FXML
  private Label lblArgitaletxe;

  @FXML
  private Label lblOrriKop;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }


  @FXML
  void atzeraEgin(ActionEvent event) {
    mainApp.mainErakutsi();
  }

  public void setMainApp(Main liburuak) throws Exception {
    mainApp = liburuak;
  }

  public Book getLib(String s) throws Exception {
    Book emaitza = Sarea.URLlortu(s);
    return emaitza;
  }

  public void egin(Book b) throws Exception {
    book = getLib(b.getIsbn());
    book.setIsbn(b.getIsbn());
    book.setIzena(b.getIzena());
    Details details = book.getDetails();
    lblIzenburua.setText(details.getIzenburua());
    lblArgitaletxe.setText(details.getArgitaletxea());
    lblOrriKop.setText(String.valueOf(details.getOrrikop()));
    String url = book.getThumbnail_url().replace("-S.", "-M.");
    Image i = createImage(url);
    irudiaField.setImage(i);
    Properties properties = Utils.lortuEzarpenak();
    File outputfile = new File(properties.getProperty("imagePath")+book.getIsbn()+".png");
    zb.txertatuUpdate(book.getIsbn(), details,outputfile);
    URL Url = new URL(url);
    gordeArgazkia(Url,book.getIsbn());

  }
  private void gordeArgazkia(URL Url, String isbn) throws IOException {
    Properties properties = Utils.lortuEzarpenak();
    BufferedImage image = ImageIO.read(Url);
    File outputfile = new File(properties.getProperty("imagePath")+isbn+".png");
    ImageIO.write(image, "png", outputfile);
  }
  private Image createImage(String url) throws IOException {
    URLConnection conn = new URL(url).openConnection();
    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
    try (InputStream stream = conn.getInputStream()) {
      return new Image(stream);
    }

  }

  public void labelEguneraketa(String izena, String argit, int orrikop, String isbn,String url) throws IOException {
    lblIzenburua.setText(izena);
    lblArgitaletxe.setText(argit);
    lblOrriKop.setText(String.valueOf(orrikop));
    Properties properties = Utils.lortuEzarpenak();
    Image image = new Image(new FileInputStream(properties.getProperty("imagePath")+isbn+".png"));
    irudiaField.setImage(image);
  }

  public void liburuaLortu(String izena ,String isbn) throws Exception {
    Book liburua= new Book("","");
    if (zb.dbanDago(izena)) {    //badago datubasean, beraz ez dugu internetetik hartu behar
      //DATU BASETIK HARTU
      Book b= zb.dbtikLiburua(izena);
      //LABELAK EGUNERATU
      labelEguneraketa(b.getIzena(),b.getDetails().getArgitaletxea(),b.getDetails().getOrrikop(),b.getIsbn(), b.getThumbnail_url().replace("-S.", "-M."));
    }else{  //internetetik liburua lortu;
      liburua.setIzena(izena);
      liburua.setIsbn(isbn); //isbn-a sartzen diot, egin(liburua) metodoari deitzean liburu horrek isbn izateko eta API-tik liburua lortzean ISBN-arekin egiteko
      egin(liburua);
    }
  }
}



