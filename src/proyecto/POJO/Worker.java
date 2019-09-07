package proyecto.POJO;


/**
 * Clase POJO de trabajador/empleado
 * @author Neoterux
 */
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
    

    /**
     * Retorna las propiedades de la clase en forma que se pueda leer
     * @return propiedades de la clasae ordenadas
     */
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

    /**
     * 
     * @return el id/nombre de usuario del empleado
     */
    public String getId() {
        return id;
    }

    /**
     * Coloca un id al empleado
     * @param id id a colocar
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * obtiene el nombre del Trabajador
     * @return nombre del trabajador
     */
    public String getNombre() {
        return nombre;
    }

    
    /**
     * coloca el nombre al empleado 
     * @param nombre nombre a colocar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del  empleado
     * @return apellido del empleado
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Coloca el apellido al empleado
     * @param apellido apellido a colocar
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene la cedula del empleado
     * @return cedula del empleado
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Coloca la cedula al empleado
     * @param cedula cedula a colocar
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el estado civil del empleado
     * @return estado civil
     */
    public String getEstado_civil() {
        return estado_civil;
    }

    
    /**
     * Coloca el estado civil al empleado
     * @param estado_civil a colocar
     */
    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    /**
     * obtiene la direccion del empleado
     * @return la direccion del empleado
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Coloca la direccion del empleado
     * @param direccion a colocar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el email del empleado
     * @return email del empleado
     */
    public String getEmail() {
        return email;
    }

    /**
     * Coloca el email al empleado
     * @param email a colocar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del empleado
     * @return contraseña
     */
    public String getPass() {
        return pass;
    }

    /**
     * COloca la contraseña al empleado
     * @param pass contraseña a colocar
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Obtiene si el empleado es un administrador
     * @return  true -> el empleado es administrador 
     *          false-> el empleado no es administrador
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Coloca si el empleado es administrador 
     * @param admin true-> Darle privilegio de administrador
     *              false-> Darle privilegio de usuario normal
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    
    
    /**
     * Obtiene el nombre completo del trabajador
     * @return nombre completo del trabajador
     */
    public String getFullName(){
        return nombre + " " + apellido;
    }
    
    /**
     * Verifica si la clase esta vacia
     * @return true->La clase esta vacia
     *         false-> la case tiene datos
     */
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

    
    /**
     * Transforma a clase Modelo
     * @return el modelo de esta clase
     */
    public WorkerModel toWorkerModel(){
        return new WorkerModel(id, nombre, apellido, cedula, estado_civil, direccion, email, pass, isAdmin);

    }
}
