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
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author labfe
 */
public class BarcodeDialog {
    @FXML
    private ImageView ivBarcode;
    private int BARCODE_WIDTH = 400;
    private int BARCODE_HEIGHT = 120;
    
    private Scene scene;
    private Stage stage;
    public BarcodeDialog(String code, Window owner) {
        stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto/Views/BarcodeView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            generateQR(code);
            stage.setResizable(false);
            //stage.initStyle(StageStyle.UTILITY);
            stage.initOwner(owner);
            stage.setMinHeight(400d);
            stage.setMinWidth(500d);
            stage.showAndWait();
        }catch(IOException | NullPointerException e){
        e.printStackTrace();
        }
        
    }
    
  
    
    private void generateQR(String code){
        
        try{
            Code39Writer c39Writter = new Code39Writer();
        
        //BitMatrix bmatrix = qrWritter.encode(UUIDUtils.asBytes(UUID.fromString(proformaCode)), BarcodeFormat.CODABAR, BARCODE_WIDTH, BARCODE_HEIGHT);
        BitMatrix bmatrix;
        bmatrix = c39Writter.encode(code, BarcodeFormat.CODE_39, BARCODE_WIDTH, BARCODE_HEIGHT);
        BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bmatrix);
        ivBarcode.setImage(SwingFXUtils.toFXImage(barcodeImage, null));
        ivBarcode.setFitWidth(250d);
        }catch(WriterException e){}
    }
    
    
}
