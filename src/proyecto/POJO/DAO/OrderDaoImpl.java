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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import proyecto.POJO.Order;
import proyecto.POJO.OrderModel;

/**
 *
 * @author Neoterux
 */
public class OrderDaoImpl implements OrderDAO {

    @Override
    public boolean register(Order order) {
        boolean registered = false;
        //(id, cedula, id_producto, cantidad, precio, total_precio, fecha, empleado_id)
        //"INSERT INTO proforma VALUES (uuid_to_bin(?, true),?,?,?,?,?,?,?)";
        String sql = "INSERT INTO proforma(id, cedula, id_producto, cantidad, precio, total_precio, fecha, empleado_id) VALUES (UNHEX(REPLACE(?, \'-\', \'\')),?,?,?,?,?,?,?)";
        
        try(Connection conn = proyecto.BD.Connector.connect(false)){
               PreparedStatement pst = conn.prepareStatement(sql);
               
               pst.setString(1, order.getId());
               pst.setString(2, order.getCedula());
               pst.setString(3, order.getId_producto());
               pst.setInt(4, order.getCantidad());
               pst.setFloat(5, order.getPrecio());
               pst.setFloat(6, order.getTotal_precio());
               pst.setTimestamp(7, new Timestamp(order.getFecha().getTime()) );
               pst.setString(8, order.getEmpleado_id());
               
               System.out.println("[Registros afectados] " + pst.executeUpdate());
            conn.close();
            registered = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return registered;
    }

    @Override
    public boolean delete(Order order) {
        boolean deleted = false;
        String sql = "DELETE FROM proforma WHERE id = cast(? as BINARY(16))";
        try(Connection con = proyecto.BD.Connector.connect(false)){
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, order.getId());
            
            System.out.println("[Registros afectados] " + pst.executeUpdate());
            con.close();
            deleted = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public boolean update(Order order) {
        boolean updated = false;
        //"UPDATE proforma SET cedula = ?, id_producto = ?, cantidad = ?, precio = ?, total_precio = ?, fecha = ?, empleado_id = ?  WHERE id = cast(? as BINARY(16))";
        String sql = "UPDATE proforma SET cedula = ?, id_producto = ?, cantidad = ?, precio = ?, total_precio = ?, fecha = ?, empleado_id = ?  WHERE id = cast(? as BINARY(16))";
        try(Connection con = proyecto.BD.Connector.connect(false)){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, order.getCedula());
            pst.setString(2, order.getId_producto());
            pst.setInt(3, order.getCantidad());
            pst.setFloat(4, order.getPrecio());
            pst.setFloat(5, order.getTotal_precio());
            pst.setTimestamp(6, new Timestamp(order.getFecha().getTime()));
            pst.setString(7, order.getEmpleado_id());
            pst.setString(8, order.getId());
            
            System.out.println("[Registros afectados] " + pst.executeUpdate());
            con.close();
            updated = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public List<Order> get() {
        ArrayList<Order> list = new ArrayList<>();
        
        try(Connection con = proyecto.BD.Connector.connect(false)){
            
            PreparedStatement stm = con.prepareStatement("SELECT * FROM proforma");
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Order o = new Order();
                o.setID(rs.getString("id_text"));
                o.setId_producto(rs.getString("id_producto"));
                o.setCedula(rs.getString("cedula"));
                o.setCantidad(rs.getInt("cantidad"));
                o.setPrecio(rs.getFloat("precio"));
                o.setTotal_precio(rs.getFloat("total_precio"));
                o.setFecha(rs.getTimestamp("fecha").getTime());
                o.setEmpleado_id(rs.getString("empleado_id"));
                System.out.println("[ORDER SQL] " + o.toString());
                list.add(o);
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<OrderModel> searchByIdOrCID(String IDorCID) {
        List<OrderModel> l = new ArrayList<>();
        String sql = "SELECT * FROM proforma WHERE id_text = ? OR cedula = ?";
        try{
            Connection con = proyecto.BD.Connector.connect(false);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, IDorCID);
            pst.setString(2, IDorCID);
           ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Order o = new Order();
                o.setID(rs.getString("id_text"));
                o.setId_producto(rs.getString("id_producto"));
                o.setCedula(rs.getString("cedula"));
                o.setCantidad(rs.getInt("cantidad"));
                o.setPrecio(rs.getFloat("precio"));
                o.setTotal_precio(rs.getFloat("total_precio"));
                o.setFecha(rs.getTimestamp("fecha").getTime());
                o.setEmpleado_id(rs.getString("empleado_id"));
                System.out.println("[ORDER SQL] " + o.toString());
                l.add(o.toModel());
            }
            
        }catch(SQLException e){}
        
        return l;
    }

    @Override
    public List<OrderModel> searchByDate(Date first, Date end) {
        List<OrderModel> l = new ArrayList<>();
        String sql = "SELECT * FROM proforma WHERE fecha BETWEEN ? AND ?";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            Connection con = proyecto.BD.Connector.connect(false);
            PreparedStatement pst = con.prepareStatement(sql);
            try {
                pst.setTimestamp(1, new Timestamp( df.parse(df.format(first)).getTime() ));
                pst.setTimestamp(2, new Timestamp( df.parse(df.format(end)).getTime() ));
            } catch (ParseException ex) {
                
            }
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Order o = new Order();
                o.setID(rs.getString("id_text"));
                o.setId_producto(rs.getString("id_producto"));
                o.setCedula(rs.getString("cedula"));
                o.setCantidad(rs.getInt("cantidad"));
                o.setPrecio(rs.getFloat("precio"));
                o.setTotal_precio(rs.getFloat("total_precio"));
                o.setFecha(rs.getTimestamp("fecha").getTime());
                o.setEmpleado_id(rs.getString("empleado_id"));
                System.out.println("[ORDER SQL] " + o.toString());
                l.add(o.toModel());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
            
        return l;
    }
    
}
