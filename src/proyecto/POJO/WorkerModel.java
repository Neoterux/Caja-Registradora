package proyecto.POJO;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;


/**
 * Clase modelo del trabajador
 * @author Neoterux
 */
public class WorkerModel  implements Serializable {

    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleStringProperty apellido = new SimpleStringProperty();
    private SimpleStringProperty cedula = new SimpleStringProperty();
    private SimpleStringProperty estado_civil = new SimpleStringProperty();
    private SimpleStringProperty direccion = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty pass = new SimpleStringProperty();
    private SimpleBooleanProperty isAdmin = new SimpleBooleanProperty();


/**
 * Constructor de la clase
 */
    public WorkerModel(){}

    /**
     *
     * @param id
     * @param nombre
     * @param apellido
     * @param cedula
     * @param estado_civil
     * @param direccion
     * @param email
     * @param pass
     * @param isAdmin
     */
    public WorkerModel(String id, String nombre, String apellido, String cedula, String estado_civil, String direccion, String email, String pass, boolean isAdmin){
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellido.set(apellido);
        this.cedula.set(cedula);
        this.estado_civil.set(estado_civil);
        this.direccion.set(direccion);
        this.email.set(email);
        this.pass.set(pass);
        this.isAdmin.set(isAdmin);
    }

    
    /**
     * Describe los elementos de la clase
     * @return string que describe la clase
     */
    @Override
    public String toString() {
        return "\n[ITEMS]:{" +
                "\n[ID] : "+this.id.get() +
                "\n[NOMBRE] : "+this.nombre.get() +
                "\n[APELLIDO] : "+this.apellido.get() +
                "\n[CEDULA] : "+this.cedula.get() +
                "\n[ESTADO CIVIL] : "+this.estado_civil.get() +
                "\n[DIRECCION] : "+this.direccion.get() +
                "\n[EMAIL] : "+this.email.get() +
                "\n[PASSWORD] : "+this.pass.get() +
                "\n[ADMIN] :"+this.isAdmin.get() +
                "\n}\n";
    }

    /**
     * Obtiene el id
     * @return el id
     */
    public SimpleStringProperty idProperty() {
        return id;
    }

    /**
     * Obtiene el nomnbre
     * @return el nombre
     */
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    /**
     * Obtiene el apellido
     * @return el apellido
     */
    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    /**
     * Obtiene la cedula 
     * @return la cedula
     */
    public SimpleStringProperty cedulaProperty() {
        return cedula;
    }

    /**
     * Obtiene le email del empleado
     * @return el email
     */
    public SimpleStringProperty emailProperty() {
        return email;
    }

    /**
     * Obtiene la contraseña
     * @return la contraseña
     */
    public SimpleStringProperty passProperty() {
        return pass;
    }

    /**
     * Obtiene los privilegios de Admin
     * @return si es admin o no
     */
    public SimpleBooleanProperty isAdminProperty() {
        return isAdmin;
    }

    /**
     * Obtiene 
     * @return 
     */
    public SimpleStringProperty estado_civilProperty(){return estado_civil;}

    /**
     *
     * @return
     */
    public SimpleStringProperty direccionProperty(){return direccion;}

    /**
     *
     * @return
     */
    public Worker toWorker(){
        Worker worker = new Worker();
        worker.setId(this.id.get());
        worker.setNombre(this.nombre.get());
        worker.setApellido(this.apellido.get());
        worker.setCedula(this.cedula.get());
        worker.setEstado_civil(this.estado_civil.get());
        worker.setDireccion(this.direccion.get());
        worker.setEmail(this.email.get());
        worker.setPass(this.pass.get());
        worker.setAdmin(this.isAdmin.get());
        return worker;
    }
}
