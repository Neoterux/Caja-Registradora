/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.Controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import proyecto.POJO.Clients;
import proyecto.POJO.Order;
import proyecto.POJO.ProformaModel;

/**
 *  Clase controlador para la interfaz factura
 * @author Neoterux
 */
public class FacturaController implements Initializable {
    
    
    /**
     * FXML componentes del GUI
     */
    
    //FXML
     @FXML
    private ImageView imgCodeBar;

    @FXML
    private Label lbelNombre;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblEmpleado;
    
    @FXML
    private Label lblDate;
    
     @FXML
    private Label lblval;
     
     @FXML
     private Label lblCode;
    @FXML
    private Label lblsub;

    @FXML
    private Label lblIva;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<ProformaModel> tvProforma;

    @FXML
    private TableColumn<ProformaModel, Integer> col_cantidad;

    @FXML
    private TableColumn<ProformaModel, String> col_producto;

    @FXML
    private TableColumn<ProformaModel, Float> col_precio;

    @FXML
    private TableColumn<ProformaModel, Float> col_total;
    
    /**
     * COnstantes para la construccion del codigo de barra
     */
    private final int BARCODE_WIDTH = 400;
    private final int BARCODE_HEIGHT = 120;
    
    /**
     * Objetos para la visualizacion de la interfaz
     */
    private Stage stage;
    private Scene scene;
    
    /**
     * Objetos para calcular cosas de la factura
     */
    private String proformaCode;
    private ObservableList<ProformaModel> list;
    private List<Order> orders;
    private Clients client;
    private float subtotal;
    private float iva;
    private float total;
    
    
    /**
     * Constructor que genera la interfaz grafica de la factura
     * @param orders lista de las ordenes que se listaran en la factura
     * @param client cliente que efectuo la compra  
     * @param subtotal subtotal de la compra
     * @param iva iva de la compra
     * @param total subtotal + iva
     */
    public FacturaController( List<Order> orders, Clients client, float subtotal, float iva, float total){
        stage = new Stage();
        proformaCode = orders.get(0).getId();
        this.subtotal = subtotal;
        this.total = total;
        this.iva = iva;
        this.orders = orders;
        this.client = client;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto/Views/factura.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            //stage.setMaxWidth(380d);
            //stage.setMinWidth(380d);
            stage.setResizable(false);
            tvProforma.setSelectionModel(null);
            stage.showAndWait();
        
        
        }catch(IOException e){
            System.out.print("[ERROR AL CARGAR FACTURA] " + e);
            e.printStackTrace();
        }
        
    }
    
    
/**
 * Metodo de autoinicializaccion del controlador
 * @param location localizacion del FXML
 * @param resources ??
 */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //NodeUtils.setTableHeightByRowCount(tvProforma, list);
        list = FXCollections.observableArrayList();
        orders.forEach(it->{
            list.add(it.toProforma());
            System.out.println(it.toString());
        });
        cfgColumn();
        tvProforma.setItems(list);
        generateQR();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        
        lbelNombre.setText(client.getFullName().toUpperCase());
        lblCedula.setText(client.getCedula().toUpperCase());
        lblDireccion.setText(client.getDireccion().toUpperCase());
        lblEmail.setText(client.getEmail().toUpperCase());
        lblEmpleado.setText(orders.get(0).getEmpleado_id().toUpperCase());
        lblDate.setText(df.format(orders.get(0).getFecha()));
        lblCode.setText(proformaCode);
        lblval.setText(String.valueOf(subtotal));
        lblsub.setText(String.valueOf(subtotal));
        lblIva.setText(String.valueOf(iva));
        lblTotal.setText(String.valueOf(total));
        
        
    }
    
    /**
     * Metodo para configurar los valores que recibiran la tablas
     */
    private void cfgColumn(){
        col_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        col_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        col_producto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    
    /**
     * Metodo para generar el codigo de barra
     */
    private void generateQR(){
        try{            
            Code39Writer c39Writter = new Code39Writer();
            BitMatrix bmatrix;
            bmatrix = c39Writter.encode(proformaCode, BarcodeFormat.CODE_39, BARCODE_WIDTH, BARCODE_HEIGHT);
            
            BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bmatrix);
            imgCodeBar.setImage(SwingFXUtils.toFXImage(barcodeImage, null));
            imgCodeBar.setFitWidth(250d);
        }catch(WriterException e){
            e.printStackTrace();
        }
    }
    
}
