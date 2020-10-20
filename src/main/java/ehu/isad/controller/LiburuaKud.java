package ehu.isad.controller;

import ehu.isad.Main;
import ehu.isad.Utils.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
  mainApp.mainErakutsi(comboZerbitzua.getValue());

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
    liburuak = FXCollections.observableArrayList();
    liburuak.addAll(
            new Book("R for Data Science","1491910399" ),
            new Book("Fluent Python","1491946008"),
            new Book( "Data Algorithms","9781491906187")
    );
    comboZerbitzua.setItems(liburuak);
    comboZerbitzua.setEditable(false);


  }

}