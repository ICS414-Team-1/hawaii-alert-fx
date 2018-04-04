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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Accordion;
import javafx.scene.Group;
import javafx.scene.control.TitledPane;
/**
 *
 * 
 */
public class HawaiiAlertSystem extends Application {

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
      public void handle(ActionEvent event) {
        initTop.setStyle("-fx-background-color: #00870d");
        scenetitle.setText("TEST");
        scenetitle.setFill(Color.WHITE);
        rightBox.setStyle("-fx-background-color: #00870d");
        rightBox.setPrefWidth(100);
        leftBox.setStyle("-fx-background-color: #00870d");
        leftBox.setPrefWidth(100);

      }
    });
    grid.add(testButton, 0, 2);

    Button alertButton = new Button("ALERT");
    alertButton.setPrefSize(400, 80);
    alertButton.setStyle("-fx-font: 40 Tahoma; -fx-base: #d10000");
    alertButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        initTop.setStyle("-fx-background-color: #d10000");
        scenetitle.setText("ALERT");
        scenetitle.setFill(Color.WHITE);
        rightBox.setStyle("-fx-background-color: #d10000");
        leftBox.setStyle("-fx-background-color: #d10000");
        border.setCenter(choices());
      }
    });
    grid.add(alertButton, 1, 2);

    border.setCenter(grid);



    Scene scene = new Scene(border, 800, 600);
    primaryStage.setScene(scene);


  }

    Accordion choices() { // might change to a scene return value

      VBox content1 = new VBox();
      BorderPane border = new BorderPane();
      HBox initTop = new HBox();
      initTop.setAlignment(Pos.CENTER);
      initTop.setPadding(new Insets(20, 10, 20, 10));
      Text scenetitle = new Text("Please select alert type:");
      initTop.getChildren().add(scenetitle);
      border.setTop(initTop);
      
      

      TitledPane t1 = new TitledPane();
      t1.setText("Choices: ");
      GridPane grid = new GridPane();
      grid.setPadding(new Insets(5, 5, 5, 5));
      ToggleGroup tg = new ToggleGroup();
      ToggleButton tgb[] = new ToggleButton[8];
      tgb[0] = new ToggleButton("B1");
      tgb[1] = new ToggleButton("B2");
      tgb[2] = new ToggleButton("B3");
      tgb[3] = new ToggleButton("B4");
      tgb[4] = new ToggleButton("B5");
      tgb[5] = new ToggleButton("B6");
      tgb[6] = new ToggleButton("B7");
      tgb[7] = new ToggleButton("B8");

      for (ToggleButton i : tgb) {
        i.setToggleGroup(tg);
        i.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            t1.setText("Choice: " + i.getText());
          }
        });
      }

      grid.add(tgb[0], 1, 0);
      grid.add(tgb[1], 2, 0);
      grid.add(tgb[2], 3, 0);
      grid.add(tgb[3], 4, 0);
      grid.add(tgb[4], 1, 1);
      grid.add(tgb[5], 2, 1);
      grid.add(tgb[6], 3, 1);
      grid.add(tgb[7], 4, 1);
      grid.setAlignment(Pos.CENTER);
      border.setCenter(grid);
      // border.setCenter(grid);
      content1.getChildren().add(border);
      t1.setContent(content1);
      TitledPane t2 = new TitledPane("T2", new Button("A2"));
      TitledPane t3 = new TitledPane("T3", new Button("A3"));

      Accordion accordion = new Accordion();
      accordion.getPanes().addAll(t1, t2, t3);
      accordion.setExpandedPane(t1);
      
      return accordion;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
    }

    

  }