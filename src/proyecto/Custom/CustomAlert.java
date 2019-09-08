package proyecto.Custom;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author labfe
 */
public class CustomAlert extends Alert {

    /**
     *
     * @param alertType
     */
    public CustomAlert(AlertType alertType) {
        super(alertType);
    }

    /**
     *
     * @param alertType
     * @param contentText
     * @param buttons
     */
    public CustomAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
    }
    
    
}
