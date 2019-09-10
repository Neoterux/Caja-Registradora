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
import proyecto.POJO.ProductModel;
import proyecto.POJO.Producto;

/**
 *
 * @author Neoterux
 */

public class ProductDaoImpl implements ProductDAO{
    
    private String sql;
    private Connection con;
    private PreparedStatement stm;
    private DbgMessage dbg;
    private final Logger logger = Logger.getLogger("ProductDAO");
    
    
    /**
     * Registra un nuevo producto en la base de datos
     * @param product producto que va a ser insertado
     * @return true-> si se ejecuto con exito
     *         false-> si se ejecuto con errores
     */
    @Override
    public boolean register(Producto product) {
        boolean registered = false;
        sql = "INSERT INTO bodega values(upper(?), upper(?), ?, ?)";
        
        try{
            con = Connector.connect(false);
            stm = con.prepareStatement(sql);
            stm.setString(1, product.getId());
            stm.setString(2, product.getNombre_producto());
            stm.setFloat(3, product.getPrecio());
            stm.setInt(4, product.getCantidad_disponible());
            stm.executeUpdate();
            logger.info("Ejecutando Insert en tabla Producto");          
        }catch(SQLException e){
            logger.error("Error al Ejecutar Insert en tabla Producto:", e);
            
        }
        return registered;
    }
    
    
/**
 * Borra un registro de la base de datos
 * @param product producto que va a ser eliminado
 * @return true-> si se ejecuto con exito
 *         false-> si se ejecuto con errores
 */
    @Override
    public boolean delete(Producto product) {
        boolean deleted = false;
        try{
            con = Connector.connect(false);
            stm = con.prepareStatement("DELETE FROM bodega WHERE id = ?");            
            stm.setString(1, product.getId());
            
            stm.executeUpdate();
            logger.info( "Ejecutando Delete en tabla Producto rows_affected" );    
            con.close();
            deleted = true;            
        }catch(SQLException e){
            logger.info("Error al Ejecutar Delete en tabla Producto ", e);
        }        
        return deleted;
    }

    /**
     *
     * @param product
     * @return
     */
    @Override
    public boolean update(Producto product) {
        boolean updated = false;
        try{
            con = Connector.connect(false);            
            stm = con.prepareStatement("UPDATE bodega SET nombre_producto=?, precio=?, cantidad_disponible=? WHERE id = ? ");            
            stm.setString(1, product.getNombre_producto());
            stm.setFloat(2, product.getPrecio());
            stm.setInt(3, product.getCantidad_disponible());
            stm.setString(4, product.getId());
            System.out.println("[UPDATE class='ProductDaoImpl']: " + stm.executeUpdate());
            stm.executeUpdate();
            logger.info("Ejecutando Update en tabla Producto");    
            con.close();
            updated = true;
        }catch(SQLException e){
            logger.error("Error al Ejecutar Update en tabla Producto ", e);            
        }
        return updated;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Producto> get() {
        ArrayList<Producto> lst = new ArrayList<>();
        
        try{
            con = Connector.connect(false);
            stm = con.prepareStatement("SELECT * FROM bodega");
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Producto pd = new Producto();
                pd.setId(rs.getString("id"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio(rs.getFloat("precio"));
                pd.setCantidad_disponible(rs.getInt("cantidad_disponible"));
                lst.add(pd);
            }
            logger.info("Ejecutando GetAll en tabla Producto");    
           con.close();
            
        }catch(SQLException e){
            logger.error("Error al Ejecutar GetAll en tabla Producto:" , e);
        }
        
        return lst;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Producto getFromId(String id) {
        sql = "SELECT * FROM bodega WHERE id=?";
        Producto p = new Producto();
        try{
            con = Connector.connect(false);
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                p.setId(rs.getString("id"));
                p.setCantidad_disponible(rs.getInt("cantidad_disponible"));
                p.setPrecio(rs.getFloat("precio"));
                p.setNombre_producto(rs.getString("nombre_producto"));
            }
            logger.info("Ejecutando GetFromID en tabla Producto");    
            con.close();
        }catch(SQLException e){
            logger.error("Error al Ejecutar GetFromID en tabla Producto:", e);
        }
        return p;
    }
    
    /**
     *
     * @param IDorName
     * @return
     */
    @Override
    public List<ProductModel> searchByIDorName(String IDorName) {
        List<ProductModel> l = new ArrayList<>();
        sql = "SELECT * FROM bodega WHERE id like ? OR nombre_producto like ?";
        try{
            con = Connector.connect(false);
            stm = con.prepareStatement(sql);
            stm.setString(1, IDorName + "%");
            stm.setString(2, IDorName + "%");
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                ProductModel pm = new ProductModel();
                pm.setId(rs.getString("id"));
                pm.setNombre_producto("nombre_produco");
                pm.setPrecio(rs.getFloat("precio"));
                pm.setCantidad_disponible(rs.getInt("cantidad_disponible"));
                l.add(pm);
            }
            logger.info("Ejecutando SearchByIDorName en tabla Producto ");    
            con.close();
            
        }catch(SQLException e){
            logger.error("Error al Ejecutar SearchByIDorName en tabla Producto: ",e);
        }
        
        return l;
    }
    
    
    
}
