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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

public class AdminController implements Initializable {
    


    /**
     * Tab Empleados
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
    /**
     * Tab Clientes
     */
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
                cw.register(w);
                succesfullDataAlert.showAndWait();
                buildWorkerData();

            } else {
                emptyTextFieldAlert.showAndWait();
            }
        }else if (event.getSource().equals(btnApplyCli)){
            //Boton agregar tab Cliente
            
            if (NodeUtils.notIsNullOrEmpty(txtCliCedula, txtCliNombre, txtCliApellido, txtCliTelf, txtCliDir, txtCliEmail)) {
                Clients c = new Clients();
                c.setCedula(txtCliCedula.getText());
                c.setNombre(txtCliNombre.getText());
                c.setApellido(txtCliApellido.getText());
                c.setDireccion(txtCliDir.getText());
                c.setTelefono(txtCliTelf.getText());
                c.setEmail(txtCliEmail.getText());
                cc.register(c);

                
                    succesfullDataAlert.showAndWait();

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
    public AdminController(Worker w){
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
            System.out.println("[IOException]\n"
                    + "[Class = ] AdminController\n"
                    + "[Cause]: " + ie.getCause() +
                    "\n[Message]: "+ ie.getMessage()
                    + "\n[StackTrace]: ");
                    ie.printStackTrace();
            
        }catch (NullPointerException ne){
            System.out.println("[NullPointerException]\n"
                    + "[Class = ] AdminController\n"
                    + "[Cause]: " + ne.getCause() +
                    "\n[Message]: "+ ne.getMessage()
                    + "\n[StackTrace]: ");
                    ne.printStackTrace();
        }catch(IllegalArgumentException iae){
            System.out.println("[ILLEGAL ARGUMENT EXCEPTION]\n"
                    + "[Class = ] AdminController\n"
                    + "[Cause]: " + iae.getCause() +
                    "\n[Message]: "+ iae.getMessage()
                    + "\n[StackTrace]: ");
                    iae.printStackTrace();
        }
            
        
    }
        
    
    //--------------------------------------- FXML EVENTS -------------------------
     
    @FXML
    void GeneralKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.F5){
            buildClientData();
            buildPedidoData();
            buildProductData();
            buildWorkerData();
        }
    }
    @FXML
    void goToCajero(ActionEvent event) {
        
        
       UserController uc = new UserController(current_worker, stage);
       stage.hide();
    }
    
    @FXML
    void onClose(ActionEvent event) {
       Platform.setImplicitExit(true);
       stage.close();
    }
    
    @FXML
    void onLogOut(ActionEvent event) {
       stage.close();
       
        LogInDialog lg = new LogInDialog();
        lg.show();
    }
    
    @FXML
    void onProductMod(ActionEvent event) {
        tvProduct.getItems().forEach(pm->{
            cp.update(pm.toProduct());
        });
    }
     @FXML
    void onClientMod(ActionEvent event) {
        tClientes.getItems().forEach(cm->{
            cc.update(cm.toClient());
        });
    }
     @FXML
    void addProduct(ActionEvent event) {
      if (NodeUtils.notIsNullOrEmpty(txtProductID, txtProductName, txtProductCuant, txtProductPrice)){
          Producto p = new Producto();
          p.setId(txtProductID.getText().trim());
          p.setNombre_producto(txtProductName.getText().trim());
          p.setPrecio(Float.parseFloat(txtProductPrice.getText().trim()));
          p.setCantidad_disponible(Integer.parseInt(txtProductCuant.getText().trim()));
          cp.register(p);
          buildProductData();
          succesfullDataAlert.showAndWait();
      }else{
          emptyTextFieldAlert.showAndWait();
      }
    }
    
     @FXML
    void searchProforma(ActionEvent event) {
       if(NodeUtils.isNullOrEmpty(txtSearchProforma)){
           
           co.searchByIDorCID(txtSearchProforma.getText().trim()).forEach(Odata::add);
           tvProforma.setItems(Odata);
           
       }else{
        //Odata.clear();
        
        
       }
    }
    
    @FXML
    void searchClient(ActionEvent event) {
        if(NodeUtils.isNullOrEmpty(txtSearchClient)){
            buildClientData();
        }else{
            Cdata.clear();
            
            cc.search(txtSearchClient.getText().trim()).forEach(cl->{
                Cdata.add(cl.toModel());
            });
            tClientes.setItems(Cdata);
        }
    }

    @FXML
    void searchProduct(ActionEvent event) {

    }
    
     @FXML
    void searchByDate(ActionEvent event) {
       Odata.clear();
         LocalDate lf = dpFirst.getValue();
         LocalDate le = dpEnd.getValue();
         Date f = Date.from(lf.atStartOfDay().atZone(ZoneId.of("America/Guayaquil")).toInstant());
         Date e = Date.from(le.atStartOfDay().atZone(ZoneId.of("America/Guayaquil")).toInstant());
       co.searchByDate(f, e)
               .forEach(Odata::add);
         System.out.println("[Date First] " + f.toString() + "\t[End Date] " + e.toString());
       tvProforma.setItems(Odata);
    }
    
    @FXML
    void addClient(ActionEvent event) {
        if(NodeUtils.notIsNullOrEmpty(txtCliCedula, txtCliApellido, txtCliNombre, txtCliEmail, txtCliTelf, txtCliDir)){
            Clients cli = new Clients();
            cli.setApellido(txtCliApellido.getText().trim());
            cli.setCedula(txtCliCedula.getText().trim());
            cli.setDireccion(txtCliDir.getText().trim());
            cli.setEmail(txtCliEmail.getText().trim());
            cli.setNombre(txtCliNombre.getText().trim());
            cli.setTelefono(txtCliNombre.getText().trim());
            cc.register(cli);
            buildClientData();
            succesfullDataAlert.showAndWait();
        }else{
            emptyTextFieldAlert.showAndWait();
        }
    }
    
    // ------------------------------------------------- END FXML EVENTS ----------------------------------------------------------
    

    private ObservableList<WorkerModel> Wdata;
    private ObservableList<ClientsModel> Cdata;
    private ObservableList<ProductModel> Pdata;
    private ObservableList<OrderModel> Odata;
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
     * Initialize Empleados Tab
     */
    private void buildWorkerData(){
        
        Wdata.clear();
        cw.getList().forEach((w) -> {
                Wdata.add(w.toWorkerModel());
        });

            //System.out.println("Ejecutandola tb" + Wdata);
            tEmpleado.setItems(Wdata);
    }
    
    private void buildClientData() {
        
        Cdata.clear();
        cc.list().forEach((client)->{
            Cdata.add(client.toModel());
            System.out.println(client.toString());
        
        });
        
        tClientes.setItems(Cdata);
    }
    
    private void buildProductData(){
        Pdata.clear();
        cp.list().forEach((product) ->{
            Pdata.add(product.toModel());
            System.out.println("[BUILD PRODUCT DATA]" + product.toString());
        });
        tvProduct.setItems(Pdata);
    }
    
    private void buildPedidoData(){
        Odata.clear();
        co.list().forEach((order)->{
            Odata.add(order.toModel());
        });
        
        tvProforma.setItems(Odata);
    }
    
    public void onEmpCellClicked(MouseEvent mouseEvent) {

        System.out.println(tEmpleado.getSelectionModel().getSelectedItem().toString());
        btnEmpMod.setDisable(false);

    }

    public void onMod(ActionEvent actionEvent) {
        
         tEmpleado.getItems().forEach(csrm->{
            System.out.println(csrm.toString());
            cw.update(csrm.toWorker());
        
        });
         buildWorkerData();
         ((Button)actionEvent.getSource()).setDisable(true);
    }

    public void onUpdate(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(btnEmpUp)){
            /**
             * Tab Empleado Button Update
             */
            buildWorkerData();
            
        }else{
            /**
             * Tab Cliente Button Update
             */
            buildClientData();

        }
    }

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

    public void onDeleteButton(ActionEvent actionEvent) {

        if(actionEvent.getSource().equals(btnEmpDel)){

           /* db.customModQuery("Delete from empleados where id ='"
                    +tEmpleado.getSelectionModel().getSelectedItem().idProperty().get() +
                    "';", "No se pudo eliminar al empleado, contacte con un superior", Alert.AlertType.INFORMATION);*/
            new Alert(Alert.AlertType.INFORMATION, "Registro eliminado correctamente").showAndWait();
            buildWorkerData();
        }

    }


   
    

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
