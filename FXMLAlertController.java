import javafx.event.ActionEvent;
import java.util.*;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.TitledPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import devices.*;

public class FXMLAlertController {
    private Stage primaryStage;
    private String alertType;
    private LinkedList<String> checkedBoxes = new LinkedList<String>();
    private LinkedList<String> checkedLocations = new LinkedList<String>();

    public FXMLAlertController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML private TitledPane t1;
    @FXML private TitledPane t2;
    @FXML private TitledPane t3;
    @FXML private TitledPane t4;
    @FXML private TitledPane locationPane;
    @FXML private Accordion accordion;
    @FXML private Button btn3;
    @FXML private VBox selectedAlertOut;
    @FXML private VBox selectedDevicesOut;
    @FXML private VBox selectedLocationsOut;
    @FXML private ToggleButton tb0;
    @FXML protected void handleBackButtonAction(ActionEvent event) {
        backToMain();
    }

    /**
     *  Sets Confirmation Pane disabled if something isn't selected.
     */
    void setDisabled() {
        if(t1.getText().equals("Alert Type: ") || t2.getText().equals("Select devices: ") || t2.getText().equals("Select devices: []")
            || t3.getText().equals("Select locations: ") || t3.getText().equals("Select locations: []")) {
            t4.setDisable(true);
            btn3.setDisable(true);
        }
        else {
            t4.setDisable(false);
            btn3.setDisable(false);
        }
    }

    @FXML protected void changeTitle(ActionEvent event) {
        if (((ToggleButton)event.getSource()).isSelected()) {
            alertType = ((ToggleButton)event.getSource()).getText();
            t1.setText("Alert Type: " + alertType);
        }
        else {
            t1.setText("Alert Type: ");
        }
        setDisabled();
    }

    @FXML protected void changeDevicesTitle(ActionEvent event) {
        Object source = event.getSource();
        if(((ToggleButton)source).isSelected()) {
            checkedBoxes.add(((ToggleButton)source).getText());
        }
        else {
            checkedBoxes.remove(((ToggleButton)source).getText());
        }
        t2.setText("Select devices: " + checkedBoxes.toString());
        setDisabled();
    }

    @FXML protected void changeLocationsTitle(ActionEvent event) {
        Object source = event.getSource();
        if(((CheckBox)source).isSelected()) {
            checkedLocations.add(((CheckBox)source).getText());
        }
        else {
            checkedLocations.remove(((CheckBox)source).getText());
        }
        t3.setText("Select locations: " + checkedLocations.toString());
        setDisabled();
    }

    // Alert -> Devices
    @FXML protected void changePane1(ActionEvent event) {
        accordion.setExpandedPane(t2);
    }

    // Devices -> Location
    @FXML protected void changePane2(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Next")) {
            accordion.setExpandedPane(t3);
        }
        else {
            accordion.setExpandedPane(t1);
        }
    }

    // Location -> Final
    @FXML protected void changePane3(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Next")) {
            accordion.setExpandedPane(t4);
        }
        else {
            accordion.setExpandedPane(t2);
        }
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
        String message;
        String filename = "Log/realAlert.txt";
        String username = FXMLLoginController.loginName;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TextInputDialog confirm = new TextInputDialog();

        message = timeStamp + ":" + username + " just sent an alert about ";
        message +=  alertType;
        confirm.setHeaderText("Are you sure you want to send this warning?\nPlease type \"Affirmative\" to confirm");
        Optional<String> clickedButton = confirm.showAndWait();
        if(clickedButton.isPresent()) {
            String capcha = confirm.getEditor().getText().trim();
            if(capcha.equals("Affirmative")) {
                for(String device: checkedBoxes) {
                    switch (device) {
                        case "Radio":
                        {
                            message += " to all radio devices";
                            try {
                                userTracking(filename, message);
                            }
                            catch(IOException io) {
                                System.out.println(io);
                            }
                            // String s is the array form of the linked list checkedLocations
                            String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                            Radio radio1 = new Radio(alertType, s);
                            radio1.warningSET(2);
                            radio1.send();
                            break;
                        }
                        case "Television":
                        {
                            message += " to all television devices";
                            try {
                                userTracking(filename, message);
                            }
                            catch(IOException io) {
                                System.out.println(io);
                            }
                            String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                            Television television1 = new Television(alertType, s);
                            television1.warningSET(2);
                            television1.send();
                            break;
                        }
                        case "Siren":
                        {
                            message += " to all Siren devices";
                            try {
                                userTracking(filename, message);
                            }
                            catch(IOException io) {
                                System.out.println(io);
                            }
                            String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                            Siren siren1 = new Siren(alertType, s);
                            siren1.warningSET(2);
                            siren1.send();
                            break;
                        }
                        case "SMS":
                        {
                            message += " to all SMS devices";
                            try {
                                userTracking(filename, message);
                            }
                            catch(IOException io) {
                                System.out.println(io);
                            }
                            String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                            CellPhones cellphones1 = new CellPhones(alertType, s);
                            cellphones1.warningSET(2);
                            cellphones1.send();
                            break;
                        }
                        case "Email":
                        {
                            message += " to all Email devices";
                            try {
                                userTracking(filename, message);
                            }
                            catch(IOException io) {
                                System.out.println(io);
                            }
                            String[] s = checkedLocations.toArray(new String[checkedLocations.size()]);
                            Email email1 = new Email(alertType, s);
                            email1.warningSET(2);
                            email1.send();
                            break;
                        }
                        default:
                           break;
                    }

                }
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
    private void userTracking(String filename, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.append("\n");
        writer.append(message);

        writer.close();
    }


}
