package proyecto;




import javafx.stage.Stage;
import proyecto.Custom.LogInDialog;

public class Main extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("Views/LogIn.fxml"));
        primaryStage.setTitle("Iniciar sesion");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
        LogInDialog logInDialog = new LogInDialog();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
