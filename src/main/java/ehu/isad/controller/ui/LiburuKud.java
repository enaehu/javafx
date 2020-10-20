package ehu.isad.controller.ui;

import ehu.isad.Book;
import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.List;

public class LiburuKud {

    private ObservableList<Book> liburuak;
    List<Book> liburuList;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button botoia;

    private Main mainApp;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }


    @FXML
    void sacatu(ActionEvent event) throws Exception {
        Book b = (Book) comboBox.getValue();
        mainApp.xeheErakutsi(b);
    }

    @FXML
    public void initialize() throws SQLException {
        liburuList = ZerbitzuKud.getInstance().lortuZerbitzuak();
        liburuak = FXCollections.observableArrayList(liburuList);


        Book b = new Book("1491910399", "R for Data Science");
        if(ZerbitzuKud.getInstance().liburuaEskatu(b.getISBN())==null){
            liburuak.add(b);
        }

        Book b2 = new Book("","");
        b2.setTitle("Fluent Python");
        b2.setIsbn("1491946008");
        if(ZerbitzuKud.getInstance().liburuaEskatu(b.getISBN())==null){
            liburuak.add(b2);
        }

        Book b1 = new Book("","");
        b1.setTitle("Data Algorithms");
        b1.setIsbn("9781491906187");
        if(ZerbitzuKud.getInstance().liburuaEskatu(b.getISBN())==null){
            liburuak.add(b1);
        }

        comboBox.setItems(liburuak);


    }


}
