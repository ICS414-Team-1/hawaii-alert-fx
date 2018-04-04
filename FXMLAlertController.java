import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class FXMLAlertController {
    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        //System.out.println("Hello");
        String text = ((Button)event.getSource()).getText();
        actiontarget.setText(text);
    }

}
