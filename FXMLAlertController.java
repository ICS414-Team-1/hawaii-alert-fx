import javafx.event.ActionEvent;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.TitledPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;

public class FXMLAlertController {
    private Stage primaryStage;
    private String alertType;

    private LinkedList<String> checkedBoxes = new LinkedList<String>();
    public FXMLAlertController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private TitledPane t1;
    @FXML private TitledPane t2;
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
    @FXML protected void changeDevicesTitle(ActionEvent event) {
        Object source = event.getSource();
        //System.out.println(((CheckBox)source).getText());
        if(((CheckBox)source).isSelected()) {
            //System.out.println(((CheckBox)source).getText());
            checkedBoxes.add(((CheckBox)source).getText());
        }
        else {
            checkedBoxes.remove(((CheckBox)source).getText());
        }
        t2.setText("Select devices " + checkedBoxes.toString());
    }
}
