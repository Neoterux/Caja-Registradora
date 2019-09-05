package proyecto.Custom;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType) {
        super(alertType);
    }

    public CustomAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
    }
    
    
}
