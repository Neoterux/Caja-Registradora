package proyecto.Controllers;


import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyecto.POJO.Clients;
import proyecto.POJO.Controller.ControllerClient;
import proyecto.POJO.Controller.ControllerOrder;
import proyecto.POJO.Controller.ControllerProduct;
import proyecto.POJO.Order;
import proyecto.POJO.ProductModel;
import proyecto.POJO.Producto;
import proyecto.POJO.Worker;
import proyecto.Utils.NodeUtils;
import proyecto.Utils.RandomUtils;


/**
 * Clase controlador de la interfaz del cajero
 * @author user
 */
public class UserController implements Initializable {
    
    private final float IVA = 0.12f;
    
    //<FXML Variables>
    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtPNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TableView<ProductModel> tvProductos;

    @FXML
    private TableColumn<ProductModel, String> col_id;

    @FXML
    private TableColumn<ProductModel, String> col_name;

    @FXML
    private TableColumn<ProductModel, Integer> col_cant;

    @FXML
    private TableColumn<ProductModel, Float> col_prec;

    @FXML
    private TableColumn<ProductModel, Float> col_tot;

    @FXML
    private TextField txtSubtotal;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtTotal;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPagar;
    
    @FXML
    private Button btnAddProduct;
    
      @FXML
    private Button btnReturn;

    @FXML
    private Button btnClose;

    //</FXML Variables>
    
    
    
    //<FXML ACTIONS>
    
    /**
     * Metodo para añadir productos a la tabla
     * @param event click event
     */
    @FXML
    void AddProduct(ActionEvent event) {
        if(NodeUtils.notIsNullOrEmpty(txtCodigo, txtCedula, txtNombre, txtPNombre) && NodeUtils.parseInt(txtCantidad) > 0){
            if(NodeUtils.parseInt(txtCantidad) <= product.getCantidad_disponible()){
                Producto p = cp.getFromID(txtCodigo.getText().trim());
                p.setCantidad_disponible(NodeUtils.parseInt(txtCantidad));
                System.out.println("[ADDPRODUCT] " + p.toModel().toString());
                //System.out.println(p.toModel().existsInTable(tvProductos));
                list.add(p.toModel());
                updateTable();
                calcOther();
                NodeUtils.clearAll(txtCodigo, txtCedula, txtNombre, txtPNombre);
                txtCodigo.requestFocus();
            }          
        }else{
            event.consume();
            Alert a = new Alert(Alert.AlertType.ERROR, "Rellene los datos faltantes");
            a.showAndWait();
            if(NodeUtils.isNullOrEmpty(txtCedula)){
                txtCedula.requestFocus();
            }
            else if(NodeUtils.isNullOrEmpty(txtCodigo)){
                txtCodigo.requestFocus();
            }
        }
    }
    
    /**
     * Metodo para detectar el tab o enter para obtener datos del cliente y/o producto
     * @param event key event
     */
    @FXML
    void tabRealeased(KeyEvent event) {
        System.out.println("[EJECUTSNDO TAB RELEASED] " + event.getCode());
        
       if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){
           
           if(event.getSource() == txtCedula && NodeUtils.isNullOrEmpty(txtNombre) && NodeUtils.notIsNullOrEmpty(txtCedula) ){
               
                Clients client = cc.getFromCedula(txtCedula.getText().trim());
                c = client;
                txtNombre.setText(client.getFullName());
                txtDireccion.setText(client.getDireccion());
                txtTelefono.setText(client.getTelefono());
                txtEmail.setText(client.getEmail());
           }
           
           if(event.getSource() == txtCodigo && ( (NodeUtils.isNullOrEmpty(txtPNombre) && NodeUtils.notIsNullOrEmpty(txtCodigo))  || tvProductos.getItems().size() > 0) ){
                Producto producto = cp.getFromID(txtCodigo.getText());
                product = producto;
                txtPNombre.setText(producto.getNombre_producto());
                txtPrecio.setText(producto.getPrecioIntText() );  
           } 
       }
       
       
    }//end tabreleased
    
    
    /**
     * Metodo para filtrar la entrada de caracteres a textfield
     * @param event  key event
     */
    @FXML
    void keyPressed(KeyEvent event) {
        
        if (event.getSource() == txtCantidad){
            if(event.getText().matches("\\D")){
                txtCantidad.setText(txtCantidad.getText().replaceAll("\\D", ""));
                event.consume();
            }
        }
    }
    
    /**
     * Metodo del boton cancelar
     * @param event 
     */
    @FXML
    void cancel(ActionEvent event) {
       NodeUtils.clearAll(this.scene.getRoot());
       txtCedula.requestFocus();
       
       order.setID(RandomUtils.randID());
    }
    
    /**
     * Metodo para boton pagar
     * @param event click event
     */
    @FXML
    void pay(ActionEvent event) {
        order.setID(RandomUtils.randID());
        order.setFecha(new Date().getTime());
      tvProductos.getItems().forEach(it->{
         order.setEmpleado_id(current_worker.getId());
         order.setCedula(c.getCedula());
         order.setId_producto(it.getId());
         order.setCantidad(it.getCantidad_disponible());
         order.setPrecio(it.getPrecio());
         order.setTotal_precio(it.getTotal());
         order.setProduct_name(it.getNombre_producto());
         orderList.add(order);
      });
      
      orderList.forEach((o)->{
          co.register(o);
      });
      Producto p;
      //modify product quantity      
      for(ProductModel pm : tvProductos.getItems()){
          p = cp.getFromID(pm.getId());
          p.setCantidad_disponible(p.getCantidad_disponible() - pm.getCantidad_disponible());
          cp.update(p);
      }      
      FacturaController fc = new FacturaController(orderList, c, subtotal, iva, total);    
    }
    
   
    /**
     * Metodo para boton de cerrar
     * @param event click event
     */
    @FXML
    void OnClose(ActionEvent event) {
        Platform.setImplicitExit(true);
        stage.close();
    }
    
    
    
    //</FXML ACTIONS>
    /**
     * Listas para llenar tablas
     */
    ObservableList<ProductModel> list;
    List<ProductModel> productsList;
    List<Order> orderList;
    
    /**
     * Objetos para la factura
     */
    Worker current_worker;
    Order order;
    Clients c;
    Producto product;
    ControllerProduct cp;
    ControllerClient cc;
    ControllerOrder co;
    private Stage stage;
    private Date date;
    private float total;
    private float subtotal;
    private float iva;
    private Scene scene;
    
    /**
     * COnstructor del GUI
     * @param worker trabajador que esta trabajando
     */
    public UserController(Worker worker) {
        stage = new Stage();
        date = new Date();
        order = new Order();
        cp = new ControllerProduct();
        cc = new ControllerClient();
        co = new ControllerOrder();
        productsList = new ArrayList<>();
        orderList = new ArrayList<>();               
        try{
            this.current_worker = worker;
            System.out.println("[CONSTRUCTOR USER CONTROLLER]\n"
                    + "[INFO] IMPRIMIENDO CURRENT_WORKER\n"
                    + current_worker.toString());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto/Views/UserView.fxml"));
            loader.setController(this);            
            System.out.println("[LOADER INFO]: " + loader.toString());
            Parent root = loader.load();            
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Empleado: " + current_worker.getFullName());
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("[Error al cargar Panel de Usuario] "
                    + e + "\n[Causa]: " + e.getCause()+ 
                    "\n[Message]: " + e.getMessage());
        }
    }
    
    /**
     * Constructor para administrador
     * @param worker
     * @param stage 
     */
    public UserController(Worker worker, Stage stage){
        this(worker);
        btnReturn.setOnAction(av->{
            stage.show();
            this.stage.close();
        });
    }
    
    /**
     * Metodo ignorado
     * @return 
     */
    public boolean showWithReturn(){
        boolean yes =false;
        stage.show();
        
        return yes;
    }
    
    
    
    /**
     * Metodo de initialize de siempre......
     * @param location ya sabes que es
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Entrando en Controller User");
        stage.setMinWidth(1000f);
        stage.setMinHeight(600f);
       btnReturn.setVisible(current_worker.isAdmin());      
        txtCedula.positionCaret(0);
        Platform.runLater(()->{
            this.txtCedula.requestFocus();        
        });
        txtCantidad.textProperty().addListener((o, ovalue, nvalue)->{
            if(nvalue.matches("\\d*"))return;
            txtCantidad.setText(nvalue.replaceAll("[^\\d]", ""));
        });        
        NodeUtils.limitTextFieldLength(5, txtCodigo);        
        NodeUtils.limitTextFieldLength(10, txtCedula);
        list = FXCollections.observableArrayList();
        list.clear();
        cfgColumns();        
    }
    
    /**
     * Configurar columnas
     */
    private void cfgColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
        col_cant.setCellValueFactory(new PropertyValueFactory<>("cantidad_disponible"));
        col_prec.setCellValueFactory(new PropertyValueFactory<>("precio"));
        col_tot.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    
    /**
     * Metodo para actualizar tabla
     */
    private void updateTable(){
       tvProductos.setItems(list);      
    }
    
    /**
     * Metodo para calcular otros calores
     */
   private void calcOther(){       
       subtotal = tvProductos.getItems().stream().map((pm) -> pm.getTotal()).reduce(subtotal, (accumulator, _item) -> accumulator + _item);
       iva = (float) (Math.round((subtotal*IVA )*100.0) / 100.0);
       total = (float) (Math.round((subtotal + iva)*100.0) / 100.0);
       txtSubtotal.setText(String.valueOf(subtotal));
       txtIva.setText(String.valueOf(iva));
       txtTotal.setText(String.valueOf(total));
   }
}
