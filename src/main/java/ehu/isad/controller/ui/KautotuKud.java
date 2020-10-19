package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KautotuKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;
    private ZerbitzuKud zk = ZerbitzuKud.getInstance();
    private ObservableList<String> zerbitzuak;
    private List<String> zerbitzuList;

    @FXML
    private ComboBox comboZerbitzua;

    @FXML
    private TextField txtErabiltzaile;

    @FXML
    private TextField txtPasahitza;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) {
        System.out.println(txtErabiltzaile.getText() + ":" + txtPasahitza.getText());
        System.out.println(comboZerbitzua.getValue());

        if ("Flickr".equals(comboZerbitzua.getValue()) &&
                "juanan".equals(txtErabiltzaile.getText()) &&
                "pereira".equals(txtPasahitza.getText())) {

            mainApp.mainErakutsi();
        }
    }

    @FXML
    public void onClickEzabatu(ActionEvent event) {
        //Eskaera egin
        String zerbitzua = (String)comboZerbitzua.getValue();
        zk.ezabatu(zerbitzua);
        zerbitzuak.remove(zerbitzua);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> zerbitzuList = ZerbitzuKud.getInstance().lortuZerbitzuak();
        zerbitzuak = FXCollections.observableArrayList(zerbitzuList);
        comboZerbitzua.setItems( zerbitzuak );
    }
}
