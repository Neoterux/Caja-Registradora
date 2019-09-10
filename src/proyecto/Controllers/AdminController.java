package proyecto.Controllers;

import java.io.IOException;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import proyecto.Custom.LogInDialog;
import proyecto.POJO.Clients;
import proyecto.POJO.ClientsModel;
import proyecto.POJO.Controller.ControllerClient;
import proyecto.POJO.Controller.ControllerOrder;
import proyecto.POJO.Controller.ControllerProduct;
import proyecto.POJO.Controller.ControllerWorker;
import proyecto.POJO.OrderModel;
import proyecto.POJO.ProductModel;
import proyecto.POJO.Producto;
import proyecto.POJO.Worker;
import proyecto.POJO.WorkerModel;
import proyecto.Utils.NodeUtils;


/**
 * Clase Controlador del panel de administracion
 * @author Neoterux
 */

public class AdminController  implements Initializable, Runnable {
    
    private final Logger logger = Logger.getLogger(AdminController.class.getName());
    /**
     * Objetos propios del FXML que van a referenciar a los objetos de la interfaz
     */
    
        @FXML
    private DatePicker dpFirst;

    @FXML
    private DatePicker dpEnd;

    @FXML
    private ComboBox<String> cboxEstado;

    @FXML
    private CheckBox cbAdmin;

    @FXML
    private TextField txtID,txtNom,txtApellido,txtCedula,txtDir,txtEmail,txtPassword, txtSearchEmp;

    @FXML
    private Button btnApply,btnEmpMod, btnEmpUp, btnEmpSearch, btnEmpDel;

    @FXML
    private TableView<WorkerModel> tEmpleado;
    
    @FXML
    private Button btnProdDel;

    @FXML
    private TableColumn<WorkerModel, String> col_id_e;

    @FXML
    private TableColumn<WorkerModel, String> col_nombre_e;

    @FXML
    private TableColumn<WorkerModel, String> col_apellido_e;

    @FXML
    private TableColumn<WorkerModel, String> col_cedula_e;

    @FXML
    private TableColumn<WorkerModel, String> col_estado_civil; // TODO: Añadir La forma de ENUM para la seleccion

    @FXML
    private TableColumn<WorkerModel, String> col_dir_e;

    @FXML
    private TableColumn<WorkerModel, String> col_email_e;

    @FXML
    private TableColumn<WorkerModel, String> col_pass;

    @FXML
    private TableColumn<WorkerModel, Boolean> col_admin;

    @FXML
    private TextField txtProductID;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductCuant;

    @FXML
    private TextField txtSearchProduct;

    @FXML
    private TextField txtSearchProforma;
    
    @FXML
    private TextField txtCliCedula, txtCliNombre,txtCliApellido,txtCliTelf,txtCliDir, txtCliEmail;

    @FXML
    private Button btnCliMod, btnCliSearch, btnCliUp, btnCliDel, btnApplyCli;

    @FXML
    private TableView<ClientsModel> tClientes;
    @FXML
    private TableColumn<ClientsModel, String> col_ced_c;

    @FXML
    private TableColumn<ClientsModel, String> col_nombre_c;

    @FXML
    private TableColumn<ClientsModel, String> col_apel_c;

    @FXML
    private TableColumn<ClientsModel, String> col_telf_c;

    @FXML
    private TableColumn<ClientsModel, String> col_dir_c;

    @FXML
    private TableColumn<ClientsModel, String> col_email_c;
    
    @FXML
    private TableView<ProductModel> tvProduct;
      @FXML
    private TableColumn<ProductModel, String> col_prod_id;

    @FXML
    private TableColumn<ProductModel, String> col_prod_name;

    @FXML
    private TableColumn<ProductModel, Float> col_prod_price;

    @FXML
    private TableColumn<ProductModel, Integer> col_prod_cuant;

       @FXML
    private TextField txtSearchClient;
@FXML
    private TableView<OrderModel> tvProforma;

    @FXML
    private TableColumn<OrderModel, String> col_prof_id;

    @FXML
    private TableColumn<OrderModel, String> col_prof_ced;

    @FXML
    private TableColumn<OrderModel, String> col_prof_prod;

    @FXML
    private TableColumn<OrderModel, Integer> col_prof_cant;

    @FXML
    private TableColumn<OrderModel, Float> col_prof_price;

    @FXML
    private TableColumn<OrderModel, Float> col_prof_tot;

    @FXML
    private TableColumn<OrderModel, Timestamp> col_prof_fecha;

    @FXML
    private TableColumn<OrderModel, String> col_prof_emp;

@FXML
    private Button btnClose;



/**
 * Metodo para ejecutar la accion de click a botones
 * @param event evento click
 */
    @FXML
    private void onApplyClick(Event event) {
        
        if(event.getSource().equals(btnApply)) {
            //Boton agregar tab Empleados
            if (NodeUtils.notIsNullOrEmpty(txtID, txtNom, txtApellido, txtCedula, txtDir, txtEmail, txtPassword)) {
                Worker w = new Worker();
                w.setId(txtID.getText().trim());
                w.setNombre(txtNom.getText().trim());
                w.setApellido(txtApellido.getText().trim());
                w.setCedula(txtCedula.getText().trim());
                w.setDireccion(txtDir.getText().trim());
                w.setEmail(txtEmail.getText().trim());
                w.setPass(txtPassword.getText().trim());
                w.setEstado_civil(cboxEstado.getSelectionModel().getSelectedItem());
                if(cw.register(w)){
                    succesfullDataAlert.showAndWait();
                    buildWorkerData();
                }else{
                    errorAlert.setContentText("No se pudo agregar Trabajador, ya existente");
                    errorAlert.show();
                }
                NodeUtils.clearAll(txtID, txtNom, txtApellido, txtCedula, txtDir, txtEmail, txtPassword);
                cbAdmin.setSelected(false);

            } else {
                emptyTextFieldAlert.showAndWait();
            }
        }
    }

    
    private Stage stage;
    private Scene scene;
    private Worker current_worker;
    private Alert emptyTextFieldAlert;
    private Alert succesfullDataAlert;
    private ControllerWorker cw ;
    private ControllerClient cc;
    private ControllerProduct cp;
    private ControllerOrder co;
    private Alert warningAlert;
    private Alert errorAlert;
    
    /**
     * Constructor para construir el fxml y mostrarlo
     * @param w Trabajador que inicio sesion
     */
    public AdminController(Worker w){
        BasicConfigurator.configure();
        warningAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert  = new Alert(Alert.AlertType.ERROR);
        logger.debug("Cargando Interfaz de Admin");
        stage = new Stage();
        cw = new ControllerWorker();
        cc = new ControllerClient();
        cp = new ControllerProduct();
        co = new ControllerOrder();
        current_worker = w; 
        succesfullDataAlert = new Alert(Alert.AlertType.INFORMATION, "Datos agregados correctamente, refresque la tabla");
        emptyTextFieldAlert = new Alert(Alert.AlertType.INFORMATION, "Relleno los campos faltantes por favor");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto/Views/AdminView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setMaximized(true);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        }catch(IOException ie ){
            logger.fatal("IOException producida, finalizando tarea: " + ie.getMessage());
            
        }catch (NullPointerException ne){
            logger.fatal("NullPointerException producida, finalizando tarea: " + ne.getMessage());
        }catch(IllegalArgumentException iae){
            logger.fatal("IllegalArgumentException producida, finalizando tarea: " + iae.getMessage());
        }catch(IllegalStateException ie){
            logger.fatal("IllegalStateExeption producida, finalizando tarea: " + ie.getMessage());
        }
            
        
    }
        
    
    //--------------------------------------- FXML EVENTS -------------------------
     
    /**
     * Metodo para actualizar cuando se pulse el F5
     * @param event click event
     */
    @FXML
    void GeneralKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.F5){
            logger.debug("Actualizando tablas");
            buildClientData();
            buildPedidoData();
            buildProductData();
            buildWorkerData();
        }
    }
    
    /**
     * Metodo para dirigirse al cajero en caso de requerirlo
     * @param event 
     */
    @FXML
    void goToCajero(ActionEvent event) {      
       UserController uc = new UserController(current_worker, stage);
       stage.hide();
    }
    
    
    /**
     * Metodo para el boton que simula el close
     * @param event click event 
     */
    @FXML
    void onClose(ActionEvent event) {
       Platform.setImplicitExit(true);
       stage.close();
    }
    
    
    /**
     * Metodo para cerrar sesion
     * @param event 
     */
    @FXML
    void onLogOut(ActionEvent event) {
       stage.close();
       
        LogInDialog lg = new LogInDialog();
        lg.show();
    }
    
    /**
     * Metodo para actualizar la tabla de productos
     * @param event click event
     */
    @FXML
    void onProductMod(ActionEvent event) {
        tvProduct.getItems().forEach(pm->{
            cp.update(pm.toProduct());
        });
    }
    
    /**
     * Metodo para actualizar tabla de clientes
     * @param event 
     */
     @FXML
    void onClientMod(ActionEvent event) {
        tClientes.getItems().forEach(cm->{
            cc.update(cm.toClient());
        });
    }
    
    /**
     * Metodo para añadir productos a la base de datos y a la tabla
     * @param event click evento
     */
     @FXML
    void addProduct(ActionEvent event) {
        logger.debug("Añadiendo Producto");
      if (NodeUtils.notIsNullOrEmpty(txtProductID, txtProductName, txtProductCuant, txtProductPrice)){
          Producto p = new Producto();
          p.setId(txtProductID.getText().trim());
          p.setNombre_producto(txtProductName.getText().trim());
          p.setPrecio(Float.parseFloat(txtProductPrice.getText().trim()));
          p.setCantidad_disponible(Integer.parseInt(txtProductCuant.getText().trim()));
          logger.info("Registrando Producto");
          if(cp.register(p)){
            buildProductData();
            succesfullDataAlert.showAndWait();
          }else{
              errorAlert.setContentText("Error datos de producto ya existente");
              errorAlert.show();
          }
          
          
          NodeUtils.clearAll(txtProductID, txtProductName, txtProductCuant, txtProductPrice);
      }else{
          
          emptyTextFieldAlert.showAndWait();
      }
    }
    
    /**
     * Metodo para buscar factura por ID o CID
     * @param event click event
     */
     @FXML
    void searchProforma(ActionEvent event) {
        logger.debug("Buscando entre las facturas");
       if(NodeUtils.notIsNullOrEmpty(txtSearchProforma)){
           Odata.clear();
           logger.info("Buscando facturas");
           co.searchByIDorCID(txtSearchProforma.getText().trim()).forEach(Odata::add);
           tvProforma.setItems(Odata);
           
       }else{
        //Odata.clear();
        buildPedidoData();
       }
    }
    
    
    /**
     * Metodo para buscar clientes
     * @param event click event
     */
    @FXML
    void searchClient(ActionEvent event) {
        if(NodeUtils.isNullOrEmpty(txtSearchClient)){
            buildClientData();
        }else{
            Cdata.clear();
            logger.info("Buscando cliente");
            cc.search(txtSearchClient.getText().trim()).forEach(cl->{
                Cdata.add(cl.toModel());
            });
            tClientes.setItems(Cdata);
            
        }
    }

    /**
     * Metodo para buscar producto
     * @param event click event
     */
    @FXML
    void searchProduct(ActionEvent event) {
        if (NodeUtils.notIsNullOrEmpty(txtSearchProduct)){
            Pdata.clear();
            logger.info("Buscando productos");
            
            cp.searchByIDorName(txtSearchProduct.getText().trim());
        }else{
            buildProductData();
        }
    }
    
    /**
     * Metodo para buscar por fecha
     * @param event click event
     */
     @FXML
    void searchByDate(ActionEvent event) {
       
        logger.debug("Buscando Factura por fecha");
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         LocalDate lf = dpFirst.getValue();
         LocalDate le = dpEnd.getValue();
         Date f = Date.from(lf.atStartOfDay().atZone(ZoneId.of("America/Guayaquil")).toInstant());
         Date e = Date.from(le.atStartOfDay().atZone(ZoneId.of("America/Guayaquil")).toInstant());
         if (e.getTime() == f.getTime()){
             e = new Date(e.getTime() + TimeUnit.DAYS.toMillis(1));
         }
         if( e.getTime() < f.getTime()){
             //TODO("Error escoger bien fecha")
             warningAlert.setContentText("Verifique bien la fecha, fecha desde tiene que ser menor a fecha hasta");
             warningAlert.show();
         }
         if(e.getTime() > f.getTime()){
             Odata.clear();
            logger.debug("Fecha desde: " + format.format(f));
            logger.debug("Fecha hasta: " + format.format(e));
            logger.info("Buscando por factura por fecha");
            co.searchByDate(f, e)
               .forEach(Odata::add);
            System.out.println("[Date First] " + f.toString() + "\t[End Date] " + e.toString());
            tvProforma.setItems(Odata);
         }
    }
    
    
    /**
     * Metodo para añadir cliente a la tabla
     * @param event click event
     */
    @FXML
    void addClient(ActionEvent event) {
        if(NodeUtils.notIsNullOrEmpty(txtCliCedula, txtCliApellido, txtCliNombre, txtCliEmail, txtCliTelf, txtCliDir)){
            Clients cli = new Clients();
            cli.setApellido(txtCliApellido.getText().trim());
            cli.setCedula(txtCliCedula.getText().trim());
            cli.setDireccion(txtCliDir.getText().trim());
            cli.setEmail(txtCliEmail.getText().trim());
            cli.setNombre(txtCliNombre.getText().trim());
            cli.setTelefono(txtCliTelf.getText().trim());
            logger.info("Registrando Cliente");
            if(cc.register(cli)){
                succesfullDataAlert.showAndWait();
                buildClientData();
            }else{
               errorAlert.setContentText("Error cliente ya existente");
               errorAlert.show();
            }
            
           
            NodeUtils.clearAll(txtCliCedula, txtCliApellido, txtCliNombre, txtCliEmail, txtCliTelf, txtCliDir);
        }else{
            emptyTextFieldAlert.showAndWait();
        }
    }
    
    // ------------------------------------------------- END FXML EVENTS ----------------------------------------------------------
    
    /**
     * Objetos para añadir datos a las tablas
     */
    private ObservableList<WorkerModel> Wdata;
    private ObservableList<ClientsModel> Cdata;
    private ObservableList<ProductModel> Pdata;
    private ObservableList<OrderModel> Odata;
    
    /**
     * Metodo que autoinicializa el controlador
     * @param location direccion del FXML
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Wdata = FXCollections.observableArrayList();
        Cdata = FXCollections.observableArrayList();
        Pdata = FXCollections.observableArrayList();
        Odata = FXCollections.observableArrayList();
        NodeUtils.addItemsToComboBox(cboxEstado,"Soltero", "Casado", "Viudo", "Divorciado");
        NodeUtils.limitTextFieldLength(10, txtCedula, txtCliCedula, txtCliTelf);
        NodeUtils.limitTextFieldLength(7, txtID);
        NodeUtils.limitTextFieldLength(5, txtProductID);
        cboxEstado.getSelectionModel().selectFirst();
        cfgColumns();
        loadData();
        
        tvProforma.setOnMouseClicked(me->{
            if(me.getClickCount() == 2){
                BarcodeDialog bc = new BarcodeDialog(tvProforma.getSelectionModel().getSelectedItem().getId(), stage.getScene().getWindow());
            }
        });

    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Metodo para cargar datos en 2do plano
     */
    private void loadData(){
        Alert loadAlert = new Alert(Alert.AlertType.INFORMATION, "Cargando Datos, Por favor espere", ButtonType.APPLY);
        Platform.runLater(()->{
            System.out.print("Ejecutando runlater");
            buildWorkerData();
            buildClientData();
            buildProductData();
            buildPedidoData();
        });
    }
    
    
    

    /**
     * Cargar datos de empleado de la base de datos a la tabla
     */
    private void buildWorkerData(){
        logger.info("Obteniendo datos de Trabajadores...");
        Wdata.clear();
        cw.getList().forEach((w) -> {
                Wdata.add(w.toWorkerModel());
        });
        tEmpleado.setItems(Wdata);
    }
    
    /**
     * Metodo para cargar datos de clientes de la base de datos a la tabla
     */
    private void buildClientData() {
        Cdata.clear();
        logger.info("Obteniendo datos de Clientes...");
        cc.list().forEach((client)->{
            Cdata.add(client.toModel());
        });
        tClientes.setItems(Cdata);
    }
    
    
    /**
     * Metodo para cargar datos de productos de la base de datos a la tabla
     */
    private void buildProductData(){
        Pdata.clear();
        logger.info("Obteniendo datos de Productos");
        cp.list().forEach((product) ->{
            Pdata.add(product.toModel());
        });
        tvProduct.setItems(Pdata);
    }
    
    
    /**
     * Metodo para cargar datos de pedidos de la base de datos a la tabla
     */
    private void buildPedidoData(){
        Odata.clear();
        
        logger.info("Obteniendo datos de Facturas");
        co.list().forEach((order)->{
            Odata.add(order.toModel());
        });
        tvProforma.setItems(Odata);
    }
    
    /**
     * Metodo para click en el cell de empleado
     * @param mouseEvent 
     */
    public void onEmpCellClicked(MouseEvent mouseEvent) {
        System.out.println(tEmpleado.getSelectionModel().getSelectedItem().toString());
        btnEmpMod.setDisable(false);
    }

    
    /**
     * Metodo para modificar empleado de la tabla
     * @param actionEvent 
     */
    public void onMod(ActionEvent actionEvent) {        
         tEmpleado.getItems().forEach(csrm->{
            System.out.println(csrm.toString());
            cw.update(csrm.toWorker());        
        });
         buildWorkerData();
         ((Button)actionEvent.getSource()).setDisable(true);
    }
    
    
    /**
     * Metodo para actualizar tabla de clientes y empleado
     * @param actionEvent 
     */
    /*public void onUpdate(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(btnEmpUp)){
            /**
             * Tab Empleado Button Update
             /
            buildWorkerData();
            
        }else{
            /**
             * Tab Cliente Button Update
             /
            buildClientData();

        }
    }*/

    
    /**
     * Metodo para buscar empleados
     * @param actionEvent 
     */
    public void onSearch(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnEmpSearch)){
            Wdata.clear();
            if (NodeUtils.notIsNullOrEmpty(txtSearchEmp)){
                cw.search(txtSearchEmp.getText().trim()).forEach((w) -> {
                    Wdata.add(w.toWorkerModel());
                });
            }else {
                buildWorkerData();
            }
        }
    }

    
    
    /**
     * Metodo para borrar empleados de la tabla y de la base de datos
     * @param actionEvent 
     */
    public void onDeleteButton(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(btnEmpDel)){
            cw.delete(tEmpleado.getSelectionModel().getSelectedItem().toWorker());
            
            buildWorkerData();
        }
        if (actionEvent.getSource().equals(btnCliDel)){
            cc.delete(tClientes.getSelectionModel().getSelectedItem().toClient());
            buildClientData();
        }
        if(actionEvent.getSource().equals(btnProdDel)){
            cp.delete(tvProduct.getSelectionModel().getSelectedItem().toProduct());
            buildPedidoData();
        }

    }
   
    
    /**
     * Metodo para generar los tipos de las celdas y sus property values factory
     */
    private void cfgColumns(){
        col_id_e.setCellValueFactory(new PropertyValueFactory<>("id"));
        //col_id_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_nombre_e.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_nombre_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_apellido_e.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        col_apellido_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_cedula_e.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        col_cedula_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_estado_civil.setCellValueFactory(new PropertyValueFactory<>("estado_civil"));
        col_estado_civil.setCellFactory(TextFieldTableCell.forTableColumn());
        col_dir_e.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_dir_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_email_e.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_email_e.setCellFactory(TextFieldTableCell.forTableColumn());
        col_pass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        col_pass.setCellFactory(TextFieldTableCell.forTableColumn());
        col_admin.setCellValueFactory(cell->{
            WorkerModel w = cell.getValue();
            return new ReadOnlyBooleanWrapper(w.isAdminProperty().get());
        });
        col_admin.setCellFactory(CheckBoxTableCell.forTableColumn(col_admin));
        
        
        //Clients columns
        col_ced_c.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        col_nombre_c.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_apel_c.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        col_telf_c.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_dir_c.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        //Products columns
        col_prod_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_prod_cuant.setCellValueFactory(new PropertyValueFactory<>("cantidad_disponible"));
        col_prod_name.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
        col_prod_price.setCellValueFactory(new PropertyValueFactory<>("precio"));
        //Pedidos COlumns
        col_prof_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_prof_ced.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        col_prof_prod.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        col_prof_cant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        col_prof_price.setCellValueFactory(new PropertyValueFactory<>("precio"));
        col_prof_tot.setCellValueFactory(new PropertyValueFactory<>("total_precio"));
        col_prof_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        col_prof_emp.setCellValueFactory(new PropertyValueFactory<>("empleado_id"));
       
    }

    

    
    
}
