package proyecto.BD;



import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

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
