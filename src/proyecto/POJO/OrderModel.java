/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

import java.sql.Timestamp;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Modelo de Pedido
 * @author Neoterux
 */
public class OrderModel{
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty cedula = new SimpleStringProperty();
    private SimpleStringProperty id_producto = new SimpleStringProperty();
    private SimpleIntegerProperty cantidad = new SimpleIntegerProperty();
    private SimpleFloatProperty precio = new SimpleFloatProperty();
    private SimpleFloatProperty total_precio = new SimpleFloatProperty();
    private Timestamp fecha;
    private SimpleStringProperty empleado_id = new SimpleStringProperty();
    
    
    
    
    /**
     * Transformar MODEL a POJO
     * @return POJO del pedido
     */
    public Order toOrder(){
        Order o = new Order();
        o.setID(getId());
        o.setCedula(getCedula());
        o.setId_producto(getId_producto());
        o.setCantidad(getCantidad());
        o.setPrecio(getPrecio());
        o.setTotal_precio(getTotal_precio());
        o.setFecha(fecha.getTime());
        o.setEmpleado_id(getEmpleado_id());
        return o;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula.get();
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    /**
     * @return the id_producto
     */
    public String getId_producto() {
        return id_producto.get();
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(String id_producto) {
        this.id_producto.set(id_producto);
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad.get();
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio.get();
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    /**
     * @return the total_precio
     */
    public float getTotal_precio() {
        return total_precio.get();
    }

    /**
     * @param total_precio the total_precio to set
     */
    public void setTotal_precio(float total_precio) {
        this.total_precio.set(total_precio);
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(long fecha) {
        this.fecha = new Timestamp(fecha);
    }

    /**
     * @return the empleado_id
     */
    public String getEmpleado_id() {
        return empleado_id.get();
    }

    /**
     * @param empleado_id the empleado_id to set
     */
    public void setEmpleado_id(String empleado_id) {
        this.empleado_id.set(empleado_id);
    }


}
