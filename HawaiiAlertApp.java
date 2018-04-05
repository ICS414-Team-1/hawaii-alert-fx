/*
 *
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.Group;
import javafx.scene.control.TitledPane;
import javafx.fxml.LoadException;
/**
 *
 *
 */
public class HawaiiAlertApp extends Application {
  @Override
    public void start(Stage primaryStage) throws Exception {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
      loader.setController(new FXMLMainController(primaryStage));
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
