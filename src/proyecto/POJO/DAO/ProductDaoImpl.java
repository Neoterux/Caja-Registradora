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
import proyecto.Custom.CustomAlert;
import proyecto.Custom.DbgMessage;
import proyecto.Custom.ExceptionType;
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
    
    
    
    /**
     * Registra un nuevo producto en la base de datos
     * @param product producto que va a ser insertado
     * @return true-> si se ejecuto con exito
     *         false-> si se ejecuto con errores
     */
    @Override
    public boolean register(Producto product) {
        boolean registered = false;
        sql = "INSERT INTO bodega values(?,?,?,?)";
        
        try{
            con = proyecto.BD.Connector.connect(false);
            stm = con.prepareStatement(sql);
            stm.setString(1, product.getId());
            stm.setString(2, product.getNombre_producto());
            stm.setFloat(3, product.getPrecio());
            stm.setInt(4, product.getCantidad_disponible());
            System.out.println("[INSERT Class='ProductDaoImple'] rows Affected: " + stm.executeUpdate());            
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
            CustomAlert al = new CustomAlert(Alert.AlertType.INFORMATION, "Prodcuto ya existente", ButtonType.APPLY);
           al.showAndWait();
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
            con = proyecto.BD.Connector.connect(false);
            stm = con.prepareStatement("DELETE FROM bodega WHERE id = ?");            
            stm.setString(1, product.getId());            
            System.out.println("[ROWS AFFECTED] : " + stm.executeUpdate());            
            con.close();
            deleted = true;            
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
        }        
        return deleted;
    }

    
    
    @Override
    public boolean update(Producto product) {
        boolean updated = false;
        try{
            con = proyecto.BD.Connector.connect(false);            
            stm = con.prepareStatement("UPDATE bodega SET nombre_producto=?, precio=?, cantidad_disponible=? WHERE id = ? ");            
            stm.setString(1, product.getNombre_producto());
            stm.setFloat(2, product.getPrecio());
            stm.setInt(3, product.getCantidad_disponible());
            stm.setString(4, product.getId());
            System.out.println("[UPDATE class='ProductDaoImpl']: " + stm.executeUpdate());
            con.close();
            updated = true;
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();            
        }
        return updated;
    }

    @Override
    public List<Producto> get() {
        ArrayList<Producto> lst = new ArrayList<>();
        
        try{
            con = proyecto.BD.Connector.connect(false);
            stm = con.prepareStatement("SELECT * FROM bodega");
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Producto pd = new Producto();
                pd.setId(rs.getString("id"));
                pd.setNombre_producto(rs.getString("nombre_producto"));
                pd.setPrecio(rs.getFloat("precio"));
                pd.setCantidad_disponible(rs.getInt("cantidad_disponible"));
                lst.add(pd);
                System.out.println(pd.toString());
            }
           con.close();
            
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
        }
        
        return lst;
    }

    @Override
    public Producto getFromId(String id) {
        sql = "SELECT * FROM BODEGA WHERE id=?";
        Producto p = new Producto();
        try{
            con = connect();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                p.setId(rs.getString("id"));
                p.setCantidad_disponible(rs.getInt("cantidad_disponible"));
                p.setPrecio(rs.getFloat("precio"));
                p.setNombre_producto(rs.getString("nombre_producto"));
            }
            con.close();
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            dbg.showExceptionDbg();
        }
        
        
        return p;
    }
    
    
    private Connection connect(){
        return proyecto.BD.Connector.connect(false);
    }

    @Override
    public List<ProductModel> searchByIDorName(String IDorName) {
        List<ProductModel> l = new ArrayList<>();
        sql = "SELECT * FROM bodega WHERE id like ? OR nombre_producto like ?";
        try{
            con = proyecto.BD.Connector.connect(false);
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
            con.close();
            
        }catch(SQLException e){
            
        }
        
        return l;
    }
    
    
    
}
