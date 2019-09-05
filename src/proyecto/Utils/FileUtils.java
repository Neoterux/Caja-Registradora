package proyecto.Utils;




import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static String readAll(String path){
        StringBuilder content = new StringBuilder();
        try {
            List<String> allfile = Files.readAllLines(Paths.get(path));
            for (String s : allfile) {
                content.append(s);
            }
        }catch (IOException e){
            System.err.println("[ERROR] :  No se pudo abrir el archivo");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");

            if (path.contains("settings.json")) {
                alert.setHeaderText("Error al cargar archivo de configuracion de la base de datos.");
                System.out.println("\n[CREANDO ARCHIVO SETTINGS PROVISIONAL]\n");
                String defaultConfigFile = "{\n" +
                        "\t\"database-config\":{\n" +
                        "\t\t\"ip\": \"localhost\",\n" +
                        "\t\t\"protocol\": \"mysql\",\n" +
                        "\t\t\"port\": \"3306\",\n" +
                        "\t\t\"db_name\": \"*\",\n" +
                        "\t\t\"user\": \"root\",\n" +
                        "\t\t\"password\": \"*\"\n" +
                        "\t}\n" +
                        "\n" +
                        "}";
                try(FileOutputStream fo = new FileOutputStream("settings.json")){
                    fo.write(defaultConfigFile.getBytes());
                    fo.flush();
                    fo.close();
                }catch (IOException ignored){}

            }else{
                alert.setHeaderText("Error al cargar archivo.");
            }
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
        return content.toString();
    }
}
