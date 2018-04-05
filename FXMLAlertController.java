import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;

public class FXMLAlertController {
    private Stage primaryStage;
    private String alertType;

    public FXMLAlertController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private Text actiontarget;
    @FXML private TitledPane t1;

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
            System.out.println("Alert Controller: " + e);
        }
    }

    @FXML protected void changeTitle(ActionEvent event) {
        alertType = ((ToggleButton)event.getSource()).getText();
        t1.setText("Alert Type " + alertType);
    }
}
