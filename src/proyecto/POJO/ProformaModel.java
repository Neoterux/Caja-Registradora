/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author labfe
 */
public class ProformaModel {
    
    private SimpleIntegerProperty cantidad;
    private SimpleStringProperty producto;
    private SimpleFloatProperty precio;
    private SimpleFloatProperty total;

    public ProformaModel() {
        cantidad = new SimpleIntegerProperty();
        producto = new SimpleStringProperty();
        precio = new SimpleFloatProperty();
        total = new SimpleFloatProperty();
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
     * @return the producto
     */
    public String getProducto() {
        return producto.get();
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto.set(producto);
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
     * @return the total
     */
    public float getTotal() {
        return total.get();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total.set(total);
    }
    
    
}
