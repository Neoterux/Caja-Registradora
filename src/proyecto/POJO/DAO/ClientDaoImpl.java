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
import org.apache.log4j.Logger;
import proyecto.BD.Connector;
import proyecto.Custom.DbgMessage;
import proyecto.POJO.Clients;

/**
 *
 * @author Neoterux
 */
public class ClientDaoImpl implements ClientsDAO{
    
    private final boolean DEBUG = false;
    private DbgMessage dbg;
    private final Logger log = Logger.getLogger("ClientDAO");
    
    private String sql;
    private Connection con;
    private PreparedStatement stm;

    
    /**
     * Registra un cliente en la base de datos
     * @param client Cliente a registrar
     * @return true->Si se realizo el ingreso
     *         false -> Si ha ocurrido un error al ingreso
     */
    @Override
    public boolean register(Clients client) {
        boolean registered = false;
        log.info("Insertando datos de Clientes");
        sql = "INSERT INTO clientes(cedula, nombre, apellido, telefono, direccion, email) values(?,upper(?),upper(?),?,upper(?),upper(?))";
        try{
            con = Connector.connect(DEBUG);
        
            stm = con.prepareStatement(sql);
            stm.setString(1, client.getCedula());
            stm.setString(2, client.getNombre());
            stm.setString(3, client.getApellido());
            stm.setString(4, client.getTelefono());
            stm.setString(5, client.getDireccion());
            stm.setString(6, client.getEmail());
            stm.executeUpdate();
            con.close();
            registered = true;
        }catch(SQLException e){
            log.error("Error al registrar datos de clientes", e);
        }
        return registered;
    }

    /**
     * Lista todos los datos de la tabla
     * @return Lista de los datos de la tabla
     */
    @Override
    public List<Clients> get() {
        log.info("Obteniendo datos de clientes");
        boolean registered = false;
        sql = "SELECT * FROM clientes";
        List<Clients> list = new ArrayList();
        try{
            con = Connector.connect(DEBUG);        
            stm = con.prepareStatement(sql);            
            ResultSet rs = stm.executeQuery();          
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
            log.error("Error al obtener los datos de los clientes", e);
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
        log.info("Actualizando datos de cliente");
       boolean updated = false;
        sql = "UPDATE clientes SET cedula=?, nombre=?, apellido=?, direccion=?, telefono=?, email=? WHERE cedula=? OR email=?";
        try {
             con = Connector.connect(DEBUG);
            
            stm = con.prepareStatement(sql);
            stm.setString(1, client.getCedula().toUpperCase());
            stm.setString(2, client.getNombre().toUpperCase());
            stm.setString(3, client.getApellido().toUpperCase());
            stm.setString(4, client.getDireccion().toUpperCase());
            stm.setString(5, client.getTelefono().toUpperCase());
            stm.setString(6, client.getEmail().toUpperCase());
            stm.setString(7, client.getCedula().toUpperCase());
            stm.setString(8, client.getEmail().toUpperCase());
            stm.executeUpdate();
            con.close();
            updated = true;

        }catch (SQLException  e){
            log.error("Error al actualizar datos de clientes", e);
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
        log.info("Borrando cliente...");
        boolean deleted = false;        
        sql = "DELETE FROM clientes WHERE cedula = ?";
        try{
            con = Connector.connect(DEBUG);
                
            stm = con.prepareStatement(sql);
            stm.setString(1, client.getCedula());
            stm.executeUpdate();
            
            deleted = true;
            con.close();
        }catch(SQLException e){
            log.error("Error al borrar cliente:", e);
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
        log.info("Buscando cliente con C.I ::" + cedula);
        sql = "SELECT * FROM clientes WHERE cedula=?";
        Clients c = new Clients();
        try{
            con = proyecto.BD.Connector.connect(DEBUG);
            stm = con.prepareStatement(sql);
            stm.setString(1, cedula);            
            ResultSet rs = stm.executeQuery();
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
            log.error("Error al buscar cliente", e);
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
        log.info("Buscando a cliente por nombre o cedula");
        List<Clients> l = new ArrayList<>();
        sql = "SELECT * FROM clientes WHERE cedula LIKE ? OR nombre LIKE ?";
        
        try{
            con = Connector.connect(DEBUG);
            stm = con.prepareStatement(sql);
            stm.setString(1, identifier + "%");
            stm.setString(2, identifier + "%");
            ResultSet rs = stm.executeQuery();
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
            log.error("Error al buscar cliente", e);
        }        
        return l;
    }

    
    
    
    
}
