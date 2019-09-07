/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import proyecto.Custom.CrudType;
import proyecto.Custom.CustomAlert;
import proyecto.Custom.DbgMessage;
import proyecto.Custom.ExceptionType;
import proyecto.POJO.Clients;

/**
 *
 * @author Neoterux
 */
public class ClientDaoImpl implements ClientsDAO{
    
    private final boolean DEBUG = false;
    private DbgMessage dbg;

    
    /**
     * Registra un cliente en la base de datos
     * @param client Cliente a registrar
     * @return true->Si se realizo el ingreso
     *         false -> Si ha ocurrido un error al ingreso
     */
    @Override
    public boolean register(Clients client) {
        boolean registered = false;
        String sql = "INSERT IGNORE INTO clientes values(?,?,?,?,?,?)";
        try(Connection con = proyecto.BD.Connector.connect(DEBUG);){
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, client.getCedula());
            pstm.setString(2, client.getNombre());
            pstm.setString(3, client.getApellido());
            pstm.setString(4, client.getTelefono());
            pstm.setString(5, client.getDireccion());
            pstm.setString(6, client.getEmail());
            System.out.println("[class= 'ClientDaoImpl' INSERT] rows Affected: " + pstm.executeUpdate() );
            
            con.close();
            registered = true;
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
        }
        return registered;
    }

    /**
     * Lista todos los datos de la tabla
     * @return Lista de los datos de la tabla
     */
    @Override
    public List<Clients> get() {
        boolean registered = false;
        String sql = "SELECT * FROM clientes";
        List<Clients> list = new ArrayList();
        try(Connection con = proyecto.BD.Connector.connect(DEBUG)){           
            PreparedStatement pstm = con.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();          
            while(rs.next()){
                Clients c = new Clients();
                c.setCedula(rs.getString("cedula"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));                              
                list.add(c);
            }
            registered = true;
            con.close();
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();    
        }
        return list;
    }

    
    /**
     * Actualiza a un respectivo clientes con nuevos datos
     * @param client clciente a actualizara
     * @return  true-> si se realizo con exito la operacion
     *          false-> si no se realizo con exito
     */
    @Override
    public boolean update(Clients client) {
       boolean updated = false;
        String sql = "UPDATE clientes SET cedula=?, nombre=?, apellido=?, direccion=?, telefono=?, email=? WHERE cedula=? OR email=?";
        try (Connection conn = proyecto.BD.Connector.connect(DEBUG)){
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, client.getCedula());
            stm.setString(2, client.getNombre());
            stm.setString(3, client.getApellido());
            stm.setString(4, client.getDireccion());
            stm.setString(5, client.getTelefono());
            stm.setString(6, client.getEmail());
            stm.setString(7, client.getCedula());
            stm.setString(8, client.getEmail());
            System.out.println("[ROWS AFFECTED]: " + stm.executeUpdate());
            conn.close();
            updated = true;

        }catch (SQLException  e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
        }
        return updated;
    }

    /**
     * Borra el registro de un cliente
     * @param client cliente a ser borrado
     * @return true-> si se realizo con exito
     *         false-> si no se realizo con exito
     */
    @Override
    public boolean delete(Clients client) {
        boolean deleted = false;        
        String sql = "DELETE FROM clientes WHERE cedula = ?";
        try(Connection con = proyecto.BD.Connector.connect(DEBUG)){
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, client.getCedula());
            dbg = new DbgMessage(this, CrudType.DELETE, stm.executeUpdate());
            dbg.showCrudDbg();
            deleted = true;
            con.close();
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
            
            CustomAlert al = new CustomAlert(Alert.AlertType.INFORMATION, "Cliente ya existente", ButtonType.APPLY);
           al.showAndWait();
        }        
        return deleted;
    }

    /**
     * Obtiene un cliente especifico por su cedula
     * @param cedula cedula del cliente a buscar
     * @return Cliente buscado
     */
    @Override
    public Clients getFromCedula(String cedula) {
        String sql = "SELECT * FROM clientes WHERE cedula=?";
        Clients c = new Clients();
        try(Connection con = proyecto.BD.Connector.connect(DEBUG)){           
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cedula);            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){                
                c.setCedula(rs.getString("cedula"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));                              
            }
            con.close();
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();            
        }
        return c;
    }

    /**
     * Obtiene una lista de clientes buscados
     * @param identifier identificador que buscara a los clientes
     * @return lista de los clientes buscados
     */
    @Override
    public List<Clients> search(String identifier) {
        List<Clients> l = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE cedula LIKE ? OR nombre LIKE ?";
        
        try(Connection con = proyecto.BD.Connector.connect(DEBUG)){
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, identifier + "%");
            pstm.setString(2, identifier + "%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Clients c = new Clients();
                c.setCedula(rs.getString("cedula"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                l.add(c);
            }
            con.close();
        }catch(SQLException e){            
            e.printStackTrace();
        }        
        return l;
    }

    
    
    
    
}
