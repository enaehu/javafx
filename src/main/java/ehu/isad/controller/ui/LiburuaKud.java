package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.Book;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LiburuaKud implements Initializable {

  // Reference to the main application.
  private Main mainApp;
  private ObservableList<Book> liburuak;

  @FXML
  private Label LabLiburuak;

  @FXML
  private ComboBox<Book> comboZerbitzua;

  @FXML
  private Button BotoiaIkusi;

  @FXML
  void onClick(ActionEvent event) throws Exception {
    mainApp.liburuErakutsi((Book)comboZerbitzua.getValue());

  }

  public void setMainApp(Main main) {
    this.mainApp = main;
  }

 /* @FXML
  public void onClick(ActionEvent actionEvent) {
    System.out.println(txtErabiltzaile.getText() + ":" + txtPasahitza.getText());
    System.out.println(comboZerbitzua.getValue());

    if ("Flickr".equals(comboZerbitzua.getValue()) &&
        "juanan".equals(txtErabiltzaile.getText()) &&
        "pereira".equals(txtPasahitza.getText())) {

      mainApp.mainErakutsi();
    }*/

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //LEHENENGO DATUAK(IZENA+ISBN) DATU BASEAN GORDE BEHAR DIRA, ESKUZ EZ SARTZEKO. COMBOBOX-EAN OBSERVABLE BAT SARTUKO DA, ZERBITZUKUD KLASEAK BUELTATU DIONA.
    ZerbitzuKud zb= ZerbitzuKud.getInstance();
    try {
      liburuak=zb.liburuakDBnGorde();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    comboZerbitzua.setItems(liburuak);
    comboZerbitzua.setEditable(false);


  }

}