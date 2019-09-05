package proyecto.Custom;


import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import proyecto.Controllers.AdminController;
import proyecto.Controllers.UserController;
import proyecto.POJO.Controller.ControllerWorker;
import proyecto.POJO.Worker;

public class LogInDialog extends Dialog {

    

    public LogInDialog( ){
        setTitle("Iniciar Sesion");
        setHeaderText("Bienvenido, inicie sesión por favor ");
       getDialogPane().getStylesheets().add(getClass().getResource("/proyecto/Views/CSS/dark-theme.css").toExternalForm());
       getDialogPane().getStylesheets().add(getClass().getResource("/proyecto/Views/CSS/login.css").toExternalForm());
        //Configuring login image
        System.out.println(new File("/proyecto/Sources/user-logo.png").getPath());
        System.out.println(new File("").getAbsolutePath());
        try{
        ImageView im = new ImageView(getClass().getResource("/proyecto/Sources/login.png").toString());
        
        im.setPreserveRatio(true);
        im.setFitHeight(70d);
        setGraphic(im);
        }catch(Exception e){
            e.printStackTrace();
        }


        //Configuring login buttons
        ButtonType loginButtonType = new ButtonType("Iniciar sesion", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        //Optional<Pair<String, String>> result = show();
        
        Window win = getDialogPane().getScene().getWindow();
        
        ((Stage) win).initStyle(StageStyle.TRANSPARENT);
        show();
        
        loginButton.addEventFilter(EventType.ROOT, event ->{
            if(event.getEventType().equals(ActionEvent.ACTION)){
                new CustomAlert(Alert.AlertType.INFORMATION, "Hola");
                
                ControllerWorker cw = new ControllerWorker();
                Worker w = new Worker();
                w.setId(username.getText());
                w.setPass(password.getText());
                w = cw.getSpecific(w);
                
                System.out.println(w.toString());
                
                
                
               
                    if("".equals(password.getText().trim()) || "".equals(username.getText().trim())){
                    new Alert(Alert.AlertType.WARNING, "Rellene todos los campos por favor").show();
                    event.consume();
                }
                    
                    if(!w.isNull()){
                        if(w.isAdmin()){
                            
                            AdminController adminController = new AdminController(w);

                        }else{
                            UserController uc = new UserController(w);
                        

                        }
                    }
                
                
            }

        });//loggin utton event end 

    }


}
