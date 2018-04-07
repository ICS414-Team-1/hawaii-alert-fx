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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLAlertController {
    private Stage primaryStage;
    private String alertType;
    private LinkedList<String> checkedBoxes = new LinkedList<String>();

    public FXMLAlertController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private TitledPane t1;
    @FXML private TitledPane t2;
    @FXML private TitledPane t3;
    @FXML private Accordion accordion;
    @FXML private VBox selectedAlertOut;
    @FXML private VBox selectedDevicesOut;
    @FXML protected void handleBackButtonAction(ActionEvent event) {
        backToMain();
    }

    @FXML protected void changeTitle(ActionEvent event) {
        alertType = ((ToggleButton)event.getSource()).getText();
        t1.setText("Alert Type " + alertType);
    }
    @FXML protected void changeDevicesTitle(ActionEvent event) {
        Object source = event.getSource();
        if(((CheckBox)source).isSelected()) {
            checkedBoxes.add(((CheckBox)source).getText());
        }
        else {
            checkedBoxes.remove(((CheckBox)source).getText());
        }
        t2.setText("Select devices " + checkedBoxes.toString());
    }

    @FXML protected void changePane1(ActionEvent event) {
        accordion.setExpandedPane(t2);
    }

    @FXML protected void changePane2(ActionEvent event) {
        accordion.setExpandedPane(t3);
    }
    
    public void initialize() {
        t3.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                selectedAlertOut.getChildren().removeAll(selectedAlertOut.getChildren());
                selectedDevicesOut.getChildren().removeAll(selectedDevicesOut.getChildren());
                Text selectedAlertType = new Text(alertType);
                selectedAlertOut.getChildren().add(selectedAlertType);
                for(String device: checkedBoxes) {
                    Text selectedDevice = new Text(device);
                    selectedDevicesOut.getChildren().add(selectedDevice);
                }
            }
        });
    }
    @FXML protected void handleSendAlertButton(ActionEvent event) {
        TextInputDialog confirm = new TextInputDialog();
        confirm.setHeaderText("Are you sure you want to send this warning?\nPlease type \"Affirmative\" to confirm");
        confirm.showAndWait();
        String capcha = confirm.getEditor().getText().trim();
        if(capcha.equals("Affirmative")) {
            Alert sentConfirm = new Alert(AlertType.INFORMATION);
            sentConfirm.setTitle("Confirmation Dialog");
            sentConfirm.setHeaderText("The alert has been sent!");
            sentConfirm.showAndWait();
            backToMain();
        }
        else {
            Alert sentError = new Alert(AlertType.ERROR);
            sentError.setTitle("Confirmation Dialog");
            sentError.setHeaderText("You didn't entered the text correctly!");
            sentError.showAndWait();
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
}
