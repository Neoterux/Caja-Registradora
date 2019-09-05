package proyecto.POJO.DAO;



import proyecto.POJO.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * TODO: Arreglar clase de Workers y hacerlo un POJO comun, adicionalmente hacer un WorkerModel a partir del POJO
 */
public class WorkerDaoImpl implements WorkersDAO {

    /**
     *
     * @param workers
     * @return
     */
    private final boolean DEBUG_ENABLED = false;

    @Override
    public boolean reg(Worker worker) {
        boolean register = false;
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "INSERT IGNORE INTO empleados VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
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
            
            System.out.println(stm.toString());
            stm.executeUpdate();
            register = true;
            stm.close();
            conn.close();
        }catch (SQLException e){
            System.out.println("\n[ERROR CLASS = 'WORKERDAOIMPL' ]: Error al Ejecutar reg {\n" + e.getMessage() +"\n}\n");
        }
        return register;
    }

    @Override
    public List<Worker> get() {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM empleados ORDER BY nombre;";
        List<Worker> workerList = new ArrayList<>();

        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
            stm = conn.createStatement();
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
            System.out.println("\n[ERROR CLASS = 'WORKERDAOIMPL' ]: Error al Ejecutar get {\n" + e.getSQLState() +"\n}\n");
        }
        return workerList;
    }

    @Override
    public boolean update(Worker worker) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean updated = false;
        //String sql = "UPDATE  empleados SET nombre = ? WHERE id = ?";
        String sql = "UPDATE  empleados SET id=?, nombre = ?, apellido = ?, cedula = ?, estado_civil = ?, direccion = ?, email = ?, pass = ?, isAdmin = ? WHERE id = ? OR cedula = ?";
        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
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
            
            System.out.println(stm.toString());
            System.out.println("Rows affecgted : "+  stm.executeUpdate());
            
            conn.close();
            updated = true;

        }catch (SQLException  e){
            System.out.println("\n[ERROR("+e.getSQLState()+") CLASS = 'WORKERDAOIMPL' ]: Error al Actualizar {\n" + e.getMessage() +"\n}\n");
        }

        return updated;
    }

    @Override
    public boolean delete(Worker worker) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql ="DELETE FROM empleados WHERE id=?;";
        boolean deleted = false;
        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);

            stm.setString(1, worker.getId());

            stm.execute(sql);
            stm.close();
            deleted = true;

        }catch (SQLException e){
            System.out.println("\n[ERROR CLASS = 'WORKERDAOIMPL' ]: Error al Eliminar {\n" + e.getSQLState() +"\n}\n");
        }
        return false;
    }

    @Override
    public boolean isAdmin(Worker worker) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql ="SELECT isAdmin FROM empleados WHERE id = ? AND pass = ?";
        boolean isAdmin = false;
        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
            stm = conn.prepareStatement(sql);

            stm.setString(1, worker.getId());
            stm.setString(2, worker.getPass());
            rs =stm.executeQuery();
            if (rs.next()){
                isAdmin = rs.getBoolean("isAdmin");
            }
            stm.close();

        }catch (SQLException e){
            System.out.println("\n[ERROR CLASS = 'WORKERDAOIMPL' ]: Error al chequear permisos {\n" + e.getSQLState() +"\n}\n");
        }
        return isAdmin;
    }


    public List<Worker> search(String name){
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql ="SELECT * FROM empleados WHERE nombre like'"+name+"%'";
        List<Worker> workerList = new ArrayList<>();
        try {
            conn = proyecto.BD.Connection.connect(DEBUG_ENABLED);
            stm = conn.createStatement();
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

        }catch (SQLException e){
            System.out.println("\n[ERROR("+e.getSQLState()+") CLASS = 'WORKERDAOIMPL' ]: Error al Obtener Busqueda {\n" + e.getMessage() +"\n}\n");
        }
        return workerList;

    }

    @Override
    public Worker specific(Worker worker) {
        Connection con;
        PreparedStatement pstm;
        Worker w = new Worker();
        final String sql = "SELECT * FROM empleados WHERE id=? AND pass=?";
        try {
            con = proyecto.BD.Connection.connect(DEBUG_ENABLED);
            pstm = con.prepareStatement(sql);
            pstm.setString(1, worker.getId());
            pstm.setString(2, worker.getPass());
            ResultSet rs = pstm.executeQuery();
            if (rs == null){
                new Alert(Alert.AlertType.INFORMATION, "usuario o contraseña incorrecta").show();
            }
            rs.next();
            w.setId(rs.getString("id"));
            w.setNombre(rs.getString("nombre"));
            w.setApellido(rs.getString("apellido"));
            w.setCedula(rs.getString("cedula"));
            w.setDireccion(rs.getString("direccion"));
            w.setEstado_civil(rs.getString("estado_civil"));
            w.setEmail(rs.getString("email"));
            w.setPass(rs.getString("pass"));
            w.setAdmin(rs.getBoolean("isAdmin"));
            con.close();
            rs.close();
        } catch (SQLException e) {
        }
        
        return w;
    }
}
