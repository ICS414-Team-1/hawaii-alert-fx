import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FXMLTestController {
    private Stage primaryStage;
    public FXMLTestController(Stage primaryStage) {
      this.primaryStage = primaryStage;
    }
    @FXML private Text actiontarget;

    @FXML protected void handleBackButtonAction(ActionEvent event) {
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
         System.out.println("Test Controller: " + e);
       }
    }

}
