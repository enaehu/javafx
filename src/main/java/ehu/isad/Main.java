package ehu.isad;

import ehu.isad.controller.LiburuaKud;
import ehu.isad.controller.DetailsKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private Parent liburuUI;
  private Parent detailsUI;

  private Stage stage;

  private LiburuaKud liburuaKud;
  private DetailsKud detailsKud;
  private Scene liburu,xehetasun;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Liburuak");
    stage.setScene(liburu);
    stage.show();
  }

  private void pantailakKargatu() throws Exception {

    FXMLLoader loaderKautotu = new FXMLLoader(getClass().getResource("/libAukeratu.fxml"));
    liburuUI = (Parent) loaderKautotu.load();
    liburuaKud = loaderKautotu.getController();
    liburuaKud.setMainApp(this);
    liburu = new Scene(liburuUI);

    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/libXehetas.fxml"));
    detailsUI = (Parent) loaderMain.load();
    detailsKud = loaderMain.getController();
    detailsKud.setMainApp(this);
    xehetasun = new Scene(detailsUI);
  }


  public static void main(String[] args) {
    launch(args);
  }

  public void mainErakutsi() {

    stage.setScene(liburu);
    stage.show();
  }
  public void liburuErakutsi(Book b) throws Exception {
    detailsKud.egin(b);
    stage.setScene(xehetasun);
    stage.show();
  }
}
