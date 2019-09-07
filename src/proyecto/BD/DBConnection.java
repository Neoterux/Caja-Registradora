package proyecto.BD;


import javafx.scene.control.Alert;
import java.nio.file.Paths;
import java.sql.*;

public class DBConnection {

    //Database connection paths
    /**
     * Objetos necesarios para la conexion a la base de datos
     */
    private String url ;
    private String user;
    private String password;

    //Database objects
    /**
     * Objetos necesarios para obtene
     */
    private DatabaseConfigurations dbconfig;
    private java.sql.Connection conn;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection() {
        dbconfig = new DatabaseConfigurations(Paths.get(".").toAbsolutePath().normalize() + "/settings.json");
        this.url = dbconfig.getDBUri();
        this.user = dbconfig.getUser();
        this.password = dbconfig.getPassword();

        try{
            System.out.println(Class.forName("com.mysql.jdbc.Driver"));
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("\n[INFO]: Conexion establecida correctamente\n");
            openStatement();
        }catch(SQLException | ClassNotFoundException e){

            System.out.println("\n[ERROR] : No se pudo establecer conexion con la base de datos\n");
            
        }

    }

    /**
     * Crear conexion a la base de datos
     */
    public void connect(){
        try {

            conn = DriverManager.getConnection(url, user, password);
            openStatement();
            System.out.println("[Info]: Ingreso Correctamente a la base de datos");

        }catch(SQLException  e){
            System.out.println("[Error]: Error al conectar la base de datos: " + e);
        }
    }

    /**
     * Método que permite ejecutar querys sql
     * @param SQLQuery Sentencia SQL a ejecutar
     * @return se obtiene el resultset de la ejecucion del query
     */
    public ResultSet execute(String SQLQuery){
        System.out.println("\n[DEBUG]: EJECUTANDO EXECUTE");
        try{
            //closeStatement();
            //openStatement();

            reOpenStatement();
            resultSet = statement.executeQuery(SQLQuery);
            connect();
            closeConnection();

            System.out.println("[Info]: Query Ejecutado Exitosamente");
        }catch(SQLException e){
            System.out.println("[Error]: No se pudo ejecutar el SQL Query >> " + e.getMessage());
        }
        return resultSet;
    }

    /**
     * This method is for testing or intended message to display
     *
     * @param SQLQuery SQL query to execute
     * @param Message Message will be displayed in alert
     * @param type Type alert to display
     */

    public ResultSet customExecute(String SQLQuery, String Message, Alert.AlertType type){
        System.out.println("\n[DEBUG]: EJECUTANDO EXECUTE");
        try{
            //closeStatement();
            //openStatement();

            reOpenStatement();
            resultSet = statement.executeQuery(SQLQuery);
            connect();
            closeConnection();

            System.out.println("[Info]: Query Ejecutado Exitosamente");
        }catch(SQLException e){
            System.out.println("[Error]: No se pudo ejecutar el SQL Query >> " + e.getMessage());
            new Alert(type, Message).showAndWait();
        }
        return resultSet;
    }

    /**
     * El siguiente metodo permite ejecutar querys que requieran modificar o eliminar registros
     * @param SQLQuery : Sentencia SQL a ejecutar
     *
     */
    public void executeDBModQuery(String SQLQuery){
        System.out.println("\n[DEBUG]: EJECUTANDO INTERNAL MOD QUERY");
        try{
            reOpenStatement();
            statement.executeUpdate(SQLQuery);
            connect();
            closeConnection();
        }catch(SQLException e){
            System.out.println("[Error]: No se pudo ejecutar el Query >> " + e);
        }
    }
    /**
     * El siguiente metodo permite ejecutar querys que requieran modificar o eliminar registros
     * @param SQLQuery : Sentencia SQL a ejecutar
     *
     */
    public void customModQuery(String SQLQuery, String message, Alert.AlertType type){
        System.out.println("\n[DEBUG]: EJECUTANDO INTERNAL MOD QUERY");
        try{
            reOpenStatement();
            statement.executeUpdate(SQLQuery);
            connect();
            closeConnection();
        }catch(SQLException e){
            System.out.println("[Error]: No se pudo ejecutar el Query >> " + e);
            new Alert(type, message).showAndWait();
        }
    }

    /**
     * Metodo para ejecutar un query que requiera modificar la tabla o base de datos
     * @param SQLQuery
     * @return
     */
    public int executeModQuery(String SQLQuery){
        System.out.println("\n[DEBUG]: EJECUTANDO MOD QUERY");
        try{
            reOpenStatement();
            int updateState = statement.executeUpdate(SQLQuery);
            connect();
            closeConnection();
            return updateState;
        }catch(SQLException e){
            System.out.println("[Error]: No se pudo ejecutar el Query >> " + e);
        }
        return -1;
    }


    /**
     * Metodo para ejecutar el Query y quede dentro del result set de la clase
     * @param SQLQuery
     */
    public void internalExecute(String SQLQuery){
        System.out.println("[DEBUG]: EJECUTANDO INTERNAL EXECUTE");

        resultSet = execute(SQLQuery);

    }

    /**
     * Este metodo permite obtener el result set obtenido de un query
     * @return obtiene el resultset para utilizarlo externamente
     */
    public ResultSet getResultSet(){ return resultSet; }


    /**
     * Metodo permite abrir un statement
     */
    public void openStatement(){
        System.out.println("\n[DEBUG]: EJECUTANDO OPEN STATEMENT");
        try{

            statement = conn.createStatement();
            System.out.println("[Info]: Statement abierto correctamente");
        }catch(SQLException | NullPointerException e){
            System.out.println("[Error]: No se pudo crear Statement >> " + e);
        }
    }

    public void reOpenStatement(){
        System.out.println("\n[DEBUG]: EJECUTANDO REOPEN STATEMENT");
        closeStatement();
        connect();
    }

    public void closeStatement(){
        System.out.println("\n[DEBUG]: EJECUTANDO CLOSE STATEMENT");
        try{

            statement.close();
            statement = null;
            System.out.println("[Info]: Statement cerrado correctamente");
        }catch(SQLException | NullPointerException e){
            System.out.println("[Error]: No se pudo Cerrar el Statement >> " + e);
        }

    }

    public void closeConnection(){
        System.out.println("\n[DEBUG]: EJECUTANDO CLOSE CONNECTION");
        try {
            conn.close();
            System.out.println("[Info]: La conexion se pudo cerrar correctamente");
        } catch (SQLException | NullPointerException e) {
            System.err.println("[Error]: No se pudo cerrar la conexión a la base de datos: " + e);
        }
    }

}
