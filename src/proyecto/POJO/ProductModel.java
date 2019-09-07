/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;

/**
 *Modelo de producto
 * @author Neoterux
 */
public class ProductModel {
    private SimpleStringProperty id = new SimpleStringProperty();
    
    private SimpleStringProperty nombre_producto = new SimpleStringProperty();
    
    private SimpleFloatProperty precio = new SimpleFloatProperty();
    private SimpleIntegerProperty cantidad_disponible = new SimpleIntegerProperty();
    
    private SimpleFloatProperty total = new SimpleFloatProperty();

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
     * @return the nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto.get();
    }

    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto.set(nombre_producto);
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
     * @return the cantidad_disponible
     */
    public int getCantidad_disponible() {
        return cantidad_disponible.get();
    }

    /**
     * @param cantidad_disponible the cantidad_disponible to set
     */
    public void setCantidad_disponible(int cantidad_disponible) {
        
        this.cantidad_disponible.set(cantidad_disponible);
        
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
    
    
    /**
     * 
     * @param tableView table that would be scanned
     * @return  return the index that the item exists
     *          -1 -> when the item doesnt exists
     *          #-> the index where the object is 
     */
    public int existsInTable(TableView<ProductModel> tableView){
        int exists  = -1;
        List<ProductModel> l = tableView.getItems();
        for (ProductModel p : l){
            if (this.equals(p)){
                exists = l.indexOf(p);
            }
        }
        
        return exists;
    }
    
    /**
     * Transforma MODEL a POJO
     * @return POJO del producto
     */
    public Producto toProduct(){
        Producto p = new Producto();
        
        p.setId(getId());
        p.setNombre_producto(getNombre_producto());
        p.setPrecio(getPrecio());
        p.setCantidad_disponible(getCantidad_disponible());
        return p;
    }
    
    @Override
    public String toString() {
        return "[Producto ="+this.id.get() +" ]\n"
                + "[Nombre]: "+this.nombre_producto.get()+
                "\n[Precio]: " + this.precio.get() + 
                "\n[cantidad]: " + this.cantidad_disponible.get()+
                "\n[TOTAL'no param']: " + total.get();
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((ProductModel) obj).id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nombre_producto);
        hash = 41 * hash + Objects.hashCode(this.precio);
        hash = 41 * hash + Objects.hashCode(this.cantidad_disponible);
        hash = 41 * hash + Objects.hashCode(this.total);
        return hash;
    }
    
    
    
}
