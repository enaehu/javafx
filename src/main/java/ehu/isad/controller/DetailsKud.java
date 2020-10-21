package ehu.isad.controller;

import com.google.gson.Gson;
import ehu.isad.Main;
import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DetailsKud implements Initializable {

  // Reference to the main application.
  private Main mainApp;
  private Gson gson;
  private Book book;

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
    //String izena = b.toString();
    //String is = b.getIsbn();
    book = getLib(b.getIsbn());
    book.setIsbn(b.getIsbn());
    book.setIzena(b.getIzena());
    Details details = book.getDetails();
    //System.out.println(book.getIzena()+"hau da liburua klasearen .izena");
    //System.out.println(book.getXehetasunak().getIzenburua());
    lblIzenburua.setText(details.getIzenburua());
    lblArgitaletxe.setText(details.getArgitaletxea());
    lblOrriKop.setText(String.valueOf(details.getOrrikop()));
    String url = book.getThumbnail_url().replace("-S.", "-M.");
    Image i = createImage(url);
    irudiaField.setImage(i);
  }

  private Image createImage(String url) throws IOException {
    URLConnection conn = new URL(url).openConnection();
    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
    try (InputStream stream = conn.getInputStream()) {
      return new Image(stream);
    }
  }





}