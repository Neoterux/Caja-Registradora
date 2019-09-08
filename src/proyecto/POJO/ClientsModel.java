/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 *  Modelo de Cliente
 * @author Neoterux
 */
public class ClientsModel {
    
    /**
     * MODEL Info
     */
    private SimpleStringProperty cedula;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty telefono;
    private SimpleStringProperty direccion;
    private SimpleStringProperty email;

    /**
     *
     */
    public ClientsModel() {
        
        cedula = new SimpleStringProperty();
        nombre = new SimpleStringProperty();
        apellido = new SimpleStringProperty();
        telefono = new SimpleStringProperty();
        direccion = new SimpleStringProperty();
        email = new SimpleStringProperty();
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido.get();
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono.get();
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion.get();
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    /**
     * convertir a POJO
     * @return POJO de clientes
     */
    public Clients toClient(){
        Clients c = new Clients();
        c.setCedula(cedula.get());
        c.setNombre(nombre.get());
        c.setApellido(apellido.get());
        c.setTelefono(telefono.get());
        c.setDireccion(direccion.get());
        c.setEmail(email.get());
        return c;
    }
    
    
    
}
