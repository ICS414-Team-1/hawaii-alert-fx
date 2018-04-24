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

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.control.Button;


import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.Label;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
/**
 *
 *
 */
public class HawaiiAlertApp extends Application {
  @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                if(checkValidation(username, password)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
                    loader.setController(new FXMLMainController(primaryStage));
                    try {

                        Parent root = loader.load();

                        Scene scene = new Scene(root, 800, 600);

                        primaryStage.setTitle("Hawaii Alert System");
                        primaryStage.setScene(scene);
                    }
                    catch(Exception ie) {
                        System.out.println(ie);
                    }
                  }
                 else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Invalid username/password");
                 }
            }
        });

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Boolean checkValidation(String username, String password) {
        boolean valid = false;
        File jsonInputFile = new File("config/users.json");
        InputStream iStream;
        try {
            iStream = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(iStream);
            // Get the JsonObject structure from JsonReader.
            JsonObject usersObj = reader.readObject();
            reader.close();
            JsonArray users = usersObj.getJsonArray("users");
            for(int i = 0; i < users.size(); i++) {
                JsonObject user = users.getJsonObject(i);
                if(user.getString("username").equals(username)) {
                    if(user.getString("password").equals(password)) {
                        valid = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return valid;
    }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }


}
