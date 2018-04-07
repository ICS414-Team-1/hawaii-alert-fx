import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FXMLMainController {
    private Stage primaryStage;
    public FXMLMainController(Stage primaryStage) {
      this.primaryStage = primaryStage;
    }

    @FXML protected void handleTestButtonAction(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Test.fxml"));
        loader.setController(new FXMLTestController(this.primaryStage));
        Parent root = loader.load();

         Scene scene = new Scene(root, 800, 600);

         this.primaryStage.setTitle("Hawaii Alert System");
         this.primaryStage.setScene(scene);
         this.primaryStage.show();
       }
       catch(Exception e) {
         System.out.println("Main controller: " + e);
       }
    }
    @FXML protected void handleAlertButtonAction(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Alert.fxml"));
        loader.setController(new FXMLAlertController(this.primaryStage));
        Parent root = loader.load();

         Scene scene = new Scene(root, 800, 600);

         this.primaryStage.setTitle("Hawaii Alert System");
         this.primaryStage.setScene(scene);
         this.primaryStage.show();
       }
       catch(Exception e) {
         System.out.println("Main controller: " + e);
       }
    }

}
