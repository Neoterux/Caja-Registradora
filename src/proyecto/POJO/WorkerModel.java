package proyecto.POJO;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

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



    public WorkerModel(){}
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

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    public SimpleStringProperty cedulaProperty() {
        return cedula;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty passProperty() {
        return pass;
    }

    public SimpleBooleanProperty isAdminProperty() {
        return isAdmin;
    }

    public SimpleStringProperty estado_civilProperty(){return estado_civil;}

    public SimpleStringProperty direccionProperty(){return direccion;}

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
