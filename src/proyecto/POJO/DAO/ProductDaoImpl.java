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
import proyecto.POJO.Producto;

/**
 *
 * @author user
 */
public class ProductDaoImpl implements ProductDAO{
    
    private String sql;
    private Connection con;
    private PreparedStatement stm;
    private DbgMessage dbg;

    
    
    /**
     * Register a new Product to the database
     * @param product product to be inserted
     * @return true if the product was inserted correctly
     */
    @Override
    public boolean register(Producto product) {
        boolean registered = false;
        sql = "INSERT INTO bodega values(?,?,?,?)";
        
        try{
            con = proyecto.BD.Connection.connect(false);
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, product.getId());
            stm.setString(2, product.getNombre_producto());
            stm.setFloat(3, product.getPrecio());
            stm.setInt(4, product.getCantidad_disponible());
            System.out.println("[INSERT Class='ProductDaoImple'] rows Affected: " + stm.executeUpdate());
            //dbg = new DbgMessage(this, CrudType.INSERT, );
            //dbg.showCrudDbg();
            
        }catch(SQLException e){
            dbg = new DbgMessage(ExceptionType.SQLEXCEPTION, this, e.getMessage());
            
            dbg.showExceptionDbg();
            
            CustomAlert al = new CustomAlert(Alert.AlertType.INFORMATION, "Prodcuto ya existente", ButtonType.APPLY);
           al.showAndWait();
        }
        
        
        return registered;
    }
    
    
/**
 * Delete a product from the database
 * @param product producto to be deleted
 * @return true if the product was deleted correctly
 */
    @Override
    public boolean delete(Producto product) {
        boolean deleted = false;
        try{
            con = proyecto.BD.Connection.connect(false);
            stm = con.prepareStatement("DELETE FROM bodega WHERE id = ?");
            
            stm.setString(1, product.getId());
            
            dbg = new DbgMessage(this, CrudType.DELETE, stm.executeUpdate());
            dbg.showCrudDbg();
            
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
            con = proyecto.BD.Connection.connect(false);
            
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
            con = proyecto.BD.Connection.connect(false);
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
        return proyecto.BD.Connection.connect(false);
    }
    
    
    
}
