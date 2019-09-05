package proyecto.POJO;

import java.io.Serializable;

public class Worker {
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String estado_civil;
    private String direccion;
    private String email;
    private String pass;
    private boolean isAdmin;
    

    @Override
    public String toString() {
        return "\n[ITEMS]:{" +
                "\n[ID] : "+ this.getId() +
                "\n[NOMBRE] : "+ this.getNombre() +
                "\n[APELLIDO] : "+ this.getApellido() +
                "\n[CEDULA] : "+ this.getCedula() +
                "\n[ESTADO CIVIL] : "+ this.getEstado_civil() +
                "\n[DIRECCION] : "+ this.getDireccion() +
                "\n[EMAIL] : "+ this.getEmail() +
                "\n[PASSWORD] : "+ this.getPass() +
                "\n[ADMIN] :"+ this.isAdmin() +
                "\n}\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    
    public String getFullName(){
        return nombre + " " + apellido;
    }
    
    public boolean isNull(){
        return ( id == null || id.isEmpty() )
                && (nombre == null || nombre.isEmpty())
                && (apellido == null || nombre.isEmpty())
                && (cedula == null || cedula.isEmpty())
               && (estado_civil == null || estado_civil.isEmpty())
                && (direccion == null || direccion.isEmpty())
                && (email == null || email.isEmpty())
                && (pass == null || pass.isEmpty());
    }

    public WorkerModel toWorkerModel(){
        return new WorkerModel(id, nombre, apellido, cedula, estado_civil, direccion, email, pass, isAdmin);

    }
}
