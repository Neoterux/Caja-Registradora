package proyecto.POJO;

public class Clients {
    
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName(){
        return (nombre == null && nombre.isEmpty() && apellido == null && apellido.isEmpty())?"":this.nombre + " " + this.apellido;
    }
    
    public ClientsModel toModel(){
        ClientsModel cm = new ClientsModel();
        cm.setNombre(nombre);
        cm.setCedula(cedula);
        cm.setApellido(apellido);
        cm.setDireccion(direccion);
        cm.setEmail(email);
        cm.setTelefono(telefono);
        return cm;
    }
    
    
}
