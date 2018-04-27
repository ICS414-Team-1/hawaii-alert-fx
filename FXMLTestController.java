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
import javafx.scene.paint.Color;
import devices.*;

public class FXMLTestController {
    private Stage primaryStage;
    private String alertType;
    private LinkedList<String> checkedBoxes = new LinkedList<String>();
    private LinkedList<String> checkedLocations = new LinkedList<String>();

    public FXMLTestController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private TitledPane t1;
    @FXML private TitledPane t2;
    @FXML private TitledPane t3;
    @FXML private TitledPane t4;
    @FXML private Accordion accordion;
    @FXML private VBox selectedAlertOut;
    @FXML private VBox selectedDevicesOut;
    @FXML private VBox selectedLocationsOut;
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

    @FXML protected void changeLocationsTitle(ActionEvent event) {
        Object source = event.getSource();
        if(((CheckBox)source).isSelected()) {
            checkedLocations.add(((CheckBox)source).getText());
        }
        else {
            checkedLocations.remove(((CheckBox)source).getText());
        }
        t3.setText("Select locations " + checkedLocations.toString());
    }

    @FXML protected void changePane1(ActionEvent event) {
        accordion.setExpandedPane(t2);
    }

    @FXML protected void changePane2(ActionEvent event) {
        accordion.setExpandedPane(t3);
    }

    @FXML protected void changePane3(ActionEvent event) {
        accordion.setExpandedPane(t4);
    }
    @FXML protected void checkIfEmpty(Action event) {
        
    }
    public void initialize() {
        t4.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                selectedAlertOut.getChildren().removeAll(selectedAlertOut.getChildren());
                selectedDevicesOut.getChildren().removeAll(selectedDevicesOut.getChildren());
                selectedLocationsOut.getChildren().removeAll(selectedLocationsOut.getChildren());
                Text selectedAlertType = new Text(alertType);
                selectedAlertOut.getChildren().add(selectedAlertType);
                for(String device: checkedBoxes) {
                    Text selectedDevice = new Text(device);
                    selectedDevicesOut.getChildren().add(selectedDevice);
                }

                for(String location: checkedLocations) {
                    Text selectedLocation = new Text(location);
                    selectedLocationsOut.getChildren().add(selectedLocation);
                }
            }
        });
    }

    @FXML protected void handleSendAlertButton(ActionEvent event) {
        for(String device: checkedBoxes) {
            if(device.equals("radio")) {
                String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                Radio radio1 = new Radio(alertType, s);
                radio1.warningSET(1);
                radio1.send();
            }
            else if(device.equals("televisions")) {
                String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                Television television1 = new Television(alertType, s);
                television1.warningSET(1);
                television1.send();
            }
            else {
                //Other devices will be implementing later
            }

        }
        Alert sentConfirm = new Alert(AlertType.INFORMATION);
        sentConfirm.setTitle("Confirmation Dialog");
        sentConfirm.setHeaderText("The test alert has been sent!");
        sentConfirm.showAndWait();
        backToMain();
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
