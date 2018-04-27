/*
 *
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 *
 */
public class HawaiiAlertApp extends Application {
  @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Alert.fxml"));
        loader.setController(new FXMLAlertController(primaryStage));
        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Hawaii Alert System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }


}
