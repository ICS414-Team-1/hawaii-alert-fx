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
/**
 *
 *
 */
public class HawaiiAlertApp extends Application {
  @Override
  public void start(Stage primaryStage) {

      primaryStage.setTitle("Hawaii Alert System");

      primaryStage.show();

      BorderPane border = new BorderPane();
      HBox initTop = new HBox();
      initTop.setAlignment(Pos.CENTER);
      initTop.setPadding(new Insets(20, 10, 20, 10));

      initTop.setStyle("-fx-background-color: #38cdff");

      Text scenetitle = new Text("Hawaii Alert System");
      scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 80));

      initTop.getChildren().add(scenetitle);
      border.setTop(initTop);

      VBox rightBox = new VBox();
      rightBox.setStyle("-fx-background-color: #ededed");
      rightBox.setPrefWidth(100);
      border.setRight(rightBox);

      VBox leftBox = new VBox();
      leftBox.setStyle("-fx-background-color: #ededed");
      leftBox.setPrefWidth(100);
      border.setLeft(leftBox);

      GridPane grid = new GridPane();
      grid.setAlignment(Pos.TOP_CENTER);
      grid.setHgap(10);
      grid.setVgap(25);
      grid.setPadding(new Insets(80, 25, 25, 25));
      grid.setStyle("-fx-background-color: #ededed");

      Text instruction = new Text("Please select a choice:");
      instruction.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
      grid.add(instruction, 0, 0, 2, 1);

      Button testButton = new Button("TEST");
      testButton.setPrefSize(400, 80);
      testButton.setStyle("-fx-font: 40 Tahoma; -fx-base: #00870d");
      testButton.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent event){
              try {
                Parent root = FXMLLoader.load(getClass().getResource("fxml/Test.fxml"));
                Scene sceneTest = new Scene(root, 800, 600);
                primaryStage.setScene(sceneTest);
              }
              catch(Exception e) {
                System.out.println(e);
              }
          }
      });
      grid.add(testButton, 0, 2);

      Button alertButton = new Button("ALERT");
      alertButton.setPrefSize(400, 80);
      alertButton.setStyle("-fx-font: 40 Tahoma; -fx-base: #d10000");
      alertButton.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent event) {
            try {
              Parent root = FXMLLoader.load(getClass().getResource("fxml/Alert.fxml"));
              Scene sceneTest = new Scene(root, 800, 600);
              primaryStage.setScene(sceneTest);
            }
            catch(Exception e) {
              System.out.println(e);
            }
          }
      });
      grid.add(alertButton, 1, 2);

      border.setCenter(grid);

      Scene scene = new Scene(border, 800, 600);
      primaryStage.setScene(scene);
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }


}
