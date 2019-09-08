/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

import java.util.Date;



/**
 * POJO de pedidos
 * @author labfe
 */
public class Order {
    
    private String id;
    private String cedula;
    private String id_producto;
    private int cantidad;
    private float precio;
    private float total_precio;
    private Date fecha;
    private String empleado_id;
    private String product_name;
    
    /**
     *
     * @return
     */
    public String getId(){
        return id;
    }
    
    /**
     *
     * @param id
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the id_producto
     */
    public String getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.total_precio = precio*cantidad;
        this.cantidad = cantidad;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.total_precio = precio*cantidad;
        this.precio = precio;
    }

    /**
     * @return the total_precio
     */
    public float getTotal_precio() {
        return total_precio;
    }

    /**
     * @param total_precio the total_precio to set
     */
    public void setTotal_precio(float total_precio) {
        this.total_precio = total_precio;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(long fecha) {
        this.fecha = new Date(fecha);
    }

    /**
     * @return the empleado_id
     */
    public String getEmpleado_id() {
        return empleado_id;
    }

    /**
     * @param empleado_id the empleado_id to set
     */
    public void setEmpleado_id(String empleado_id) {
        this.empleado_id = empleado_id;
    }
    
    /**
     *
     */
    public void updateTotal(){
        this.total_precio = this.cantidad * this.precio;
    }
    
    /**
     *
     * @return
     */
    public OrderModel toModel(){
        OrderModel o = new OrderModel();
        o.setId(id);
        o.setCedula(cedula);
        o.setCantidad(cantidad);
        o.setEmpleado_id(empleado_id);
        o.setFecha(fecha.getTime());
        o.setId_producto(id_producto);
        o.setPrecio(precio);
        o.setTotal_precio(total_precio);
        return o;
    }
    
    /**
     * Transformar a proforma
     * @return el Modelo de proforma
     */
    public ProformaModel toProforma(){
        ProformaModel pf = new ProformaModel();
        
        pf.setCantidad(cantidad);
        pf.setPrecio(precio);
        pf.setProducto(product_name);
        pf.setTotal(total_precio);
        
        return pf;
    }

    /**
     * @return the product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @param product_name the product_name to set
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "[ORDER "+ id + " ]"
                + "\n[cedula]: " + cedula
                + "\n[id_producto]: " + id_producto
                + "\n[cantidad]: " + cantidad
                + "\n[precio]: " + precio
                + "\n[total_precio]: " + total_precio
                + "\n[fecha]: " + fecha.toString()
                + "\n[empleado]: " + empleado_id;
    }
    
    

    
}
