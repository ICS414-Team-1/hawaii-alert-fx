import javafx.event.ActionEvent;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import devices.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
public class FXMLLoginController {
    private Stage primaryStage;
    public static String loginName;
    public FXMLLoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private TextField usernameTF;
    @FXML private TextField passwordTF;
    @FXML private Text actiontarget;
    @FXML protected void handleLoginButtonAction(ActionEvent event) {
        if(checkValidation(usernameTF.getText(), passwordTF.getText())) {
            loginName = usernameTF.getText();
            backToMain();
        }
        else {
           actiontarget.setText("Invalid username/password");
        }
    }

    @FXML private void backToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            loader.setController(new FXMLMainController(this.primaryStage));
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);

            this.primaryStage.setTitle("Hawaii Alert System");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        }
        catch(Exception e) {
            System.out.println("Alert Controller: " + e);
        }
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
}
