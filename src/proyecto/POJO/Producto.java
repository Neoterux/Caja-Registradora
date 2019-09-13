/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

/**
 *POJO del producto
 * @author Neoterux
 */
public class Producto {
    
    private String id;
    
    private String nombre_producto;
    
    private float precio;
    private int cantidad_disponible;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    /**
     * @return the nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto.toUpperCase();
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
        this.precio = precio;
    }
    
    /**
     *
     * @return
     */
    public String getPrecioIntText(){
        return (this.precio == 0.0)? "":String.valueOf(this.precio);
    }

    /**
     * @return the cantidad_disponible
     */
    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    /**
     * @param cantidad_disponible the cantidad_disponible to set
     */
    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }
    
    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return this.id == "" && this.nombre_producto == "" && this.precio != 0.0 && this.cantidad_disponible != 0;
    }
    
    /**
     *
     * @return
     */
    public float calcTotal(){
        
        return this.precio*this.cantidad_disponible;
    }
    
    /**
     *
     * @param cantidad
     */
    public void calcCantidad(int cantidad){
        this.cantidad_disponible -= cantidad;
    }

    @Override
    public String toString() {
        return "[Producto ="+this.id +" ]\n"
                + "[Nombre]: "+this.nombre_producto+
                "\n[Precio]: " + this.precio + 
                "\n[cantidad]: " + this.cantidad_disponible+
                "\n[TOTAL'no param']: " + calcTotal();
    }
    
    /**
     * Transforma POJO a MODEL
     * @return MODEL del producto
     */
    public ProductModel toModel(){
        ProductModel pm = new ProductModel();
            pm.setId(id);
            pm.setNombre_producto(nombre_producto);
            pm.setCantidad_disponible(cantidad_disponible);
            pm.setPrecio(precio);
            pm.setTotal(calcTotal());
        return pm;
    }
    
    
    
}
