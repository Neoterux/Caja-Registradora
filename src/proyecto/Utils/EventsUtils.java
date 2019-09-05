package proyecto.Utils;


import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EventsUtils {

    public static void exitWindow(InputEvent evt){
        Node node = (Node) evt.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void openWindow(String pathToView,Object o, Node node) {
        Parent root;
        try{
            root = FXMLLoader.load(o.getClass().getResource(pathToView));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            node.getScene().getWindow().hide();

        }catch(IOException e){
            System.out.println("[Error]: No se pudo abrir nueva ventana >> " + e);
        }

    }

}
