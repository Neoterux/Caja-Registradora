/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.DAO;

import com.mysql.cj.x.protobuf.MysqlxSql;
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
import org.apache.log4j.Logger;
import proyecto.BD.Connector;
import proyecto.POJO.Order;
import proyecto.POJO.OrderModel;

/**
 *
 * @author Neoterux
 */
public class OrderDaoImpl implements OrderDAO {

    private final Logger log = Logger.getLogger(getClass().getName());
    
    private Connection conn;
    private String sql;
    private PreparedStatement pst;

    /**
     *
     * @param order
     * @return
     */
    @Override
    public boolean register(Order order) {
        boolean registered = false;
        log.debug("Insert para factura: " + order.getId());
        sql = "INSERT INTO proforma(id, cedula, id_producto, cantidad, precio, total_precio, fecha, empleado_id) VALUES (UNHEX(REPLACE(?, \'-\', \'\')),?,?,?,?,?,?,?)";
        
        try{
               conn = Connector.connect(false);
               pst = conn.prepareStatement(sql);
               
               pst.setString(1, order.getId());
               pst.setString(2, order.getCedula());
               pst.setString(3, order.getId_producto());
               pst.setInt(4, order.getCantidad());
               pst.setFloat(5, order.getPrecio());
               pst.setFloat(6, order.getTotal_precio());
               pst.setTimestamp(7, new Timestamp(order.getFecha().getTime()) );
               pst.setString(8, order.getEmpleado_id());
               
               log.info("Ejecutando Insert en tabla de Ordenes");
               pst.executeUpdate();
               conn.close();
            registered = true;
        }catch (SQLException e){
            log.error("SQLException producida :", e);
        }
        return registered;
    }

    /**
     *
     * @param order
     * @return
     */
    @Override
    public boolean delete(Order order) {
        boolean deleted = false;
        sql = "DELETE FROM proforma WHERE id_text = ?";
        try{
            conn = Connector.connect(false);
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, order.getId());
            log.info("Ejecutando Delete para orden: " + order.getId());
            pst.executeUpdate();
            conn.close();
            deleted = true;
        }catch(SQLException e){
            log.error("SQLException producida", e);
        }
        return deleted;
    }

    /**
     *
     * @param order
     * @return
     */
    @Override
    public boolean update(Order order) {
        boolean updated = false;
        sql = "UPDATE proforma SET cedula = ?, id_producto = ?, cantidad = ?, precio = ?, total_precio = ?, fecha = ?, empleado_id = ?  WHERE id_text = ?";
        try{
            conn = Connector.connect(false);
            pst = conn.prepareStatement(sql);
            pst.setString(1, order.getCedula());
            pst.setString(2, order.getId_producto());
            pst.setInt(3, order.getCantidad());
            pst.setFloat(4, order.getPrecio());
            pst.setFloat(5, order.getTotal_precio());
            pst.setTimestamp(6, new Timestamp(order.getFecha().getTime()));
            pst.setString(7, order.getEmpleado_id());
            pst.setString(8, order.getId());
            
            log.info("Ejecutando Update para factura: " + order.getId());
            pst.executeUpdate();
            conn.close();
            updated = true;
        }catch(SQLException e){
            log.error("SQLException producida", e);
        }
        return updated;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Order> get() {
        log.info("Obteniendo todas las facturas");
        ArrayList<Order> list = new ArrayList<>();
        
        try{
            conn = Connector.connect(false);
            pst = conn.prepareStatement("SELECT * FROM proforma");
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
                list.add(o);
            }
            conn.close();
        }catch(SQLException e){
            log.error("SQLException producida", e);
        }
        
        return list;
    }

    /**
     *
     * @param IDorCID
     * @return
     */
    @Override
    public List<OrderModel> searchByIdOrCID(String IDorCID) {
        log.info("Obteniendo facturas por ID o Cedula");
        List<OrderModel> l = new ArrayList<>();
        sql = "SELECT * FROM proforma WHERE id_text = ? OR cedula = ?";
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
            
        }catch(SQLException e){
            log.error("SQLException producida", e);
        }
        
        return l;
    }

    /**
     *
     * @param first
     * @param end
     * @return
     */
    @Override
    public List<OrderModel> searchByDate(Date first, Date end) {
        log.info("Obteniendo facturas por fecha");
        List<OrderModel> l = new ArrayList<>();
        sql = "SELECT * FROM proforma WHERE fecha BETWEEN ? AND ?";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            conn = Connector.connect(false);
            pst = conn.prepareStatement(sql);
            try {
                pst.setTimestamp(1, new Timestamp( df.parse(df.format(first)).getTime() ));
                pst.setTimestamp(2, new Timestamp( df.parse(df.format(end)).getTime() ));
            } catch (ParseException ex) {
               log.error("ParseException ocurrida", ex);
            }
            log.debug("busqueda por fecha sql: " + pst);
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
                l.add(o.toModel());
            }
        }catch(SQLException e){
            log.error("SQLException ocurrida", e);
        }
            
        return l;
    }
    
}
