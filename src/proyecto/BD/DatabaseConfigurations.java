package proyecto.BD;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import proyecto.Utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author Neoterux
 */
public class DatabaseConfigurations {
    private String ip;
    private String protocol = "3306";
    private String port;
    private String db_name;
    private String user;
    private String password;
    private String other;

    
    /**
     * Metodo constructor
     * @param ip ip de la conexion
     * @param protocol protocolo de conexion
     * @param port puerto de conexion
     * @param db_name nombre de la base de datos a conectar
     * @param user usuario para conexion a base de datos
     * @param password contraseña para conexion a base de datos
     */
    public DatabaseConfigurations( String ip, String protocol, String port, String db_name, String user, String password){
        this.ip = ip;
        this.protocol = protocol;
        this.port = port;
        this.db_name = db_name;
        this.user = user;
        this.password = password;
    }

    /**
     * Metodo Constructor
     * @param pathToJson Direccion al fichero de configuracion .json
     *        si el archivo de configuracion no existe se crea uno nuevo
     */
    public DatabaseConfigurations(String pathToJson){
        try {
            JsonObject json = new JsonParser().parse(FileUtils.readAll(pathToJson)).getAsJsonObject();

            this.ip = json.getAsJsonObject("database-config").get("ip").getAsString();

            this.protocol = json.getAsJsonObject("database-config").get("protocol").getAsString();

            this.port = json.getAsJsonObject("database-config").get("port").getAsString();

            this.db_name = json.getAsJsonObject("database-config").get("db_name").getAsString();

            this.user = json.getAsJsonObject("database-config").get("user").getAsString();

            this.password = json.getAsJsonObject("database-config").get("password").getAsString();
        }catch (IllegalStateException e){
           // new ErrorAlert(e).showAndWait();
            System.out.println(pathToJson);
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
            try(FileWriter fo = new FileWriter("settings.json")){
                fo.write(defaultConfigFile);
                fo.flush();

                fo.close();
                System.out.println(new File("settings.json").getAbsolutePath());
                System.out.println(Paths.get("settings.json"));
            }catch (IOException ignored){}
        }

    }

    /**
     * Metodo para obterner la Uri completa para la conexion a la base de datos
     * @return La Uri de conexion
     */
    public String getDBUri(){
        return "jdbc:" + protocol + "://" + ip + ":" + port + "/" + db_name;
    }

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *  Obtiene la contraseña del archivo JSON
     * @return contraseña
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return"\n[ DATABASE CONFIGURATION OPTIONS ]" +
                "\nip : " + ip +
                "\nprotocol : " + protocol +
                "\nport : " + port +
                "\ndb name : " + db_name +
                "\nuser : " + user +
                "\npassword : " + password;
    }
}
