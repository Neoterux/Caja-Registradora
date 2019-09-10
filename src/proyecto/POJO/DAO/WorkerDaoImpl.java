package proyecto.POJO.DAO;



import proyecto.POJO.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import proyecto.BD.Connector;

/**
 * @author Neoterux
 */
public class WorkerDaoImpl implements WorkersDAO {
    private final boolean DEBUG_ENABLED = false;
    private final Logger log = Logger.getLogger("WorkerDAO");
    
    
    private Connection conn;
    private PreparedStatement stm;
    private String sql;

    /**
     * Registrar trabajador en la base de datos
     * @param worker Trabajador a registrar
     * @return true->Si se ejecuto con exito
     *         false->Si tuvo errores al ejecutarse
     */
    @Override
    public boolean reg(Worker worker) {
        log.info("Registrando Empleado");
        boolean register = false;
        sql = "INSERT INTO empleados VALUES (upper(?), upper(?), upper(?), ?, upper(?), upper(?), upper(?), ?, ?)";
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            stm.setString(1, worker.getId());
            stm.setString(2, worker.getNombre());
            stm.setString(3, worker.getApellido());
            stm.setString(4, worker.getCedula());
            stm.setString(5, worker.getEstado_civil());
            stm.setString(6, worker.getDireccion());
            stm.setString(7, worker.getEmail());
            stm.setString(8, worker.getPass());
            stm.setBoolean(9, worker.isAdmin());
            stm.executeUpdate();
            conn.close();
            register = true;
        }catch (SQLException e){
            log.error("Error al registrar empleado", e);
        }
        return register;
    }

    /**
     * Obtiene una lista de los empleados registrados en la base de datos
     * @return lista de los empleados
     */
    @Override
    public List<Worker> get() {
        log.info("Obteniendo datos de empleados");
        ResultSet rs;
        sql = "SELECT * FROM empleados ORDER BY nombre;";
        List<Worker> workerList = new ArrayList<>();

        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Worker w = new Worker();
                w.setId(rs.getString("id"));
                w.setNombre(rs.getString("nombre"));
                w.setApellido(rs.getString("apellido"));
                w.setDireccion(rs.getString("direccion"));
                w.setCedula(rs.getString("cedula"));
                w.setEstado_civil(rs.getString("estado_civil"));
                w.setEmail(rs.getString("email"));
                w.setPass(rs.getString("pass"));
                w.setAdmin(rs.getBoolean("isAdmin"));

                workerList.add(w);
            }

            stm.close();
            rs.close();
            conn.close();
        }catch (SQLException e){
           log.error("Error al obtener los datos de los empleados", e);
        }
        return workerList;
    }

    /**
     * Actualiza el registro del trabajador con nuevos datos
     * @param worker trabajador al que se modifica
     * @return true->Si se ejecuto con exito
     *         false->Si tuvo errores al ejecutarse
     */
    @Override
    public boolean update(Worker worker) {
        log.info("Actualizando trabajador");
        boolean updated = false;
        //String sql = "UPDATE  empleados SET nombre = ? WHERE id = ?";
        sql = "UPDATE  empleados SET id= upper(?), nombre = upper(?), apellido = upper(?), cedula = ?, estado_civil = upper(?), direccion = upper(?), email = upper(?), pass = ?, isAdmin = ? WHERE id = ? OR cedula = ?";
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            stm.setString(1, worker.getId());
            stm.setString(2, worker.getNombre());
            stm.setString(3, worker.getApellido());
            stm.setString(4, worker.getCedula());
            stm.setString(5, worker.getEstado_civil());
            stm.setString(6, worker.getDireccion());
            stm.setString(7, worker.getEmail());
            stm.setString(8, worker.getPass());
            stm.setBoolean(9, worker.isAdmin());
            stm.setString(10, worker.getId());
            stm.setString(11, worker.getCedula());
            System.out.println("Rows affecgted : "+  stm.executeUpdate());
            conn.close();
            updated = true;

        }catch (SQLException  e){
            log.error("Error al actualizar trabajador", e);
        }

        return updated;
    }

    /**
     * Borra a un empleado de la Base de datos
     * @param worker trabajador a eliminar
     * @return true->Si se ejecuto con exito
     *         false->Si tuvo errores al ejecutarse
     */
    @Override
    public boolean delete(Worker worker) {
        log.info("Borrando trabajador");
        ResultSet rs;
        sql ="DELETE FROM empleados WHERE id=?;";
        boolean deleted = false;
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            stm.setString(1, worker.getId());
            System.out.println("[ROWS AFFECTED]: " + stm.executeUpdate());
            conn.close();
            deleted = true;

        }catch (SQLException e){
            log.error("Error al borrar empleado", e);
        }
        return false;
    }

    /**
     * Verifica si el trabajador posee privilegios de administrador
     * @param worker trabajador a verificar
     * @return true->Si se ejecuto con exito
     *         false->Si tuvo errores al ejecutarse
     */
    @Override
    public boolean isAdmin(Worker worker) {
        log.info("Verificando permisos de admin");
        ResultSet rs;
        sql ="SELECT isAdmin FROM empleados WHERE id = ? AND pass = ?";
        boolean isAdmin = false;
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);

            stm.setString(1, worker.getId());
            stm.setString(2, worker.getPass());
            rs =stm.executeQuery();
            if (rs.next()){
                isAdmin = rs.getBoolean("isAdmin");
            }
            conn.close();

        }catch (SQLException e){
            log.error("Error al verificar los permisos de admin", e);
        }
        return isAdmin;
    }


    /**
     * Crea una lista de trabajadores de acuerdo a los nombres
     * @param name nombres a buscar
     * @return true->Si se ejecuto con exito
     *         false->Si tuvo errores al ejecutarse
     */
    public List<Worker> search(String name){
        log.info("Buscando trabajador por nombre");
        ResultSet rs;
        sql ="SELECT * FROM empleados WHERE nombre like ?";
        List<Worker> workerList = new ArrayList<>();
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            stm.setString(1, name + "%");
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Worker w = new Worker();
                w.setId(rs.getString("id"));
                w.setNombre(rs.getString("nombre"));
                w.setApellido(rs.getString("apellido"));
                w.setDireccion(rs.getString("direccion"));
                w.setCedula(rs.getString("cedula"));
                w.setEstado_civil(rs.getString("estado_civil"));
                w.setEmail(rs.getString("email"));
                w.setPass(rs.getString("pass"));
                w.setAdmin(rs.getBoolean("isAdmin"));

                workerList.add(w);
            }

            conn.close();

        }catch (SQLException e){
            log.error("Error al buscar trabajador");
        }
        return workerList;

    }

    /**
     * Obtiene datos de un trabajador especifico de acuerdo a su id y pass
     * @param worker datos del trabajador a buscar
     * @return Los datos del trabajador
     */
    @Override
    public Worker specific(Worker worker) {
       log.info("Buscando trabajador especifico");
        Worker w = new Worker();
        sql = "SELECT * FROM empleados WHERE id=? AND pass=?";
        try {
            conn = Connector.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);
            stm.setString(1, worker.getId());
            stm.setString(2, worker.getPass());
            ResultSet rs = stm.executeQuery();
            if (rs == null){
                new Alert(Alert.AlertType.INFORMATION, "usuario o contraseña incorrecta").show();
            }
            if(rs.next()){
                w.setId(rs.getString("id"));
                w.setNombre(rs.getString("nombre"));
                w.setApellido(rs.getString("apellido"));
                w.setCedula(rs.getString("cedula"));
                w.setDireccion(rs.getString("direccion"));
                 w.setEstado_civil(rs.getString("estado_civil"));
                w.setEmail(rs.getString("email"));
                w.setPass(rs.getString("pass"));
                w.setAdmin(rs.getBoolean("isAdmin"));
            }
            conn.close();
            rs.close();
        } catch (SQLException e) {
            log.error("Error al buscar un trabajador especifico", e);
        }
        
        return w;
    }
}
