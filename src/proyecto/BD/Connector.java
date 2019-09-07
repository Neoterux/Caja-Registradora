package proyecto.BD;



import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase para obtener la conexion a la base de datos
 * @author Neoterux
 */

public class Connector {
    
    
    /**
     * Método para obtener la conexion a la base de datos
     * @param useErrorDialogs [DEBUG] obtener dialogos de error para la conexion
     * @return la conexion
     */
    public static java.sql.Connection connect( boolean useErrorDialogs){
        java.sql.Connection conn = null;
        DatabaseConfigurations dbcfg = new DatabaseConfigurations(Paths.get(".").toAbsolutePath().normalize() + "/settings.json");
        try{
            conn = DriverManager.getConnection(dbcfg.getDBUri() + "?useTimezone=true&serverTimezone=UTC", dbcfg.getUser(), dbcfg.getPassword());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }


}
