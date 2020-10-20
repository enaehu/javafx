package ehu.isad;

import ehu.isad.Utils.Book;
import ehu.isad.Utils.Details;
import ehu.isad.controller.LiburuaKud;
import ehu.isad.controller.DetailsKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent liburuUI;
  private Parent detailsUI;

  private Stage stage;

  private LiburuaKud liburuaKud;
  private DetailsKud detailsKud;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Liburuak");
    stage.setScene(new Scene(liburuUI, 450, 275));
    stage.show();
  }

  private void pantailakKargatu() throws Exception {

    FXMLLoader loaderKautotu = new FXMLLoader(getClass().getResource("/libAukeratu.fxml"));
    liburuUI = (Parent) loaderKautotu.load();
    liburuaKud = loaderKautotu.getController();
    liburuaKud.setMainApp(this);

    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/libXehetas.fxml"));
    detailsUI = (Parent) loaderMain.load();
    detailsKud = loaderMain.getController();
    detailsKud.setMainApp(this);
  }


  public static void main(String[] args) {
    launch(args);
  }

  public void mainErakutsi(Book s) throws Exception {
    detailsKud.egin(s);
    stage.setScene(new Scene(liburuUI));
    stage.show();
  }
  /*public void liburuErakutsi() {
    stage.setScene(new Scene(detailsUI));
    stage.show();
  }*/
}
